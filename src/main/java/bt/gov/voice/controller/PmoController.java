package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.Role;
import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.UserLoginDTO;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.lib.ResponseMessage;
import bt.gov.voice.service.MinistryService;
import bt.gov.voice.service.PmService;
import bt.gov.voice.service.PublicService;
import bt.gov.voice.util.SmsSender;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by USER on 6/23/2021.
 */

/**
 * pmo user
 */
@Controller
@RequestMapping("/pmoLogin")
public class PmoController {
    //region private interface
    @Autowired
    private PublicService publicService;
    private CommonService commonService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
     private PmService pmService;
    Role currentRole = null;
    UserLoginDTO userLoginDTO = new UserLoginDTO();
    String uid = "";

    /**
     * pmo dashboard
     * @param model
     * @param request
     * @return page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTO2s = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs3 = new ArrayList<WorkFlowDTO>();
        WorkFlowDTO workFlowDTOs4=new WorkFlowDTO();
        WorkFlowDTO workFlowDTOs5=new WorkFlowDTO();
        workFlowDTOs = publicService.getMyTaskList(user);
        workFlowDTOs1=publicService.getMyTaskList1(user);
        workFlowDTO2s=publicService.getMyTaskList2(user);
        request.setAttribute("myTasklist", workFlowDTOs);
        request.setAttribute("tasklists",workFlowDTOs1);
        request.setAttribute("tasklists1", workFlowDTO2s);
        workFlowDTOs3=publicService.closed_update(user);
        request.setAttribute("rejected", workFlowDTOs3);
        BigInteger count =publicService.submitme(user);
        request.setAttribute("ministry_forward", count);
        BigInteger count1=publicService.submitime(user);
        request.setAttribute("pm_forward",count1);
        request.setAttribute("count_me",count.add(count1));
        BigInteger new_grievance=publicService.new_grievance(user);
        BigInteger pm_returns=publicService.pm_returns(user);
        BigInteger ministry_returns=publicService.ministry_returns(user);
      BigInteger my_news=new_grievance.add(pm_returns.add(ministry_returns));
        request.setAttribute("my_news",my_news);
        BigInteger rejects=publicService.rejects(user);
        request.setAttribute("rejects",rejects);
        BigInteger all_delivered=pm_returns.add(ministry_returns.add(rejects));
        request.setAttribute("all_delivered",all_delivered);
        List<WorkFlowDTO> workFlowDTOs33 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs33=publicService.closed(user);
        request.setAttribute("closed",workFlowDTOs33);
        List<WorkFlowDTO> workFlowDTO3d=new ArrayList<WorkFlowDTO>();
        workFlowDTOs=publicService.delivered(user);
        request.setAttribute("delivered",workFlowDTO3d);
        List<WorkFlowDTO> workFlowDTOsp=new ArrayList<WorkFlowDTO>();
        workFlowDTOs=publicService.tasklist(user);
        request.setAttribute("process",workFlowDTOsp);
        return "application/pmoLogin";
    }

    /**
     * tasklist method
     * @param map
     * @param request
     * @return tasklist
     */
    @RequestMapping(value = "/taskList")
    public String getMyTaskList(ModelMap map, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTO2s = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs3 = new ArrayList<WorkFlowDTO>();
       // List<WorkFlowDTO> workFlowDTOs4 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = publicService.getMyTaskList(user);
        workFlowDTOs1=publicService.getMyTaskList1(user);
        workFlowDTO2s=publicService.getMyTaskList2(user);
        request.setAttribute("myTasklist", workFlowDTOs);
        request.setAttribute("tasklists",workFlowDTOs1);
        request.setAttribute("tasklists1", workFlowDTO2s);
        BigInteger count =publicService.submitme(user);
        request.setAttribute("ministry_forward", count);
        BigInteger count1=publicService.submitime(user);
        request.setAttribute("pm_forward",count1);
        request.setAttribute("count_me",count.add(count1));
        return "application/taskList";
    }

