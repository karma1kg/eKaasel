package bt.gov.voice.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 5/27/2021.
 */
public class ApplicationDTO implements Serializable {
    private String application_Number;
    private int request_Service_Id;
    private String  g2C_Service_Tag;
    private Integer gRV_Catagory_Id;
    private String application_Type;
    private String g2C_Application_No;
    private String cID_Number;
    private String first_Name;
    private String middle_Name;
    private String last_Name;
    private String contact_No;
    private String email_Id;
    private String dzongkhag;
    private String  gewog;
    private String village;
    private String appl_Type_Remark;
    private String legal_Action_Tag;
    private Date expt_Date_Recv_Service;
    private String app_Submission_Date;
    private String pMO_Remark;
    private Date pMO_Forward_Date;
    private String pM_Remark;
    private String ministry_Remark;
    private int ministry_Id;
    private String ministryName;
    private Date ministry_Forward_Date;
    private String secretary_Remark;
    private Date secretary_Forward_Date;
    private String dept_Remark;
    private Date dept_Action_Date;
    private String  appl_Close_Tag;
    private Date appl_Close_Date;
    private String appl_Close_Remark;
    private String print_Status;
    private int service_Fee;
    private String application_Fee;
    private String closed_By_Type;
    private String closed_By;
    private String assigned_role;
    private String cidNo;
    private String applicantDob;
    private String dzongkhagName;
    private String gewogName;
     //private String mobileNo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String villageName;
    private Integer roleId;
    private String roleName;
    private String current_status;
    private List<Attachmentdto> fileAttachmentDTOs;
    private String document_Type;
    private String division_Remark;
    private Date division_Forward_Date;
    private Date dept_Forward_Date;
    private int department_Id;
    private String department_Name;
    private Date action_date;
    private String previous_stats;
    private int dept_Division_Id;
    private String dept_Division;
/*
    private List<FileAttachmentDTO> fileAttachmentDTOs;
*/
    private MultipartFile files;

