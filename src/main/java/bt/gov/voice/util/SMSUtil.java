package bt.gov.voice.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

/**
 * Created by USER on 6/27/2020.
 */
public class SMSUtil implements Runnable {
    ResourceBundle bundle = ResourceBundle.getBundle("wsEndPointURL_en_US");
    private String smsUrl = bundle.getString("smsNotificationURL");
    private String mobileNo = "";
    private String smsContent = "";
    private String subject = "";
    private String sentFrom = "";
    boolean result = false;
    public static final String SMS_ENCONDING_TYPE = "UTF-8";
    public static final String URL_MIDDLE_PART = "&msg=";

    public boolean sendSMS(){
        Thread thread = new Thread(this);
        thread.start();
        return result;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo the mobileNo to set
     */
    public SMSUtil setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return null;
    }

    /**
     * @return the smsUrl
     */
    public String getSmsUrl() {
        return smsUrl;
    }

    /**
     * @param smsUrl the smsUrl to set
     */
    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }
    /**
     * @return the smsContent
     */
    public String getSmsContent() {
        return smsContent;
    }

    /**
     * @param smsContent the smsContent to set
     */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void sentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setSentFrom(String sentFrom) {
        this.sentFrom = sentFrom;
    }

    @Override
    public void run() {

        String encodedMobileNo = "";
        String encodedSMScontent = "";
        String fullURLStr = "";
        URL url =null;
        HttpURLConnection connection = null;
        String responseMSg = "";
        if(smsUrl!="" && mobileNo!="" && smsContent!=""){
            try{
                encodedMobileNo=URLEncoder.encode(mobileNo, SMS_ENCONDING_TYPE);
                encodedSMScontent=URLEncoder.encode(smsContent, SMS_ENCONDING_TYPE);
                fullURLStr = smsUrl + mobileNo+ URL_MIDDLE_PART +subject +smsContent;
                fullURLStr= fullURLStr.replaceAll(" ","%20");
                url = new URL(fullURLStr);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(false);
                connection.setDoInput(true);
                responseMSg = connection.getResponseMessage();

                int code = connection.getResponseCode() ;
                if (code == HttpURLConnection.HTTP_OK){
                    connection.disconnect() ;
                    result = true;
                }
            }
            catch(Exception e){
            }
        }
    }
}
