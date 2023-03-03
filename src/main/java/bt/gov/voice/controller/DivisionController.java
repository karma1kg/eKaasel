package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.lib.ResponseMessage;
import bt.gov.voice.service.DeptService;
import bt.gov.voice.service.DivService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* division user*/
@Controller
@RequestMapping("/divLogin")
public class DivisionController {
    @Autowired
    private PublicService publicService;
    @Autowired
     private DivService divService;
    @Autowired
    private DeptService deptService;

    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=divService.myreturns();
        workFlowDTOs = divService.getMyTaskList();
        workFlowDTOs1=divService.divclosed();
        request.setAttribute("myTasklist", workFlowDTOs);
            request.setAttribute("closed",workFlowDTOs1);
        request.setAttribute("returns",workFlowDTOs2);
        List<WorkFlowDTO> workFlowDTOss = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = divService.closed(user);
        model.addAttribute("close",workFlowDTOss);
       /* List<WorkFlowDTO> workFlowDTOs3 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs3 = divService.forward(user);
        model.addAttribute("process",workFlowDTOs3);*/
        return "/division/divLogin";
    }

    @RequestMapping(value = "/taskList")
    public String getMyTaskList(ModelMap map, HttpServletRequest request) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = divService.getMyTaskList();
        request.setAttribute("myTasklist", workFlowDTOs);
        return "division/div_tasklist";
    }

    @RequestMapping(value = "/tasklistDetails", method = RequestMethod.GET)
    public String taskDetails(ModelMap model, HttpServletRequest request) {
       Integer subjectIdByName = Integer.valueOf(request.getParameter("department_Id"));
        return "division/div_tdetails";
    }

    @RequestMapping(value = "/usergrievance")
    public String taskgrievance(ModelMap modelMap) {
        return "division/div_grievance";
    }

    @RequestMapping(value = "/closed", method = RequestMethod.GET)
    public String closed(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = divService.closed(user);
        model.addAttribute("close",workFlowDTOs);
        return "division/closed";
    }

    @RequestMapping(value = "/process", method = RequestMethod.GET)
    public String forward(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = divService.forward(user);
        model.addAttribute("process",workFlowDTOs);
        return "division/process";
    }

    @RequestMapping(value = "/delivered", method = RequestMethod.GET)
    public String delivered(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = divService.delivered(user);
        model.addAttribute("delivered",workFlowDTOs);
        return "division/delivered";
    }

    @RequestMapping(value = "/closed_submit", method = RequestMethod.POST)
    public String submitPMOClose(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("filling") MultipartFile[] files, ModelMap model) throws Exception {
        String stats="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setAppl_Close_Date(new Date());
        applicationDTO.setAction_date(new Date());
        if(applicationDTO.getDivision_Remark().indexOf(',')!=-1){
            applicationDTO.setDivision_Remark(applicationDTO.getDivision_Remark().replace(',',' '));
        }
        stats=divService.closed_update(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(stats.equalsIgnoreCase("1")){
            model.addAttribute("message", "You have closed this application with the application number "+applicationDTO.getApplication_Number()+". Thank you!");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been closed to Division on "+new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  has been closed by Division on "+new Date()+ "Stay Home, Stay Safe!";
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
    public String submitPMOForward(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fillers") MultipartFile[] files, ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setDivision_Forward_Date(new Date());
        applicationDTO.setAction_date(new Date());
        String department_name="";
        department_name=deptService.getDepartment_name(applicationDTO.getDepartment_Id());
        applicationDTO.setDepartment_Name(department_name);
        if(applicationDTO.getDivision_Remark().indexOf(',')!=-1){
            applicationDTO.setDivision_Remark(applicationDTO.getDivision_Remark().replace(',',' '));
        }
        status=deptService.forward_submit(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")) {
            model.addAttribute("message", "You have returned this application with the application number "+applicationDTO.getApplication_Number()+" successfully to "+applicationDTO.getDepartment_Name()+ ". Thank you. ");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" has been returned to "+applicationDTO.getDepartment_Name()+ " on " +new Date()+ ".";

            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
           /* String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+"  has been returned to Department on "+new Date()+ "Stay Home, Stay Safe!";
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
        model.addAttribute("departments", divService.getDepartments(request));
        applicationDTOs = divService.getTask(appNo);
        request.setAttribute("ClaimedLists", applicationDTOs);
        model.addAttribute("cid", applicationDTOs.get(0).getcID_Number());
        return "division/div_tdetails";
    }

    @RequestMapping(value="/grievance_status")
    public String check_status(ModelMap modelMap){
        return "division/div_status";
    }

    @ResponseBody
    @RequestMapping(value = "/view_status")
    public ResponseMessage view_status(HttpServletRequest request) {
        String appNo = request.getParameter("appNo");
        return divService.checkStatus(appNo);
    }
    @RequestMapping(value="/generate_report",  method = RequestMethod.GET)
    public String report(ModelMap modelMap,HttpServletRequest request){
        modelMap.addAttribute("ministries", publicService.getMinistriesReport(request));
        return "division/div_report";
    }

    @ResponseBody
    @RequestMapping(value = "/generate",  method = RequestMethod.GET)
    public ResponseMessage generate_report(ModelMap modelMap, HttpServletRequest request) {
        String select_cat=request.getParameter("select_cat");
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
        return divService.generate_report(select_cat,fromDate,toDate);
    }

    @ResponseBody
    @RequestMapping(value = "/fetchDocuments", method = RequestMethod.GET)
    public List<Attachmentdto> fetchDocuments(HttpServletRequest request, ModelMap model){
        String appNo = request.getParameter("appNo");
        return divService.getDocumnets(appNo);
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
