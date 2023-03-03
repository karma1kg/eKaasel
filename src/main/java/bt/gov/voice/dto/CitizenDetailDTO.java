package bt.gov.voice.dto;

/**
 * Created by USER on 5/31/2021.
 */
public class CitizenDetailDTO {
    private String application_Number;
    private String cidNo;
    private String applicantDob;
    private String dzongkhagName;
    private String gewogName;
   /* private String mobileNo;*/
    private String firstName;
    private String middleName;
    private String lastName;
    private String villageName;

    public CitizenDetailDTO() {
    }

    public CitizenDetailDTO(String application_Number, String cidNo, String applicantDob, String dzongkhagName, String gewogName, String firstName, String middleName, String lastName, String villageName) {
        this.application_Number = application_Number;
        this.cidNo = cidNo;
        this.applicantDob = applicantDob;
        this.dzongkhagName = dzongkhagName;
        this.gewogName = gewogName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.villageName = villageName;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
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

 /*   public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }*/

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
}
