package bt.gov.voice.service;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dao.PmDao;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.MinistryDTO;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.entity.ApplicationEntity;
import bt.gov.voice.entity.FileAttachment;
import bt.gov.voice.entity.WorkFlowEntity;
import bt.gov.voice.lib.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by USER on 7/5/2021.
 */
@Service
public class PmService {
    @Autowired
    APIService tokenApi;
    @Autowired
    CommonService commonService;
    @Autowired
    PmDao pmDao;
    @Autowired
    PublicService publicService;

    @Transactional
    public List<MinistryDTO> getMinistries(HttpServletRequest request) {
        return pmDao.getMinistries(request);
    }

/*
 @Override
    public String saveData(TestDTO TestDTO) {
        return null;
    }
*/

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage saveData(ApplicationDTO applicationDTO, String ServiceId, String ApplicationType) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationEntity applicationEntity = convertToEntity(applicationDTO);
        applicationEntity.setrequest_Service_Id(ServiceId);
        applicationEntity.setApplication_Type(ApplicationType);
        String output = pmDao.saveData(applicationEntity);
        if(output.equalsIgnoreCase("Success")){
            saveDocument(applicationDTO);
            WorkFlowEntity workFlowEntity=convertToWorkFlowEntity(applicationDTO);
            workFlowEntity.setApplication_number(applicationDTO.getApplication_Number());
            workFlowEntity.setCurrent_status("Pmo_forward_to_Pm");
            workFlowEntity.setPrevious_stats("SUBMITTED");
            workFlowEntity.setAction_date(new Date());
            workFlowEntity.setRecord_date(new Date());
            workFlowEntity.setPrevious_stats(workFlowEntity.getPrevious_stats());
            workFlowEntity.setCurrent_status(workFlowEntity.getCurrent_status());
            workFlowEntity.setAssigned_role(workFlowEntity.getAssigned_role());
            workFlowEntity.setAssigned_user(workFlowEntity.getAssigned_user());
            workFlowEntity.setAssigned_ministry(workFlowEntity.getAssigned_ministry());
            workFlowEntity.setAssigned_dept(workFlowEntity.getAssigned_dept());
            workFlowEntity.setAssigned_secretary(workFlowEntity.getAssigned_secretary());
            workFlowEntity.setAction_trail(workFlowEntity.getAction_trail());
            pmDao.saveUpdate(workFlowEntity);
           /* publicDao.getApplicationDetail(applicationEntity);*/
        }
        responseMessage.setStatus(0);
       /* responseMessage.setText("Your Response has been submitted.Your Application No.is:"+applicationDTO.getApplication_Number());*/
        return responseMessage;
    }

    @Transactional
    private void saveDocument(ApplicationDTO applicationDTO) throws Exception {
        FileAttachment fileAttachment=new FileAttachment();
        MultipartFile attachment = applicationDTO.getFiles();
        String newDocNameUpload="";
        if(attachment != null|| !attachment.isEmpty()) {
            String docNameUpload = attachment.getOriginalFilename();
            newDocNameUpload = docNameUpload.replaceAll("[^\\w.-]", "_");
            String specificLoc ="/PublicGrievance/Doc/";
            String docPath = commonService.uploadDocument(attachment, specificLoc, newDocNameUpload);
            fileAttachment.setApplicationNo(applicationDTO.getApplication_Number());
            fileAttachment.setUploadUrl(docPath);
            fileAttachment.setDocumentName(newDocNameUpload);
            fileAttachment.setDocumentType(fileAttachment.getDocumentType());
            String randomUUID = UUID.randomUUID().toString();
            String uuid = randomUUID.replaceAll("-", "");
            fileAttachment.setuUID(uuid);
            pmDao.saveUpdate(fileAttachment);
        }
    }


    private ApplicationEntity convertToEntity(ApplicationDTO applicationDTO) {
        ApplicationEntity applicationEntity = new ApplicationEntity();
        BeanUtils.copyProperties(applicationDTO, applicationEntity);
        return applicationEntity;
    }

    private WorkFlowEntity convertToWorkFlowEntity(ApplicationDTO applicationDTO) {
        WorkFlowEntity applicationEntity = new WorkFlowEntity();
        applicationEntity.setApplication_number(applicationDTO.getApplication_Number());
        BeanUtils.copyProperties(applicationDTO, applicationEntity);
        return applicationEntity;
    }

    @Transactional
    public List<WorkFlowDTO> getMyTaskList(UserRolePrivilege user) {
        return pmDao.getMyTaskList(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage getApplicantDetails(String application_No) {
        ResponseMessage responseMessage=new ResponseMessage();
        ApplicationDTO applicationDTO=pmDao.getApplicantDetail(application_No);
        return responseMessage;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ApplicationDTO> getTask(String appNo) {
        return pmDao.getTaskList(appNo);
    }

    public String getApplicationDetail(ApplicationDTO applicationDTO) {
        return null;
    }


    public Attachmentdto getDocumentDetailsByDocId(String uploadDocId) {
        Attachmentdto dto=pmDao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }


    public String updateApplicationDetails(ApplicationDTO dto) {
        return pmDao.updateApplicationDetails(dto);
    }

    public String closed_update(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        /*publicService.saveDocument(applicationDTO, applicationDTO.getApplication_Number(), document_type);*/
        return pmDao.closed_update(applicationDTO);
    }

    public String getRemark(String appNo)
    {
        return pmDao.getRemark(appNo);
    }

    @Transactional
    public String pmo_forward(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception{
            /*publicService.saveDocument(applicationDTO, applicationDTO.getApplication_Number(), document_type);*/
        return pmDao.pmo_forward(applicationDTO);
    }

    @Transactional
    public ResponseMessage checkStatus(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<WorkFlowDTO> workFlowDTO = pmDao.checkStatus(appNo);
        responseMessage.setDto(workFlowDTO);
        if(workFlowDTO.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        List<Attachmentdto> attachmentdto = new ArrayList<Attachmentdto>();
        attachmentdto = pmDao.getDocumnets(appNo);
        return attachmentdto;
    }
    @Transactional(rollbackOn = Exception.class)
    public BigInteger closed_updates(UserRolePrivilege user) {
            return pmDao.closed_updates(user);
    }

    public BigInteger myforwards(UserRolePrivilege user) {
            return pmDao.myforwards(user);
    }
    @Transactional(rollbackOn = Exception.class)
    public BigInteger task(UserRolePrivilege user) {
        return pmDao.task(user);
    }

    public List<WorkFlowDTO> closed_ones(UserRolePrivilege user) {
        return pmDao.closed_ones(user);
    }

    public List<WorkFlowDTO> forwards(UserRolePrivilege user) {
        return pmDao.forwards(user);
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        return pmDao.delivered(user);
    }

    public ResponseMessage generate_report(String select_cat, String fromDate, String toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDTO> applicationDTOs = pmDao.generate_report(select_cat,fromDate,toDate);
        responseMessage.setDto(applicationDTOs);
        if(applicationDTOs.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }




  /*  public String getRemark(String s) {
        return pmDao.getRemark();
    }*/


/*
    public String saveData(ApplicationDTO applicationDTO) {
        return saveData(applicationDTO);
    }

    public String saveFormData(ApplicationDTO applicationDTO) {
        return null;
    }*/


}
