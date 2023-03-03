package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.CitizenDetailDTO;
import bt.gov.voice.lib.ResponseMessage;
import bt.gov.voice.service.PublicService;
import bt.gov.voice.util.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/*Created by USER on 5/26/2021.*/

/**
 * for public controller, maps index as url
 */
@Controller
@RequestMapping("/index")
public class PublicController {
    //declare private variables
    @Autowired
    private PublicService publicService;

   /*
   returns pge
    */
 @RequestMapping( method = {RequestMethod.GET, RequestMethod.POST})
    public String publicVoice(ModelMap model) {
        return "index";
    }

    /**
     * to generate application no.
     * @param request
     * @return application no.
     */
    @RequestMapping(value="/generateApplicationNumber",method = RequestMethod.POST)
    public String generateApplicationNumber(HttpServletRequest request){
    String ServiceId = request.getParameter("");
    String ApplicationType = request.getParameter("");
    Integer Service_Id = Integer.parseInt(ServiceId);
    String generateApplicationNumber = publicService.generateApplicationNumber(Service_Id);
        return generateApplicationNumber;
    }

    /**
     * to save data from form
     * @param applicationDTO
     * @param citizenDetailDTO
     * @param request
     * @param files
     * @param model
     * @return message
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveData(ApplicationDTO applicationDTO,CitizenDetailDTO citizenDetailDTO, HttpServletRequest request, @RequestParam("fileName") MultipartFile[] files , ModelMap model) throws Exception {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        String ServiceId = "400";
        String ApplicationType = "S";
        ResponseMessage responseMessage = new ResponseMessage();
        String document_type = request.getParameter("document_type");
        Integer Service_Id = Integer.parseInt(ServiceId);
        applicationDTO.setDocument_Type(document_type);
        String generateApplicationNumber = publicService.generateApplicationNumber(Service_Id);
        if(generateApplicationNumber != null) {
            applicationDTO.setCidNo(citizenDetailDTO.getCidNo());
            applicationDTO.setFirstName(citizenDetailDTO.getFirstName());
            if(citizenDetailDTO.getMiddleName()==null&&citizenDetailDTO.getMiddleName().equalsIgnoreCase("null")){
                applicationDTO.setMiddle_Name(citizenDetailDTO.getMiddleName().replaceAll(null,""));
            }
            if(citizenDetailDTO.getMiddleName()!=null||!(citizenDetailDTO.getMiddleName().equalsIgnoreCase("null"))){
                applicationDTO.setMiddleName(citizenDetailDTO.getMiddleName());
            }
            if(citizenDetailDTO.getLastName()==null||citizenDetailDTO.getLastName().equalsIgnoreCase("null")){
                applicationDTO.setLast_Name(citizenDetailDTO.getLastName().replaceAll(null,""));
            }
            if(citizenDetailDTO.getLastName()!=null&&!citizenDetailDTO.getLastName().equalsIgnoreCase("null")){
                applicationDTO.setLast_Name(citizenDetailDTO.getLastName());
            }
            //applicationDTO.setMiddleName(citizenDetailDTO.getMiddleName());
            //applicationDTO.setLastName(citizenDetailDTO.getLastName());
            applicationDTO.setDzongkhagName(citizenDetailDTO.getDzongkhagName());
            applicationDTO.setVillageName(citizenDetailDTO.getVillageName());
            applicationDTO.setGewogName(citizenDetailDTO.getGewogName());
            applicationDTO.setContact_No(applicationDTO.getContact_No());
            applicationDTO.setApplication_Number(generateApplicationNumber);
            model.addAttribute("cid", applicationDTO.getcID_Number());
          publicService.saveData(applicationDTO,ServiceId, ApplicationType, files,user);
            if(files != null){
                publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());}
            model.addAttribute("acknowledgement_Msg","Your submission will be assessed by the division and submitted to the concerned agency for further redressal. Your Grievance reference number is " +
                    "" + generateApplicationNumber);
            model.addAttribute("message",". Your submission will be communicated within 5 working days.");

            String smsContent = "Dear user, "+
                    "Your submission will be assessed by the division and submitted to the concerned agency for further redressal. Your Grievance reference number is : "+ generateApplicationNumber+" submitted  on "+new Date()+ ". Your submission will be communicated within 5 working days.";
/*String mailContent="Dear user,<br>"+
        "Your submission will be assessed by the division and submitted to the concerned agency for further redressal. Your Grievance reference number is : "+ generateApplicationNumber+" submitted on "+new Date()+ ". Your submission will be communicated within 5 working days.";
           if(!applicationDTO.getEmail_Id().isEmpty()) {
               MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Application Registered Success");
           }*/
            System.out.print(applicationDTO.getContact_No());
            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Application Registered Success");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    /**
     * view of status by public
     * @param request application number
     * @return status of application
     */
    @ResponseBody
    @RequestMapping(value = "/view_status", method = RequestMethod.GET)
    public ResponseMessage view_status(HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        String appNo = request.getParameter("appNo");
        System.out.print(appNo);
        return publicService.checkStatus(appNo);
    }

    /**
     * get citizen details
     * @param request cid
     * @return citizen details
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCitizenDetails", method = RequestMethod.GET)
    public CitizenDetailDTO getAllCitizenDetails(HttpServletRequest request){
        String cid = request.getParameter("cid");
        CitizenDetailDTO citizenDetailDTO = publicService.getAllCitizenDetails(cid);
        return citizenDetailDTO;
    }
}