    /**
     * view task list details
     * @param model
     * @param request
     * @return tasklist details
     */
    @RequestMapping(value = "/tasklistDetails", method = RequestMethod.GET)
    public String taskDetails(ModelMap model, HttpServletRequest request) {
        Integer subjectIdByName = Integer.valueOf(request.getParameter("ministry_Id"));
        return "application/tasklistdetails";
    }

    /**
     * pmo grievance
     * @param modelMap
     * @return page
     */
    @RequestMapping(value = "/pmousergrievance")
    public String taskgrievance(ModelMap modelMap) {
        return "application/pmousergrievance";
    }


    @RequestMapping(value = "/grievance_status")
    public String g_status(ModelMap modelMap) {
        return "application/grievance_status";
    }

    /**
     *  view status
     * @param request
     * @return status
     */
    @ResponseBody
    @RequestMapping(value = "/view_status", method = RequestMethod.GET)
    public ResponseMessage view_status(HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        String appNo = request.getParameter("appNo");
        return publicService.checkStatus(appNo);
    }

    /**
     * get ministry name with ministry id
     * @param modelMap
     * @param request
     * @return reports
     */
    @RequestMapping(value = "/generate_report", method = RequestMethod.GET)
    public String generate_reports(ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("ministries", publicService.getMinistriesReport(request));
        return "application/generate_report";
    }

    /**
     * get report with start date, end date and ministry
     * @param modelMap
     * @param request
     * @return report
     */
    @ResponseBody
    @RequestMapping(value = "/generate",  method = RequestMethod.GET)
    public ResponseMessage generate_report(ModelMap modelMap, HttpServletRequest request) {
        String select_cat=request.getParameter("select_cat");
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
            return publicService.generate_report(select_cat,fromDate,toDate);
    }

    /**
     * request attachments with application no.
     * @param request
     * @param model
     * @return attachments
     */
    @ResponseBody
    @RequestMapping(value = "/fetchDocuments", method = RequestMethod.GET)
    public List<Attachmentdto> fetchDocuments(HttpServletRequest request, ModelMap model){
        String appNo = request.getParameter("appNo");
        return publicService.getDocumnets(appNo);
    }

    /**
     * applications closed
     * @param model
     * @param request
     * @return applications
     */
@RequestMapping(value = "/closed", method = RequestMethod.GET)
public String closed(Model model, HttpServletRequest request){
    UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
    List<WorkFlowDTO> workFlowDTOs3 = new ArrayList<WorkFlowDTO>();
    workFlowDTOs3=publicService.closed(user);
    request.setAttribute("closed",workFlowDTOs3);
    return "application/closed";
}

    /**
     * applications delivered
     * @param model
     * @param request
     * @return applications
     */
    @RequestMapping(value = "/delivered", method = RequestMethod.GET)
    public String deliverd(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs=new ArrayList<WorkFlowDTO>();
        workFlowDTOs=publicService.delivered(user);
        request.setAttribute("delivered",workFlowDTOs);
        return "application/delivered";
    }

    /**
     * applications in process
     * @param model
     * @param request
     * @return applications
     */
    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String process(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs=new ArrayList<WorkFlowDTO>();
        workFlowDTOs=publicService.tasklist(user);
        request.setAttribute("process",workFlowDTOs);
        return "application/process";
    }

