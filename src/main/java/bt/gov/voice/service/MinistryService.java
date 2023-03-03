package bt.gov.voice.service;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dao.MinistryDao;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.DepartmentDTO;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.lib.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 6/30/2021.
 */
@Service
public class MinistryService {

    @Autowired
    MinistryDao ministryDao;
    @Autowired
    APIService tokenApi;
    @Autowired
    CommonService commonService;

    @Autowired
    PublicService publicService;


    @Transactional
    public List<WorkFlowDTO> getMyTaskList(UserRolePrivilege user) {
        return ministryDao.getMyTaskList(user);
    }
    /*public List<WorkFlowDTO> getMytasks() {
        return ministryDao.getMytasks();
    }*/
    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage getApplicantDetails(String application_No) {
        ResponseMessage responseMessage=new ResponseMessage();
        ApplicationDTO applicationDTO=ministryDao.getApplicantDetail(application_No);
        return responseMessage;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ApplicationDTO> getTask(String appNo) {
        return ministryDao.getTaskList(appNo);
    }
 /*   public List<ApplicationDTO> getTasks(String appNo) {
        return ministryDao.getTasks(appNo);
    }*/
    public String getApplicationDetail(ApplicationDTO applicationDTO) {
        return null;
    }


    public Attachmentdto getDocumentDetailsByDocId(String uploadDocId) {
        Attachmentdto dto=ministryDao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }



    public String updateApplicationDetails(ApplicationDTO dto, MultipartFile[] files) throws Exception {
       /* publicService.saveDocument(dto, applicationDTO.getApplication_Number(), document_type);*/
        return ministryDao.updateApplicationDetails(dto);
    }

    @Transactional
    public String closed_update(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception{
        return ministryDao.closed_update(applicationDTO);
    }


        @Transactional
        public List<DepartmentDTO> getDepartments(HttpServletRequest request) {
            return ministryDao.getDepartments(request);
        }

    public String saved_forward(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        /*publicService.saveDocument(applicationDTO, applicationDTO.getApplication_Number(), document_type);*/
        return ministryDao.saved_forward(applicationDTO);
    }

    public List<WorkFlowDTO> getMyTaskList1(UserRolePrivilege user) {
        return ministryDao.getMyTaskList1(user);
    }

    public List<ApplicationDTO> getTasks(String appNo) {
        return ministryDao.getTasks(appNo);
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        List<Attachmentdto> attachmentdto = new ArrayList<Attachmentdto>();
        attachmentdto = ministryDao.getDocumnets(appNo);
        return attachmentdto;
    }

    @Transactional
    public ResponseMessage checkStatus(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
       List<WorkFlowDTO> workFlowDTO = ministryDao.checkStatus(appNo);
        responseMessage.setDto(workFlowDTO);
        if(workFlowDTO.size()>0){
            responseMessage.setStatus(1);
        }

        return responseMessage;
    }


    public BigInteger closed_updates(UserRolePrivilege user) {
        return ministryDao.closed_updates(user);
    }

    public BigInteger myforward(UserRolePrivilege user) {
        return ministryDao.myforward(user);
    }

    public BigInteger myreturn(UserRolePrivilege user) {
        return ministryDao.myreturn(user);
    }

    public BigInteger delivered_count(UserRolePrivilege user) {
        return ministryDao.delivered_count(user);
    }

    public BigInteger new_grievance(UserRolePrivilege user) {
        return ministryDao.new_grievance(user);
    }

    public List<WorkFlowDTO> closed(UserRolePrivilege user) {
        return ministryDao.closed(user);
    }

    public List<WorkFlowDTO> forward(UserRolePrivilege user) {
        return ministryDao.forward(user);
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        return ministryDao.delivered();
    }


    public String getMinistryName(int ministry_id) {
        return ministryDao.getMinistryName(ministry_id);
    }

    public ResponseMessage generate_report(String select_cat, String fromDate, String toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDTO> applicationDTOs = ministryDao.generate_report(select_cat,fromDate,toDate);
        responseMessage.setDto(applicationDTOs);
        if(applicationDTOs.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }
}
