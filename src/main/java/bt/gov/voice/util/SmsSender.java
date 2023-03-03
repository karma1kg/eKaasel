package bt.gov.voice.util;

import com.sun.xml.fastinfoset.sax.Properties;

import java.io.File;
import java.util.ResourceBundle;

/**
 * Created by USER on 3/9/2020.
 */
public class SmsSender {

    private SMSUtil smsUtil = null;
    Properties properties = null;
    private static String SMS_URL = "";

    public static Boolean smsSender(String phoneNumber, String sentSMSFrom, String attachmentFile,String messageBody, String subject) throws Exception {
        boolean result = false;
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String smsurl =resourceBundle1.getString("smsNotificationURL");
      String trayMessage = "";
        try{
                SmsSender notification =new SmsSender();
            if(smsurl!=null)
                    //SMS_URL = smsurl;
                    if(!phoneNumber.equalsIgnoreCase("")){
                        (notification.smsUtil = new SMSUtil()).setMobileNo(phoneNumber);
                        // notification.smsUtil.setMobileNo(phoneNumber);
                       // notification.smsUtil.setSmsUrl(smsurl);
                       // notification.smsUtil.setSubject(subject);
                        notification.smsUtil.setSmsContent(messageBody);
                       // notification.smsSender.sentFrom(sentSMSFrom);
                        notification.smsUtil.sendSMS();
                        trayMessage = "SMS is sent successfully to "+ phoneNumber ;
                    }
            result = true;

        }
        catch(Exception ee){
            ee.printStackTrace();
        }
        final String finalTrayMessage = trayMessage;
        new Thread(){
            public void run(){
                try{
                   // Transport.send(message);
                    SystemTrayIcon.displayTray(finalTrayMessage);
                    System.out.print("#################----------------sms send successfully-----------###########33333");
                } catch(Exception e){
                    String errorMsg = "SMS not sent!!";
                    SystemTrayIcon.displayTray(errorMsg);
                    System.out.print(e+"###################3--------error msg here---------########################");
                }
            }
        }.start();

        return result;
    }
}