    /**
     * close application by pmo
     * @param request
     * @param applicationDTO
     * @param files
     * @param model
     * @return closed message
     * @throws Exception
     */
  @RequestMapping(value = "/submitPMOClose",method = RequestMethod.POST)
    public String submitPMOClose(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fileName") MultipartFile[] files, ModelMap model) throws Exception{
     String stats="";
      String document_type = request.getParameter("document_type");
      applicationDTO.setDocument_Type(document_type);
      applicationDTO.setAppl_Close_Date(new Date());
      applicationDTO.setAction_date(new Date());
      if(applicationDTO.getCurrent_status().equalsIgnoreCase("SUBMITTED")) {
          applicationDTO.setPrevious_stats("SUBMITTED");
      }
      if(applicationDTO.getCurrent_status().equalsIgnoreCase("PM_Return_PMO")){
          applicationDTO.setPrevious_stats("PM_Return_PMO");
      }
      if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_PMO_Returns")){
          applicationDTO.setPrevious_stats("Ministry_PMO_Returns");
      }
      stats=publicService.closed_update(applicationDTO,files);
      //stats=publicService.closed_update(applicationDTO,files);
      if(files != null){
          publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
      }
      if(stats.equalsIgnoreCase("1")){
          model.addAttribute("message", "You have successfully closed this application with application Number "+ applicationDTO.getApplication_Number()+". Thank you.");
          String smsContent = "Dear user, "+
                  "Your application with "+ applicationDTO.getApplication_Number()+"  is Closed by Prime Minister's Office on "+new Date()+ ".";
          SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
         /* String mailContent="Dear user,<br>"+
                  "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  is Closed by Prime Minister's Office on "+new Date()+ "Stay Home, Stay Safe!";
          if(!applicationDTO.getEmail_Id().isEmpty()) {
              MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
          }*/
      }
      else{
          model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
      }
        return "/ackMsg/acknowledgementMsg";
    }

    /**
     * forward by pmo
     * @param request
     * @param applicationDTO
     * @param files
     * @param model
     * @return forwarded message
     * @throws Exception
     */
    @RequestMapping(value = "/submitPMOForward", method = RequestMethod.POST)
    public String submitPMOForward(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fil") MultipartFile[] files,  ModelMap model) throws Exception{
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        String stats="";
        String document_type = request.getParameter("document_type");
        String Ministry_Name="";
        Ministry_Name=ministryService.getMinistryName(applicationDTO.getMinistry_Id());
      applicationDTO.setMinistryName(Ministry_Name);
        model.addAttribute("ministtry_names",applicationDTO);
        if(applicationDTO.getpMO_Remark().startsWith(",")){
            applicationDTO.setpMO_Remark("");
        }
        applicationDTO.setpMO_Forward_Date(new Date());
        applicationDTO.setAction_date(new Date());
        applicationDTO.setDocument_Type(document_type);
        if(applicationDTO.getpMO_Remark().indexOf(",") != -1){
            applicationDTO.setpMO_Remark(applicationDTO.getpMO_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("SUBMITTED")) {
            applicationDTO.setPrevious_stats("SUBMITTED");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("PM_Return_PMO")){
            applicationDTO.setPrevious_stats("PM_Return_PMO");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_PMO_Returns")){
            applicationDTO.setPrevious_stats("Ministry_PMO_Returns");
        }

        stats=ministryService.updateApplicationDetails(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(stats.equalsIgnoreCase("Success")){
            model.addAttribute("message", "You have successfully forwarded this application to "+ applicationDTO.getMinistryName()+" with application Number "+ applicationDTO.getApplication_Number()+"");
            //model.addAttribute("acknowledgement_Msg", "Your forward to Ministry is successful!.Thank you");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your Application Number "+ applicationDTO.getApplication_Number()+" has been forwarded to "+applicationDTO.getMinistryName()+" on " +new Date()+".";
            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  has been forwarded to Prime Minister for further redressal on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    /**
     * submit by pmo
     * @param request
     * @param applicationDTO
     * @param files
     * @param model
     * @return saved message
     * @throws Exception
     */
    @RequestMapping(value = "/pmo_submit", method = RequestMethod.POST)
    public String pmo_submit(HttpServletRequest request, ApplicationDTO applicationDTO,  @RequestParam("filler") MultipartFile[] files,ModelMap model) throws Exception {
        String pm_status="";
        String document_Type = request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_Type);
        applicationDTO.setAction_date(new Date());
        if(applicationDTO.getpMO_Remark().indexOf(",") != -1){
            applicationDTO.setpMO_Remark(applicationDTO.getpMO_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("SUBMITTED")) {
            applicationDTO.setPrevious_stats("SUBMITTED");
        }
         if(applicationDTO.getCurrent_status().equalsIgnoreCase("PM_Return_PMO")){
            applicationDTO.setPrevious_stats("PM_Return_PMO");
        }
          if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_PMO_Returns")){
            applicationDTO.setPrevious_stats("Ministry_PMO_Returns");
        }

        pm_status=pmService.pmo_forward(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(pm_status.equalsIgnoreCase("Success")){
            model.addAttribute("message", "You have forwarded this application to Prime Minister with application Number "+ applicationDTO.getApplication_Number()+" successfully. Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number "+ applicationDTO.getApplication_Number()+" has been forwarded to Prime Minister for further redressal on "+new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");

            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  has been forwarded to Prime Minister for further redressal on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    /**
     * get application details with app no.
     * @param request
     * @param model
     * @return application details
     */
    @RequestMapping(value = "/empty/getApplicationDetail", method = RequestMethod.GET)
    public String getApplicationDetail(HttpServletRequest request, ModelMap model) {
        String appNo = request.getParameter("appNo");
        List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
        model.addAttribute("ministries", publicService.getMinistries(request));
        applicationDTOs = publicService.getTask(appNo);
        request.setAttribute("ClaimedLists", applicationDTOs);
        model.addAttribute("cid", applicationDTOs.get(0).getcID_Number());
        return "application/tasklistdetails";
    }

    /**
     * download file method
     * @param request
     * @param response
     * @param model
     * @return downloaded file
     */
    @RequestMapping(value="/donwloadFiles", method = RequestMethod.GET)
    public String donwloadFiles(HttpServletRequest request,HttpServletResponse response,ModelMap model) {
        String uploadDocId = request.getParameter("uuid"),requesttype="view";
        try{
            Attachmentdto doc = publicService.getDocumentDetailsByDocId(uploadDocId);
            byte[] fileContent = downloadFile(doc.getUploadUrl());
            if(requesttype.equalsIgnoreCase("view")){
                if(doc.getFilename().substring(doc.getFilename().length()-3).equalsIgnoreCase("JPG")||doc.getFilename().substring(doc.getFilename().length()-4).equalsIgnoreCase("jpeg") || doc.getFilename().substring(doc.getFilename().length()-3).equalsIgnoreCase("png")){
                    response.setContentType("image/jpeg");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getFilename().substring(doc.getFilename().length()-3).equalsIgnoreCase("pdf")){
                    response.setContentType("APPLICATION/PDF");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getFilename().substring(doc.getFilename().length()-4).equalsIgnoreCase("docx")){
                    response.reset();
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    response.setHeader("Content-Disposition", "inline;filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getFilename().substring(doc.getFilename().length()-3).equalsIgnoreCase("xls")){
                    response.setContentType("APPLICATION/vnd.ms-excel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else if(doc.getFilename().substring(doc.getFilename().length()-4).equalsIgnoreCase("xlsx")){
                    response.setContentType("Application/x-msexcel");
                    response.setHeader("Content-disposition", "inline; filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
                else{
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-disposition", "attachment; filename="+doc.getFilename());
                    response.getOutputStream().write(fileContent);
                    response.getOutputStream().flush();
                    response.getOutputStream().close();
                }
            }
            else{
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename="+doc.getFilename());
                response.getOutputStream().write(fileContent);
                response.getOutputStream().flush();
                response.getOutputStream().close();
            }
        }catch (Exception e){
            System.out.print(e);
            return  ""+e;
        }
        return null;
    }
    public static byte[] downloadFile(String uploadUlr) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(uploadUlr);
        return IOUtils.toByteArray(fileInputStream);
    }
}