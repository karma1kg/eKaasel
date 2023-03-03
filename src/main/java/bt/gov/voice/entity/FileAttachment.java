package bt.gov.voice.entity;

import javax.persistence.*;

/**
 * Created by USER on 6/21/2021.
 */
@Entity
@Table(name="t_document")
public class FileAttachment {
    @Id
    @Column(name = "Document_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer document_Id;

    @Column(name = "Application_Number")
    private String ApplicationNo;

    @Column(name = "Document_Type")
    private String DocumentType;

    @Column(name = "Document_Name")
    private String DocumentName;

    @Column(name = "Upload_URL")
    private String UploadUrl;

    @Column(name = "UUID")
    private String uUID;


    public Integer getDocument_Id() {
        return document_Id;
    }

    public void setDocument_Id(Integer document_Id) {
        this.document_Id = document_Id;
    }


    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

    public String getApplicationNo() {
        return ApplicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        ApplicationNo = applicationNo;
    }

    public String getDocumentType() {
        return DocumentType;
    }

    public void setDocumentType(String documentType) {
        DocumentType = documentType;
    }

    public String getDocumentName() {
        return DocumentName;
    }

    public void setDocumentName(String documentName) {
        DocumentName = documentName;
    }

    public String getUploadUrl() {
        return UploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        UploadUrl = uploadUrl;
    }


}
