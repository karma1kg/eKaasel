package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.UserLoginDTO;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.lib.ResponseMessage;
import bt.gov.voice.service.DeptService;
import bt.gov.voice.service.MinistryService;
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
 * Created by USER on 6/30/2021.
 */
/* ministry use*/
@Controller
@RequestMapping("/ministryLogin")
public class MinistryLogin {
    @Autowired
    private PublicService publicService;
    private CommonService commonService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
    private DeptService deptService;

    UserLoginDTO userLoginDTO = new UserLoginDTO();

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        /*Role currentRole = null;
Pmo_forward
        HttpSession session = request.getSession();
        String roleId = request.getParameter("roleId");
        UserRoleObj userRoleObj = new UserRoleObj();
        UserRolePrivilege dto = new UserRolePrivilege();
        userRoleObj.setRoleId(Integer.parseInt(dto.getCurrentRole().getRoleId()));
        userRoleObj.setRoleName(dto.getCurrentRole().getRoleName());
        currentRole = (currentRole) request.getSession().getAttribute("currentRole");*/
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs3 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs4 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = ministryService.getMyTaskList(user);
        BigInteger new_grievance=ministryService.new_grievance(user);
        workFlowDTOs1=ministryService.getMyTaskList1(user);
        request.setAttribute("delivered",workFlowDTOs1);
        BigInteger delivered_count=ministryService.delivered_count(user);
        request.setAttribute("delivered_count",delivered_count);
        request.setAttribute("myTasklist", workFlowDTOs);
        List<WorkFlowDTO> workFlowDTOs2 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=ministryService.closed(user);
        request.setAttribute("close",workFlowDTOs2);
        BigInteger closed=ministryService.closed_updates(user);
        request.setAttribute("closed",closed);
        BigInteger myforward=ministryService.myforward(user);
        BigInteger myreturn=ministryService.myreturn(user);
        BigInteger news=new_grievance.add(delivered_count);
        request.setAttribute("news",news);
        request.setAttribute("process",myforward.add(myreturn));
        BigInteger all_delivered=delivered_count.add(closed);
        request.setAttribute("all_delivered",all_delivered);
        List<WorkFlowDTO> workFlowDTOs23 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs23 = ministryService.delivered(user);
        model.addAttribute("deliveries",workFlowDTOs23);
        return "/ministry/ministryLogin";
    }


    @RequestMapping(value = "/taskList")
    public String getMyTaskList(ModelMap map, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTO1s = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = ministryService.getMyTaskList(user);
        workFlowDTO1s=ministryService.getMyTaskList1(user);
        request.setAttribute("myTasklist", workFlowDTOs);
        request.setAttribute("tasklists",workFlowDTO1s);
        return "ministry/ministry_tList";
    }

   /* @RequestMapping(value = "/taskList")
    public String getMytasks(ModelMap map, HttpServletRequest request) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = ministryService.getMytasks();
        request.setAttribute("tasklists", workFlowDTOs);
        return "ministry/ministry_tList";
    }*/
    @RequestMapping(value = "/tasklistDetails", method = RequestMethod.GET)
    public String taskDetails(ModelMap model, HttpServletRequest request) {
        Integer subjectIdByName = Integer.valueOf(request.getParameter("department_Id"));
        return "ministry/ministry_tDetails";
    }

    @RequestMapping(value = "/usergrievance")
    public String taskgrievance(ModelMap modelMap) {
        return "ministry/ministrygrievance";
    }

    @RequestMapping(value = "/closed", method = RequestMethod.GET)
    public String closed(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = ministryService.closed(user);
        model.addAttribute("close",workFlowDTOs);
        return "ministry/closed";
    }


    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String forward(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = ministryService.forward(user);
        model.addAttribute("process",workFlowDTOs);
        return "ministry/process";
    }

        @RequestMapping(value = "/delivered", method = RequestMethod.GET)
    public String delivered(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
       workFlowDTOs = ministryService.delivered(user);
        model.addAttribute("deliveries",workFlowDTOs);
        return "ministry/delivered";
    }

    @ResponseBody
    @RequestMapping(value = "/fetchDocuments", method = RequestMethod.GET)
    public List<Attachmentdto> fetchDocuments(HttpServletRequest request, ModelMap model){
        String appNo = request.getParameter("appNo");
        return ministryService.getDocumnets(appNo);
    }


    @RequestMapping(value = "/closed_submit", method = RequestMethod.POST)
    public String submitPMOClose(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fileName") MultipartFile[] files, ModelMap model) throws Exception {
        String stats="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setAppl_Close_Date(new Date());
        applicationDTO.setAction_date(new Date());
        if(applicationDTO.getMinistry_Remark().indexOf(',')!=-1){
            applicationDTO.setMinistry_Remark(applicationDTO.getMinistry_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Department_Ministry_Return")){
            applicationDTO.setPrevious_stats("Department_Ministry_Return");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Pmo_forward")){
            applicationDTO.setPrevious_stats("Pmo_forward");
        }
        stats=ministryService.closed_update(applicationDTO,files);

        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(stats.equalsIgnoreCase("1")){

        model.addAttribute("message", "You have closed the application successfully with the application number "+applicationDTO.getApplication_Number()+". Thank you.");
            String smsContent = "Dear user, "+
                    "Your application with your application number :"+ applicationDTO.getApplication_Number()+" is closed to Ministry on "+new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");

          /*  String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" is closed to Ministry on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/forward_submit", method = RequestMethod.POST)
    public String submitPMOForward(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("filler") MultipartFile[] files, ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setMinistry_Forward_Date(new Date());
        applicationDTO.setAction_date(new Date());
        String department_name="";
        department_name=deptService.getDepartment_name(applicationDTO.getDepartment_Id());
        applicationDTO.setDepartment_Name(department_name);
        if(applicationDTO.getMinistry_Remark().indexOf(',')!=-1){
            applicationDTO.setMinistry_Remark(applicationDTO.getMinistry_Remark().replace(',',' '));
        }

        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Department_Ministry_Return")){
            applicationDTO.setPrevious_stats("Department_Ministry_Return");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Pmo_forward")){
            applicationDTO.setPrevious_stats("Pmo_forward");
        }
        status=deptService.submit_update(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")) {
            model.addAttribute("message", "You have Forwarded this application to the Department "+applicationDTO.getDepartment_Name()+" and application number associated with it is "+applicationDTO.getApplication_Number()+". Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number "+ applicationDTO.getApplication_Number()+" has been forwarded to Department "+applicationDTO.getDepartment_Name()+ " on " +new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" has been forwarded to Department on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/mp_Returns", method = RequestMethod.POST)
    public String submit_ministry(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fillers") MultipartFile[] files, ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setAction_date(new Date());
        if(applicationDTO.getMinistry_Remark().indexOf(',')!=-1){
            applicationDTO.setMinistry_Remark(applicationDTO.getMinistry_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Pmo_forward")){
            applicationDTO.setPrevious_stats("Pmo_forward");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Department_Ministry_Return")){
            applicationDTO.setPrevious_stats("Department_Ministry_Return");
        }

        status=publicService.submit_this(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")) {
            model.addAttribute("message", "You have successfully returned this application to Prime Minister&apos;s Office and the application number associated with it is "+applicationDTO.getApplication_Number()+". Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been returned to Prime Minister's Office on "+new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");

           /* String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" has been returned to Prime Minister's Office on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }


  @RequestMapping(value = "/empty/getApplicationDetail", method = RequestMethod.GET)
    public String getApplicationDetail(HttpServletRequest request, ModelMap model) {
        String appNo = request.getParameter("appNo");
        List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
        model.addAttribute("departments", ministryService.getDepartments(request));
        applicationDTOs = ministryService.getTask(appNo);
        request.setAttribute("ClaimedLists", applicationDTOs);
      model.addAttribute("cid", applicationDTOs.get(0).getcID_Number());
        return "ministry/ministry_tDetails";
  }

    @RequestMapping(value="/grievance_status")
    public String check_status(ModelMap modelMap){
        return "ministry/mgrievance_status";
    }

    @ResponseBody
    @RequestMapping(value = "/view_status", method = RequestMethod.GET)
    public ResponseMessage view_status(HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        String appNo = request.getParameter("appNo");
        return ministryService.checkStatus(appNo);
    }

    @RequestMapping(value="/generate_report",  method = RequestMethod.GET)
    public String report(ModelMap modelMap, HttpServletRequest request){
        modelMap.addAttribute("ministries", publicService.getMinistriesReport(request));
        return "ministry/mReport";
    }
    @ResponseBody
    @RequestMapping(value = "/generate",  method = RequestMethod.GET)
    public ResponseMessage generate_report(ModelMap modelMap, HttpServletRequest request) {
        String select_cat=request.getParameter("select_cat");
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
        return ministryService.generate_report(select_cat,fromDate,toDate);
    }


    @RequestMapping(value="/donwloadFiles",method = RequestMethod.GET)
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