    public ApplicationDTO() {
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public int getRequest_Service_Id() {
        return request_Service_Id;
    }

    public void setRequest_Service_Id(int request_Service_Id) {
        this.request_Service_Id = request_Service_Id;
    }

    public String getG2C_Service_Tag() {
        return g2C_Service_Tag;
    }

    public void setG2C_Service_Tag(String g2C_Service_Tag) {
        this.g2C_Service_Tag = g2C_Service_Tag;
    }

    public Integer getgRV_Catagory_Id() {
        return gRV_Catagory_Id;
    }

    public void setgRV_Catagory_Id(Integer gRV_Catagory_Id) {
        this.gRV_Catagory_Id = gRV_Catagory_Id;
    }

    public String getApplication_Type() {
        return application_Type;
    }

    public void setApplication_Type(String application_Type) {
        this.application_Type = application_Type;
    }

    public String getG2C_Application_No() {
        return g2C_Application_No;
    }

    public void setG2C_Application_No(String g2C_Application_No) {
        this.g2C_Application_No = g2C_Application_No;
    }

    public String getcID_Number() {
        return cID_Number;
    }

    public void setcID_Number(String cID_Number) {
        this.cID_Number = cID_Number;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getMiddle_Name() {
        return middle_Name;
    }

    public void setMiddle_Name(String middle_Name) {
        this.middle_Name = middle_Name;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public String getContact_No() {
        return contact_No;
    }

    public void setContact_No(String contact_No) {
        this.contact_No = contact_No;
    }

    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public String getDzongkhag() {
        return dzongkhag;
    }

    public void setDzongkhag(String dzongkhag) {
        this.dzongkhag = dzongkhag;
    }

    public String getGewog() {
        return gewog;
    }

    public void setGewog(String gewog) {
        this.gewog = gewog;
    }

    public String getAppl_Type_Remark() {
        return appl_Type_Remark;
    }

    public void setAppl_Type_Remark(String appl_Type_Remark) {
        this.appl_Type_Remark = appl_Type_Remark;
    }

    public String getLegal_Action_Tag() {
        return legal_Action_Tag;
    }

    public void setLegal_Action_Tag(String legal_Action_Tag) {
        this.legal_Action_Tag = legal_Action_Tag;
    }

    public Date getExpt_Date_Recv_Service() {
        return expt_Date_Recv_Service;
    }

    public void setExpt_Date_Recv_Service(Date expt_Date_Recv_Service) {
        this.expt_Date_Recv_Service = expt_Date_Recv_Service;
    }

    public String getApp_Submission_Date() {
        return app_Submission_Date;
    }

    public void setApp_Submission_Date(String app_Submission_Date) {
        this.app_Submission_Date = app_Submission_Date;
    }

    public String getpMO_Remark() {
        return pMO_Remark;
    }

    public void setpMO_Remark(String pMO_Remark) {
        this.pMO_Remark = pMO_Remark;
    }

    public Date getpMO_Forward_Date() {
        return pMO_Forward_Date;
    }

    public void setpMO_Forward_Date(Date pMO_Forward_Date) {
        this.pMO_Forward_Date = pMO_Forward_Date;
    }

    public String getpM_Remark() {
        return pM_Remark;
    }

    public void setpM_Remark(String pM_Remark) {
        this.pM_Remark = pM_Remark;
    }

    public String getMinistry_Remark() {
        return ministry_Remark;
    }

    public void setMinistry_Remark(String ministry_Remark) {
        this.ministry_Remark = ministry_Remark;
    }

    public Date getMinistry_Forward_Date() {
        return ministry_Forward_Date;
    }

    public void setMinistry_Forward_Date(Date ministry_Forward_Date) {
        this.ministry_Forward_Date = ministry_Forward_Date;
    }

    public String getSecretary_Remark() {
        return secretary_Remark;
    }

    public void setSecretary_Remark(String secretary_Remark) {
        this.secretary_Remark = secretary_Remark;
    }

    public Date getSecretary_Forward_Date() {
        return secretary_Forward_Date;
    }

    public void setSecretary_Forward_Date(Date secretary_Forward_Date) {
        this.secretary_Forward_Date = secretary_Forward_Date;
    }

    public String getDept_Remark() {
        return dept_Remark;
    }

    public void setDept_Remark(String dept_Remark) {
        this.dept_Remark = dept_Remark;
    }

    public Date getDept_Action_Date() {
        return dept_Action_Date;
    }

    public void setDept_Action_Date(Date dept_Action_Date) {
        this.dept_Action_Date = dept_Action_Date;
    }

    public String getAppl_Close_Tag() {
        return appl_Close_Tag;
    }

    public void setAppl_Close_Tag(String appl_Close_Tag) {
        this.appl_Close_Tag = appl_Close_Tag;
    }

    public Date getAppl_Close_Date() {
        return appl_Close_Date;
    }

    public void setAppl_Close_Date(Date appl_Close_Date) {
        this.appl_Close_Date = appl_Close_Date;
    }

    public String getAppl_Close_Remark() {
        return appl_Close_Remark;
    }

    public void setAppl_Close_Remark(String appl_Close_Remark) {
        this.appl_Close_Remark = appl_Close_Remark;
    }

    public String getPrint_Status() {
        return print_Status;
    }

    public void setPrint_Status(String print_Status) {
        this.print_Status = print_Status;
    }

    public int getService_Fee() {
        return service_Fee;
    }

    public void setService_Fee(int service_Fee) {
        this.service_Fee = service_Fee;
    }

    public String getApplication_Fee() {
        return application_Fee;
    }

    public void setApplication_Fee(String application_Fee) {
        this.application_Fee = application_Fee;
    }

    public String getClosed_By_Type() {
        return closed_By_Type;
    }

    public void setClosed_By_Type(String closed_By_Type) {
        this.closed_By_Type = closed_By_Type;
    }

    public String getClosed_By() {
        return closed_By;
    }

    public void setClosed_By(String closed_By) {
        this.closed_By = closed_By;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
    }

    public String getApplicantDob() {
        return applicantDob;
    }

    public void setApplicantDob(String applicantDob) {
        this.applicantDob = applicantDob;
    }

    public String getDzongkhagName() {
        return dzongkhagName;
    }

    public void setDzongkhagName(String dzongkhagName) {
        this.dzongkhagName = dzongkhagName;
    }

    public String getGewogName() {
        return gewogName;
    }

    public void setGewogName(String gewogName) {
        this.gewogName = gewogName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public MultipartFile getFiles() {
        return files;
    }

    public void setFiles(MultipartFile files) {
        this.files = files;
    }

    public int getMinistry_Id() {
        return ministry_Id;
    }

    public void setMinistry_Id(int ministry_Id) {
        this.ministry_Id = ministry_Id;
    }

    public String getAssigned_role() {
        return assigned_role;
    }

    public void setAssigned_role(String assigned_role) {
        this.assigned_role = assigned_role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }


    public List<Attachmentdto> getFileAttachmentDTOs() {
        return fileAttachmentDTOs;
    }

    public void setFileAttachmentDTOs(List<Attachmentdto> fileAttachmentDTOs) {
        this.fileAttachmentDTOs = fileAttachmentDTOs;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public String getDivision_Remark() {
        return division_Remark;
    }

    public void setDivision_Remark(String division_Remark) {
        this.division_Remark = division_Remark;
    }

    public Date getDivision_Forward_Date() {
        return division_Forward_Date;
    }

    public void setDivision_Forward_Date(Date division_Forward_Date) {
        this.division_Forward_Date = division_Forward_Date;
    }

    public Date getDept_Forward_Date() {
        return dept_Forward_Date;
    }

    public void setDept_Forward_Date(Date dept_Forward_Date) {
        this.dept_Forward_Date = dept_Forward_Date;
    }

    public int getDepartment_Id() {
        return department_Id;
    }

    public void setDepartment_Id(int department_Id) {
        this.department_Id = department_Id;
    }

    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }

    public String getPrevious_stats() {
        return previous_stats;
    }

    public void setPrevious_stats(String previous_stats) {
        this.previous_stats = previous_stats;
    }

    public String getMinistryName() {
        return ministryName;
    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public String getDepartment_Name() {
        return department_Name;
    }

    public void setDepartment_Name(String department_Name) {
        this.department_Name = department_Name;
    }

    public int getDept_Division_Id() {
        return dept_Division_Id;
    }

    public void setDept_Division_Id(int dept_Division_Id) {
        this.dept_Division_Id = dept_Division_Id;
    }

    public String getDept_Division() {
        return dept_Division;
    }

    public void setDept_Division(String dept_Division) {
        this.dept_Division = dept_Division;
    }

}
