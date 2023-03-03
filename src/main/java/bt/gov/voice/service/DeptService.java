package bt.gov.voice.service;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dao.DeptDao;
import bt.gov.voice.dao.DivDao;
import bt.gov.voice.dto.*;
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
 * Created by USER on 7/6/2021.
 */
@Service
public class DeptService {

    @Autowired
    DeptDao deptDao;
    APIService tokenApi;
    CommonService commonService;
    @Autowired
    PublicService publicService;
    @Autowired
    DivDao divDao;

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage saveData(ApplicationDTO applicationDTO, String ServiceId, String ApplicationType) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationEntity applicationEntity = convertToEntity(applicationDTO);
        applicationEntity.setrequest_Service_Id(ServiceId);
        applicationEntity.setApplication_Type(ApplicationType);
        String output = deptDao.saveData(applicationEntity);
        if(output.equalsIgnoreCase("Success")){
            saveDocument(applicationDTO);
            WorkFlowEntity workFlowEntity=convertToWorkFlowEntity(applicationDTO);
            workFlowEntity.setApplication_number(applicationDTO.getApplication_Number());
            workFlowEntity.setCurrent_status("Pmo_forward");
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
            deptDao.saveUpdate(workFlowEntity);
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
            fileAttachment.setDocumentType(attachment.getContentType());
            String randomUUID = UUID.randomUUID().toString();
            String uuid = randomUUID.replaceAll("-", "");
            fileAttachment.setuUID(uuid);
            deptDao.saveUpdate(fileAttachment);
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
        return deptDao.getMyTaskList(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage getApplicantDetails(String application_No) {
        ResponseMessage responseMessage=new ResponseMessage();
        ApplicationDTO applicationDTO=deptDao.getApplicantDetail(application_No);
        return responseMessage;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ApplicationDTO> getTask(String appNo) {
        return deptDao.getTaskList(appNo);
    }

    public String getApplicationDetail(ApplicationDTO applicationDTO) {
        return null;
    }


    public Attachmentdto getDocumentDetailsByDocId(String uploadDocId) {
        Attachmentdto dto=deptDao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }


    public String submit_update(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        return deptDao.submit_update(applicationDTO);
    }

    @Transactional
    public List<DepartmentDTO> getDepartments(HttpServletRequest request) {
        return deptDao.getDepartments(request);
    }

    public String closed_submit(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        return deptDao.closed_submit(applicationDTO);
    }

    @Transactional
    public List<MinistryDTO> getMinistries(HttpServletRequest request) {
        return deptDao.getMinistries(request);
    }

    @Transactional
    public List<DivisionDTO> getDivisions(HttpServletRequest request) {
        return deptDao.getDivisions(request);
    }

    public String forward_submit(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        return deptDao.forward_submit(applicationDTO);
    }
    @Transactional
    public List<WorkFlowDTO> getMyTaskList1(UserRolePrivilege user) {
        return deptDao.getMyTaskList1(user);
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        List<Attachmentdto> attachmentdto = new ArrayList<Attachmentdto>();
        attachmentdto = deptDao.getDocumnets(appNo);
        return attachmentdto;
    }

    public ResponseMessage checkStatus(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<WorkFlowDTO> workFlowDTO = deptDao.checkStatus(appNo);
        responseMessage.setDto(workFlowDTO);
        if(workFlowDTO.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    public BigInteger myclosed_submit(UserRolePrivilege user) {
        return deptDao.myclosed_submit(user);
    }

    public BigInteger myreturns(UserRolePrivilege user) {
            return deptDao.myreturns(user);
    }

    public BigInteger myforwards(UserRolePrivilege user) {
        return deptDao.myforwards(user);
    }

    public BigInteger getTaskList1(UserRolePrivilege user) {
        return deptDao.getTaskList1(user);
    }

    public BigInteger news(UserRolePrivilege user) {
        return deptDao.news(user);
    }

    public List<WorkFlowDTO> closed(UserRolePrivilege user) {
        return deptDao.closed(user);
    }

    public List<WorkFlowDTO> forward(UserRolePrivilege user) {
        return deptDao.forward(user);
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        return deptDao.delivered(user);
    }

    public String getDepartment_name(int department_id) {
        return deptDao.getDepartment_name(department_id);
    }

    public ResponseMessage generate_report(String select_cat, String fromDate, String toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDTO> applicationDTOs = deptDao.generate_report(select_cat,fromDate,toDate);
        responseMessage.setDto(applicationDTOs);
        if(applicationDTOs.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }
}
