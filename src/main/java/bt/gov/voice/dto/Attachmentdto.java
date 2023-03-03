package bt.gov.voice.dto;

/**
 * Created by Tshewang on 10/23/2019.
 */
public class Attachmentdto {
    private String filename;
    private String userfilename;
    private String uuid;
    private String uploadUrl;
    private String documentType;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUserfilename() {
        return userfilename;
    }

    public void setUserfilename(String userfilename) {
        this.userfilename = userfilename;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }
}
