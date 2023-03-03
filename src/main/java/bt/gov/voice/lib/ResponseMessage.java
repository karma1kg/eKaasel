package bt.gov.voice.lib;

/**
 * Created by USER on 6/23/2021.
 */
public class ResponseMessage {
    private Integer status;
    private String text;
    private Object dto;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getDto() {
        return dto;
    }

    public void setDto(Object dto) {
        this.dto = dto;
    }

}
