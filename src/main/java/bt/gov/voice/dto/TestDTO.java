package bt.gov.voice.dto;

import java.io.Serializable;

/**
 * Created by USER on 5/27/2021.*/



public class TestDTO implements Serializable {
    private Integer gri_id;
    private String cid;


    public TestDTO() {
    }

    public TestDTO(Integer gri_id, String cid) {
        this.gri_id = gri_id;
        this.cid = cid;
    }

    public Integer getGri_id() {
        return gri_id;
    }

    public void setGri_id(Integer gri_id) {
        this.gri_id = gri_id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
