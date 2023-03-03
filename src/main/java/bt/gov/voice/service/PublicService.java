package bt.gov.voice.service;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dao.PublicDao;
import bt.gov.voice.dto.*;
import bt.gov.voice.entity.*;
import bt.gov.voice.lib.ResponseMessage;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizenDetailsResponse;
import org.wso2.client.model.DCRC_CitizenDetailsAPI.CitizendetailsObj;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by USER on 5/26/2021.
 */
@Service
public class PublicService {
    @Autowired
    private PublicDao publicDao;
    @Autowired
    private APIService tokenApi;
    @Autowired
    private CommonService commonService;
    @Autowired
    private PublicService publicService;

    @Transactional
    public List<MinistryDTO> getMinistries(HttpServletRequest request) {
        return publicDao.getMinistries(request);
    }
    @Transactional
    public List<MinistryDTO> getMinistriesReport(HttpServletRequest request) {
        return publicDao.getMinistriesReport(request);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage saveData(ApplicationDTO applicationDTO, String ServiceId, String ApplicationType, MultipartFile[] files, UserRolePrivilege user) throws Exception {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationEntity applicationEntity = convertToEntity(applicationDTO);
        applicationEntity.setApp_Submission_Date(new Date());
        applicationEntity.setrequest_Service_Id(ServiceId);
        applicationEntity.setApplication_Type(ApplicationType);
        String output = publicDao.saveData(applicationEntity);
        if (output.equalsIgnoreCase("Success")) {
            /*saveDocument(applicationDTO, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());*/
            WorkFlowEntity workFlowEntity = convertToWorkFlowEntity(applicationDTO);
            workFlowEntity.setApplication_number(applicationDTO.getApplication_Number());
            workFlowEntity.setApp_Submission_Date(new Date());
            workFlowEntity.setAction_date(new Date());
            workFlowEntity.setCurrent_status("SUBMITTED");
            workFlowEntity.setRecord_date(new Date());
            workFlowEntity.setAction_trail("Pending at Prime Ministers Office for further action ");
                workFlowEntity.setAssigned_role("4001");
            publicDao.saveUpdate(workFlowEntity);
           /* publicDao.getApplicationDetail(applicationEntity);*/
        }
        responseMessage.setStatus(0);
       /* responseMessage.setText("Your Response has been submitted.Your Application No.is:"+applicationDTO.getApplication_Number());*/
        return responseMessage;
    }

    private WorkFlowAuditEntity convertToWorkFlowAuditEntity(ApplicationDTO applicationDTO) {
        WorkFlowAuditEntity workFlowAuditEntity = new WorkFlowAuditEntity();
        workFlowAuditEntity.setApplication_number(applicationDTO.getApplication_Number());
        BeanUtils.copyProperties(applicationDTO, workFlowAuditEntity);
        return workFlowAuditEntity;
    }



    @Transactional
    public void saveDocument(MultipartFile[] files, String application_number, String document_type) throws Exception {
        FileAttachment fileAttachment = new FileAttachment();
        String newDocNameUpload = "";
       /* FileAttachment fileAttachment=new FileAttachment();
       MultipartFile attachment = applicationDTO.getFiles();
       // MultipartFile attachment = applicationDTO.getFileAttachmentDTOs();
        String newDocNameUpload="";
        if(attachment != null|| !attachment.isEmpty()) {
            String docNameUpload = attachment.getOriginalFilename();
            newDocNameUpload = docNameUpload.replaceAll("[^\\w.-]", "_");
            String specificLoc ="/PublicGrievance/Doc/";
            String docPath = commonService.uploadDocument(attachment, specificLoc, newDocNameUpload);
            fileAttachment.setApplicationNo(applicationDTO.getApplication_Number());
            fileAttachment.setUploadUrl(docPath);
            fileAttachment.setDocumentName(newDocNameUpload);
            fileAttachment.setDocumentType(applicationDTO.getDocument_Type());
            String randomUUID = UUID.randomUUID().toString();
            String uuid = randomUUID.replaceAll("-", "");
            fileAttachment.setuUID(uuid);
            publicDao.saveUpdate(fileAttachment);
        }*/

        for (int i = 0; i < files.length; i++) {
            String docNameUpload = files[i].getOriginalFilename();
            newDocNameUpload = docNameUpload.replaceAll("[^\\w.-]", "_");
            String specificLoc = "/PublicGrievance/Doc/";
            String docPath = commonService.uploadDocument(files[i], specificLoc, newDocNameUpload);
            fileAttachment.setApplicationNo(application_number);
            fileAttachment.setUploadUrl(docPath);
            fileAttachment.setDocumentName(newDocNameUpload);
            fileAttachment.setDocumentType(document_type);
            String randomUUID = UUID.randomUUID().toString();
            String uuid = randomUUID.replaceAll("-", "");
            fileAttachment.setuUID(uuid);
            publicDao.saveUpdate(fileAttachment);
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
    public String generateApplicationNumber(Integer globalServiceTypeId) {
        String applicationNumber = publicDao.generateApplicationNumber(globalServiceTypeId);
        return applicationNumber;
    }

    @Transactional(rollbackOn = Exception.class)
    /*Citizen API for fetching All Citizen Details*/
    public CitizenDetailDTO getAllCitizenDetails(String cidNo) {
        CitizenDetailDTO citizenDetailDTO = new CitizenDetailDTO();
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String getAllCitizenDetails = resourceBundle1.getString("getCitizenDetails.endPointURL");
        try {
            OkHttpClient httpClient = new OkHttpClient();
            httpClient.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
            httpClient.setReadTimeout(10000, TimeUnit.MILLISECONDS);
            org.wso2.client.api.ApiClient apiClient = new org.wso2.client.api.ApiClient();
            /*ApiClient apiClient = new ApiClient();*/
            apiClient.setHttpClient(httpClient);
            apiClient.setBasePath(getAllCitizenDetails);
            Token token = tokenApi.getApplicationToken();
            apiClient.setAccessToken(token.getAccess_token());
            org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi api = new org.wso2.client.api.DCRC_CitizenDetailsAPI.DefaultApi(apiClient);
            CitizenDetailsResponse citizenDetailsResponse = api.citizendetailsCidGet(cidNo);

            if (citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail() != null && !citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().isEmpty()) {
                CitizendetailsObj citizendetailsObj = citizenDetailsResponse.getCitizenDetailsResponse().getCitizenDetail().get(0);
                citizenDetailDTO.setFirstName(citizendetailsObj.getFirstName());
                citizenDetailDTO.setMiddleName(citizendetailsObj.getMiddleName());
                citizenDetailDTO.setLastName(citizendetailsObj.getLastName());
                citizenDetailDTO.setApplicantDob(citizendetailsObj.getDob());
                citizenDetailDTO.setVillageName(citizendetailsObj.getVillageName());
                citizenDetailDTO.setGewogName(citizendetailsObj.getGewogName());
                citizenDetailDTO.setDzongkhagName(citizendetailsObj.getDzongkhagName());

                return citizenDetailDTO;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return citizenDetailDTO;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<WorkFlowDTO> getMyTaskList(UserRolePrivilege user) {
        return publicDao.getMyTaskList(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public ResponseMessage getApplicantDetails(String application_No) {
        ResponseMessage responseMessage = new ResponseMessage();
        ApplicationDTO applicationDTO = publicDao.getApplicantDetail(application_No);
        return responseMessage;
    }

    @Transactional(rollbackOn = Exception.class)
    public List<ApplicationDTO> getTask(String appNo) {
        return publicDao.getTaskList(appNo);
    }


    public Attachmentdto getDocumentDetailsByDocId(String uploadDocId) {
        Attachmentdto dto = publicDao.getDocumentDetailsByDocId(uploadDocId);
        return dto;
    }


    public String submit_this(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {

        return publicDao.submit_this(applicationDTO);
    }

    public List<WorkFlowDTO> getMyTaskList1(UserRolePrivilege user) {
        return publicDao.getMyTaskList1(user);
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        List<Attachmentdto> attachmentdto = new ArrayList<Attachmentdto>();
        attachmentdto = publicDao.getDocumnets(appNo);
        return attachmentdto;
    }

    public String closed_update(ApplicationDTO applicationDTO, MultipartFile[] files) throws Exception {
        return publicDao.closed_update(applicationDTO);
    }


   /* public String closed_update(ApplicationDTO applicationDTO) {
        return publicDao.closed_update(applicationDTO);
    }*/

    public void saveMe(ApplicationDTO applicationDTO) throws Exception {
        FileAttachment fileAttachment=new FileAttachment();
        MultipartFile attachment = applicationDTO.getFiles();
        // MultipartFile attachment = applicationDTO.getFileAttachmentDTOs();
        String newDocNameUpload="";
        if(attachment != null|| !attachment.isEmpty()) {
            String docNameUpload = attachment.getOriginalFilename();
            newDocNameUpload = docNameUpload.replaceAll("[^\\w.-]", "_");
            String specificLoc = "/PublicGrievance/Doc/";
            String docPath = commonService.uploadDocument(attachment, specificLoc, newDocNameUpload);
            fileAttachment.setApplicationNo(applicationDTO.getApplication_Number());
            fileAttachment.setUploadUrl(docPath);
            fileAttachment.setDocumentName(newDocNameUpload);
            fileAttachment.setDocumentType(applicationDTO.getDocument_Type());
            String randomUUID = UUID.randomUUID().toString();
            String uuid = randomUUID.replaceAll("-", "");
            fileAttachment.setuUID(uuid);
            publicDao.saveUpdate(fileAttachment);
        }
    }

    public String submit_update(ApplicationDTO dto, MultipartFile[] files) {
        return publicDao.submit_update(dto);
    }

    public List<WorkFlowDTO> getMyTaskList2(UserRolePrivilege user) {
        return publicDao.getMyTaskList2(user);
    }

    public List<WorkFlowDTO> closed_update(UserRolePrivilege user) {
            return publicDao.closed_update(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public BigInteger submitme(UserRolePrivilege user) {
            return publicDao.submitme(user);
    }
    @Transactional(rollbackOn = Exception.class)
    public BigInteger submitime(UserRolePrivilege user) {
        return publicDao.submitime(user);
    }

    public ResponseMessage checkStatus(String appNo) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<WorkFlowDTO> workFlowDTO = publicDao.checkStatus(appNo);
        responseMessage.setDto(workFlowDTO);
        if(workFlowDTO.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }

    public BigInteger new_grievance(UserRolePrivilege user) {
        return publicDao.new_grievance(user);
    }

    public BigInteger pm_returns(UserRolePrivilege user) {
        return publicDao.pm_returns(user);
    }

    public BigInteger ministry_returns(UserRolePrivilege user) {
        return publicDao.ministry_returns(user);
    }

    public BigInteger rejects(UserRolePrivilege user) {
        return publicDao.rejects(user);
    }

    public List<WorkFlowDTO> closed(UserRolePrivilege user) {
        return publicDao.closed(user);
    }

    public List<WorkFlowDTO> tasklist(UserRolePrivilege user) {
        return publicDao.tasklist(user);
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        return publicDao.delivered(user);
    }

    public ResponseMessage generate_report(String select_cat,String fromDate, String toDate) {
        ResponseMessage responseMessage = new ResponseMessage();
        List<ApplicationDTO> applicationDTOs = publicDao.generate_report(select_cat,fromDate,toDate);
        responseMessage.setDto(applicationDTOs);
        if(applicationDTOs.size()>0){
            responseMessage.setStatus(1);
        }
        return responseMessage;
    }



   /* public void closeDocument(ApplicationDTO applicationDTO) {
        try {
            FileAttachment fileAttachment=new FileAttachment();
            MultipartFile attachment = applicationDTO.getFiles();
            // MultipartFile attachment = applicationDTO.getFileAttachmentDTOs();
            String newDocNameUpload="";
            if(attachment != null|| !attachment.isEmpty()) {
                String docNameUpload = attachment.getOriginalFilename();
                newDocNameUpload = docNameUpload.replaceAll("[^\\w.-]", "_");
                String specificLoc = "/PublicGrievance/Doc/";
                String docPath = commonService.uploadDocument(attachment, specificLoc, newDocNameUpload);
                fileAttachment.setApplicationNo(a   pplicationDTO.getApplication_Number());
                fileAttachment.setUploadUrl(docPath);
                fileAttachment.setDocumentName(newDocNameUpload);
                fileAttachment.setDocumentType(applicationDTO.getDocument_Type());
                String randomUUID = UUID.randomUUID().toString();
                String uuid = randomUUID.replaceAll("-", "");
                fileAttachment.setuUID(uuid);
                publicDao.saveUpdate(fileAttachment);
            }
            }
        catch (Exception e){
            e.printStackTrace();
        }

        return;
    }*/

}

