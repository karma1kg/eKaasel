package bt.gov.voice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="t_grv_application")
public class ApplicationEntity {
    @Id
    @Column(name = "Application_Number")
    private String application_Number;
    @Column(name = "CID_Number")
    private String cidNo;
    @Column(name = "Request_Service_Id")
    private String request_Service_Id;
    @Column(name = "G2C_Service_Tag")
    private String g2C_Service_Tag;
    @Column(name = "GRV_Category_Id")
    private Integer gRV_Catagory_Id;
    @Column(name = "Application_Type")
    private String application_Type;
    @Column(name = "G2C_Application_No")
    private String g2C_Application_No;
    @Column(name = "First_Name")
    private String firstName;
    @Column(name = "Middle_Name")
    private String middleName;
    @Column(name = "Last_Name")
    private String lastName;
    @Column(name = "Contact_No")
    private String contact_No;
    @Column(name = "Email_Id")
    private String email_Id;
    @Column(name = "dzongkhag")
    private String dzongkhagName;
    @Column(name = "gewog")
    private String gewogName;
    @Column(name = "village")
    private String villageName;
    @Column(name = "Appl_Type_Remark")
    private String appl_Type_Remark;
    @Column(name = "Legal_Action_Tag")
    private String legal_Action_Tag;
    @Column(name = "Expt_Date_Recv_Service")
    private Date expt_Date_Recv_Service;
    @Column(name = "App_Submission_Date")
    private Date app_Submission_Date;
    @Column(name = "PMO_Remark")
    private String pMO_Remark;
    @Column(name = "PMO_Forward_Date")
    private Date pMO_Forward_Date;
    @Column(name = "PM_Remark")
    private String pM_Remark;
    @Column(name = "Ministry_Remark")
    private String ministry_Remark;
    @Column(name = "Ministry_Forward_Date")
    private Date ministry_Forward_Date;
    @Column(name = "Secretary_Remark")
    private String secretary_Remark;
    @Column(name = "Secretary_Forward_Date")
    private Date secretary_Forward_Date;
    @Column(name = "Dept_Remark")
    private String dept_Remark;
    @Column(name = "Dept_Action_Date")
    private Date dept_Action_Date;
    @Column(name = "Division_Remark")
    private String division_Remark;
    @Column(name = "Division_Forward_Date")
    private Date division_Forward_Date;
    @Column(name = "Appl_Close_Tag")
    private String appl_Close_Tag;
    @Column(name = "Appl_Close_Date")
    private Date appl_Close_Date;
    @Column(name = "Print_Status")
    private String print_Status;
    @Column(name = "Service_Fee")
    private int service_Fee;
    @Column(name = "Application_Fee")
    private String application_Fee;
    @Column(name = "Closed_By_Type")
    private String closed_By_Type;
    @Column(name="Closed_By")
    private String closed_By;
    @Column(name="Appl_Close_Remark")
    private String appl_Close_Remark;
    @Column(name = "Dept_Forward_Date")
    private Date dept_Forward_Date;
    public ApplicationEntity() {
    }

    public String getDivision_Remark() {
        return division_Remark;
    }

    public void setDivision_Remark(String division_Remark) {
        this.division_Remark = division_Remark;
    }

    public String getClosed_By() {
        return closed_By;
    }

    public void setClosed_By(String closed_By) {
        this.closed_By = closed_By;
    }

    public String getAppl_Type_Remark() {
        return appl_Type_Remark;
    }

    public void setAppl_Type_Remark(String appl_Type_Remark) {
        this.appl_Type_Remark = appl_Type_Remark;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getG2C_Service_Tag() {
        return g2C_Service_Tag;
    }

    public void setG2C_Service_Tag(String g2C_Service_Tag) {
        this.g2C_Service_Tag = g2C_Service_Tag;
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

    public String getRequest_Service_Id() {
        return request_Service_Id;
    }

    public void setRequest_Service_Id(String request_Service_Id) {
        this.request_Service_Id = request_Service_Id;
    }

    public Integer getgRV_Catagory_Id() {
        return gRV_Catagory_Id;
    }

    public void setgRV_Catagory_Id(Integer gRV_Catagory_Id) {
        this.gRV_Catagory_Id = gRV_Catagory_Id;
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

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getCidNo() {
        return cidNo;
    }

    public void setCidNo(String cidNo) {
        this.cidNo = cidNo;
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

    public Date getApp_Submission_Date() {
        return app_Submission_Date;
    }

    public void setApp_Submission_Date(Date app_Submission_Date) {
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


    public void setrequest_Service_Id(String request_Service_Id) {
        this.request_Service_Id = request_Service_Id;
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
}