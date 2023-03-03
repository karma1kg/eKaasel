package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.lib.ResponseMessage;
import bt.gov.voice.service.DeptService;
import bt.gov.voice.service.DivService;
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
 * Created by USER on 7/6/2021.
 */
/* department user */
@Controller
@RequestMapping("/dept_login")
public class DepartmentController {
    @Autowired
    private PublicService publicService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
    private DivService divService;
    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1=new ArrayList<WorkFlowDTO>();
        workFlowDTOs = deptService.getMyTaskList(user);
        BigInteger news=deptService.news(user);
        workFlowDTOs1=deptService.getMyTaskList1(user);
        List<WorkFlowDTO> workFlowDTOss = new ArrayList<WorkFlowDTO>();
        workFlowDTOss = deptService.closed(user);
        model.addAttribute("close",workFlowDTOss);
        BigInteger returned_true=deptService.getTaskList1(user);
        request.setAttribute("returned_true",returned_true);
        BigInteger newer=news.add(returned_true);
        request.setAttribute("newer",newer);
        List<WorkFlowDTO> workFlowDTOs3=new ArrayList<WorkFlowDTO>();
        BigInteger closed=deptService.myclosed_submit(user);
        List<WorkFlowDTO> workFlowDTOs4=new ArrayList<WorkFlowDTO>();
        BigInteger myforwards=deptService.myforwards(user);
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        BigInteger myreturns=deptService.myreturns(user);
        request.setAttribute("myreturns",myreturns);
        request.setAttribute("rejected",closed);
        request.setAttribute("myforwards",myforwards);
        request.setAttribute("myTasklist", workFlowDTOs);
        request.setAttribute("returned",workFlowDTOs1);
        BigInteger process=myforwards.add(myreturns);
        BigInteger delivered_all=process.add(returned_true.add(closed));
        request.setAttribute("process",process);
        request.setAttribute("delivered_all",delivered_all);
        List<WorkFlowDTO> workFlowDTOsc = new ArrayList<WorkFlowDTO>();
        workFlowDTOsc = deptService.closed(user);
        model.addAttribute("close",workFlowDTOsc);
        return "/department/dept_login";
    }

    @RequestMapping(value = "/taskList")
    public String getMyTaskList(ModelMap map, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1=new ArrayList<WorkFlowDTO>();
        workFlowDTOs = deptService.getMyTaskList(user);
        request.setAttribute("myTasklist", workFlowDTOs);
        workFlowDTOs1=deptService.getMyTaskList1(user);
        request.setAttribute("returned",workFlowDTOs1);
        BigInteger returned_true=deptService.getTaskList1(user);
        request.setAttribute("returned_true",returned_true);
        List<WorkFlowDTO> workFlowDTOs3=new ArrayList<WorkFlowDTO>();
        BigInteger closed=deptService.myclosed_submit(user);
        List<WorkFlowDTO> workFlowDTOs4=new ArrayList<WorkFlowDTO>();
        BigInteger myforwards=deptService.myforwards(user);
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        BigInteger myreturns=deptService.myreturns(user);
        request.setAttribute("myreturns",myreturns);
        request.setAttribute("rejected",closed);
        request.setAttribute("myforwards",myforwards);
        request.setAttribute("myTasklist", workFlowDTOs);
        request.setAttribute("returned",workFlowDTOs1);
        BigInteger process=myforwards.add(myreturns);
        BigInteger delivered_all=process.add(returned_true.add(closed));
        request.setAttribute("process",process);
        request.setAttribute("delivered_all",delivered_all);
        return "department/dept_tLists";
    }

    @RequestMapping(value = "/tasklistDetails", method = RequestMethod.GET)
    public String taskDetails(ModelMap model, HttpServletRequest request) {
        Integer subjectIdByName = Integer.valueOf(request.getParameter("ministry_Id"));
        Integer subjectIdByNamed = Integer.valueOf(request.getParameter("dept_Division_Id"));
        return "department/dept_tDetails";
     }



    @RequestMapping(value = "/usergrievance")
    public String taskgrievance(ModelMap modelMap) {
        return "department/dept_grievance";
    }

    @ResponseBody
    @RequestMapping(value = "/fetchDocuments", method = RequestMethod.GET)
    public List<Attachmentdto> fetchDocuments(HttpServletRequest request, ModelMap model){
        String appNo = request.getParameter("appNo");
        return deptService.getDocumnets(appNo);
    }

    @RequestMapping(value = "/closed_submit", method = RequestMethod.POST)
    public String submitPMOClose(HttpServletRequest request,ApplicationDTO applicationDTO,@RequestParam("fileName") MultipartFile[] files,  ModelMap model) throws Exception {
        String s="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setAppl_Close_Date(new Date());
        applicationDTO.setAction_date(new Date());
        if(applicationDTO.getDept_Remark().indexOf(',')!=-1){
            applicationDTO.setDept_Remark(applicationDTO.getDept_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Division_Department_forward")){
            applicationDTO.setPrevious_stats("Division_Department_forward");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_Department_forward")) {
            applicationDTO.setPrevious_stats("Ministry_Department_forward");
        }
        s=deptService.closed_submit(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(s.equalsIgnoreCase("1")){
            model.addAttribute("message", "You have closed this application successfully with the application number of "+applicationDTO.getApplication_Number()+". Thank you..");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been closed by Department on "+new Date()+ ".";
            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");

            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  has been closed by Department on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/saved_forward", method = RequestMethod.POST)
    public String submitPMOForward(HttpServletRequest request,ApplicationDTO applicationDTO, @RequestParam("filler") MultipartFile[] files,  ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setDept_Action_Date(new Date());
        applicationDTO.setAction_date(new Date());
        String ministry_name="";
        ministry_name=ministryService.getMinistryName(applicationDTO.getMinistry_Id());
        applicationDTO.setMinistryName(ministry_name);
        if(applicationDTO.getDept_Remark().indexOf(',')!=-1){
            applicationDTO.setDept_Remark(applicationDTO.getDept_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Division_Department_forward")){
            applicationDTO.setPrevious_stats("Division_Department_forward");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_Department_forward")) {
            applicationDTO.setPrevious_stats("Ministry_Department_forward");
        }
        status=ministryService.saved_forward(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")) {
            model.addAttribute("message", "You have successfully returned this application to "+applicationDTO.getMinistryName()+" and the application number associated with it is "+applicationDTO.getApplication_Number()+". Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been returned to "+applicationDTO.getMinistryName()+" on "+new Date()+ ".";
            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
           /* String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" has been returned to Ministry on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/

        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/forward_submits",method = RequestMethod.POST)
    public String submitPMOForwards(HttpServletRequest request,ApplicationDTO applicationDTO, @RequestParam("fillers") MultipartFile[] files, ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setDept_Forward_Date(new Date());
        applicationDTO.setAction_date(new Date());
        String division_name="";
        division_name=divService.getDivision_name(applicationDTO.getDept_Division_Id());
        applicationDTO.setDept_Division(division_name);
        if(applicationDTO.getDept_Remark().indexOf(',')!=-1){
            applicationDTO.setDept_Remark(applicationDTO.getDept_Remark().replace(',',' '));
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Division_Department_forward")){
            applicationDTO.setPrevious_stats("Division_Department_forward");
        }
        if(applicationDTO.getCurrent_status().equalsIgnoreCase("Ministry_Department_forward")) {
            applicationDTO.setPrevious_stats("Ministry_Department_forward");
        }
        status=divService.saved_forward(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")) {
            model.addAttribute("message", "You have successfully forwarded this application with the application number "+applicationDTO.getApplication_Number()+" to Division, "+applicationDTO.getDept_Division_Id()+" Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been forwarded to Division, " +applicationDTO.getDept_Division()+ " on " +new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" has been forwarded to Division on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to the Registered Email.");
            }*/
        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/empty/getApplicationDetail", method = RequestMethod.GET)
    public String getApplicationDetail(HttpServletRequest request, ModelMap model) {
        String appNo = request.getParameter("appNo");
        List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
        model.addAttribute("ministry_list", deptService.getMinistries(request));
        model.addAttribute("division_list", deptService.getDivisions(request));
        applicationDTOs = deptService.getTask(appNo);
        request.setAttribute("ClaimedLists", applicationDTOs);
        model.addAttribute("cid", applicationDTOs.get(0).getcID_Number());
        return "department/dept_tDetails";
    }

    @RequestMapping(value="/grievance_status")
    public String check_status(ModelMap modelMap){
        return "department/dept_status";
    }
    @ResponseBody
    @RequestMapping(value = "/view_status")
    public ResponseMessage view_status(HttpServletRequest request) {
        String appNo = request.getParameter("appNo");
        return deptService.checkStatus(appNo);
    }
    @RequestMapping(value="/generate_report",  method = RequestMethod.GET)
    public String report(ModelMap modelMap, HttpServletRequest request){
        modelMap.addAttribute("ministries", publicService.getMinistriesReport(request));
        return "department/dReport";
    }

    @ResponseBody
    @RequestMapping(value = "/generate",  method = RequestMethod.GET)
    public ResponseMessage generate_report(ModelMap modelMap, HttpServletRequest request) {
        String select_cat=request.getParameter("select_cat");
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
        return deptService.generate_report(select_cat,fromDate,toDate);
    }
    @RequestMapping(value = "/closed", method = RequestMethod.GET)
    public String closed(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = deptService.closed(user);
        model.addAttribute("close",workFlowDTOs);
        return "department/closed";
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String forward(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = deptService.forward(user);
        model.addAttribute("process",workFlowDTOs);
        return "department/process";
    }

    @RequestMapping(value = "/delivered", method = RequestMethod.GET)
    public String delivered(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = deptService.delivered(user);
        model.addAttribute("delivered",workFlowDTOs);
        return "department/delivered";
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
