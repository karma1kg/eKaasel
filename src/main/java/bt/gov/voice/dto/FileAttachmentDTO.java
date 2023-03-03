package bt.gov.voice.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by USER on 6/17/2021.
 */
public class FileAttachmentDTO implements Serializable {
    private int document_Id;
    private String application_Number;
    private String document_Type;
    private String document_Name;
    private String upload_URL;
    private String uUID;
    /*private MultipartFile attachedFile;*/

    public FileAttachmentDTO() {
    }

    public FileAttachmentDTO(int document_Id, String application_Number, String document_Type, String document_Name, String upload_URL, String uUID, MultipartFile attachedFile) {
        this.document_Id = document_Id;
        this.application_Number = application_Number;
        this.document_Type = document_Type;
        this.document_Name = document_Name;
        this.upload_URL = upload_URL;
        this.uUID = uUID;
    }

    public int getDocument_Id() {
        return document_Id;
    }

    public void setDocument_Id(int document_Id) {
        this.document_Id = document_Id;
    }

    public String getApplication_Number() {
        return application_Number;
    }

    public void setApplication_Number(String application_Number) {
        this.application_Number = application_Number;
    }

    public String getDocument_Type() {
        return document_Type;
    }

    public void setDocument_Type(String document_Type) {
        this.document_Type = document_Type;
    }

    public String getDocument_Name() {
        return document_Name;
    }

    public void setDocument_Name(String document_Name) {
        this.document_Name = document_Name;
    }

    public String getUpload_URL() {
        return upload_URL;
    }

    public void setUpload_URL(String upload_URL) {
        this.upload_URL = upload_URL;
    }

    public String getuUID() {
        return uUID;
    }

    public void setuUID(String uUID) {
        this.uUID = uUID;
    }

}
