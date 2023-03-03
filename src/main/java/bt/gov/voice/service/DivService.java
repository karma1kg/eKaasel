package bt.gov.voice.service;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dao.DivDao;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 7/15/2021.
 */
@Service
public class DivService {
    @Autowired
    DivDao divDao;

    @Autowired
    MinistryDao ministryDao;
    @Autowired
    APIService tokenApi;
    @Autowired
    CommonService commonService;

    @Autowired
    PublicService publicService;


 /*   @Transactional
    public String closed_update(ApplicationDTO applicationDTO) throws Exception{

        publicService.saveDocument(applicationDTO);

        return ministryDao.closed_update(applicationDTO);
    }

    public String submit_update(ApplicationDTO dto) throws Exception {
        publicService.saveDocument(dto);
        return ministryDao.submit_update(dto);
    }*/


    public String saved_forward(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
       /* publicService.saveDocument(applicationDTO, applicationDTO.getApplication_Number(), document_type);*/
        return divDao.saved_forward(applicationDTO);
    }

    public List<WorkFlowDTO> getMyTaskList() {
        return divDao.getMyTaskList();
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ApplicationDTO> getTask(String appNo) {
        return divDao.getTaskList(appNo);
    }

    public String closed_update(ApplicationDTO applicationDTO, MultipartFile[] files) {
        return divDao.closed_update(applicationDTO);
    }
    @Transactional
    public List<DepartmentDTO> getDepartments(HttpServletRequest request) {
        return divDao.getDepartments(request);
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        List<Attachmentdto> attachmentdto = new ArrayList<Attachmentdto>();
        attachmentdto = divDao.getDocumnets(appNo);
        return attachmentdto;
    }

    public ResponseMessage checkStatus(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<WorkFlowDTO> workFlowDTO = divDao.checkStatus(appNo);
        responseMessage.setDto(workFlowDTO);
        if(workFlowDTO.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    public List<WorkFlowDTO> divclosed() {
        return divDao.divclosed();
    }

    public List<WorkFlowDTO> myreturns() {
        return divDao.myreturns();
    }

    public List<WorkFlowDTO> closed(UserRolePrivilege user) {
        return divDao.closed(user);
    }

    public List<WorkFlowDTO> forward(UserRolePrivilege user) {
        return divDao.forward(user);
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        return divDao.delivered(user);
    }

    public String getDivision_name(int dept_division_id) {
        return divDao.getDivision_name(dept_division_id);
    }

    public ResponseMessage generate_report(String select_cat, String fromDate, String toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDTO> applicationDTOs = divDao.generate_report(select_cat,fromDate,toDate);
        responseMessage.setDto(applicationDTOs);
        if(applicationDTOs.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }
}
