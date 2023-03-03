package bt.gov.voice.controller;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.common.CommonService;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
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
/*
pm user
 */
@Controller
@RequestMapping("/pmLogin")
public class PmController {
    //declare region private interface
    @Autowired
    private PublicService publicService;
    private CommonService commonService;
    @Autowired
    private MinistryService ministryService;
    @Autowired
    private PmService pmService;
    String responseMessage;

    /**
     * pm dashboard
     * @param model
     * @param request
     * @return pm login
     */
    @RequestMapping(method = RequestMethod.GET)
    public String login(ModelMap model, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = pmService.getMyTaskList(user);
        List<WorkFlowDTO> workFlowDTOs1=new ArrayList<WorkFlowDTO>();
        workFlowDTOs1=pmService.closed_ones(user);
        request.setAttribute("closed_ones",workFlowDTOs1);
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=pmService.forwards(user);
        request.setAttribute("forwarded",workFlowDTOs2);
        BigInteger count=pmService.closed_updates(user);
        request.setAttribute("tasklist", workFlowDTOs);
        BigInteger count1=pmService.myforwards(user);
        BigInteger task=pmService.task(user);
        request.setAttribute("forwards",count1);
        request.setAttribute("closedme",count);
        request.setAttribute("called",count.add(count1));
        BigInteger summed=count.add(count1);
        request.setAttribute("task",summed);

        List<WorkFlowDTO> workFlowDTOs22=new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=pmService.delivered(user);
        request.setAttribute("delivered",workFlowDTOs22);
        return "pm/pmLogin";
    }

    /**
     * tasklist with user
     * @param map
     * @param request
     * @return task list
     */
    @RequestMapping(value = "/taskList")
    public String getMyTaskList(ModelMap map, HttpServletRequest request) {
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        List<WorkFlowDTO> workFlowDTOs2 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs = pmService.getMyTaskList(user);
        request.setAttribute("tasklist", workFlowDTOs);
        request.setAttribute("closed",workFlowDTOs1);
        return "pm/pm_tasklist";
    }

    /**
     * tasklist details
     * @param model
     * @param request
     * @return tasklist details
     */

    /*
     * refer pmo user for others for comments
     */
    @RequestMapping(value = "/tasklistDetails", method = RequestMethod.GET)
    public String taskDetails(ModelMap model, HttpServletRequest request) {
        Integer subjectIdByName = Integer.valueOf(request.getParameter("ministry_Id"));
        return "pm/pm_tdetails";
    }

    @RequestMapping(value = "/pmousergrievance")
    public String taskgrievance(ModelMap modelMap) {
        return "pm/pm_grievance";
    }
    @RequestMapping(value = "/closed", method = RequestMethod.GET)
    public String closedones(ModelMap modelMap, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs1 = new ArrayList<WorkFlowDTO>();
        workFlowDTOs1=pmService.closed_ones(user);
        request.setAttribute("closed_ones",workFlowDTOs1);
        return "pm/closed";
    }
    @RequestMapping(value = "/forwarded", method = RequestMethod.GET)
    public String forwarded(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=pmService.forwards(user);
        request.setAttribute("forwarded",workFlowDTOs2);
        return "pm/process";
    }

    @RequestMapping(value = "/delivered", method = RequestMethod.GET)
    public String delivered(Model model, HttpServletRequest request){
        UserRolePrivilege user=(UserRolePrivilege)request.getSession().getAttribute("UserLoginDTO");
        List<WorkFlowDTO> workFlowDTOs2=new ArrayList<WorkFlowDTO>();
        workFlowDTOs2=pmService.delivered(user);
        request.setAttribute("delivered",workFlowDTOs2);
        return "pm/delivered";
    }


    @RequestMapping(value = "/submitPMOClose",method = RequestMethod.POST)
    public String submitPMOClose(HttpServletRequest request, ApplicationDTO applicationDTO,@RequestParam("fileName") MultipartFile[] files, ModelMap model) throws Exception {
        String stats="";
        String document_type= request.getParameter("document_type");
        applicationDTO.setDocument_Type(document_type);
        applicationDTO.setAppl_Close_Date(new Date());
        applicationDTO.setAction_date(new Date());
        stats=pmService.closed_update(applicationDTO,files);
        if(files != null){
            publicService.saveDocument(files, applicationDTO.getApplication_Number(), applicationDTO.getDocument_Type());
        }
        if(stats.equalsIgnoreCase("1")){
            model.addAttribute("message", "You have successfulLy closed this application and the application number associated with it is "+applicationDTO.getApplication_Number()+ ".Thank you!");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ applicationDTO.getApplication_Number()+" is closed by Prime Minister on "+new Date()+ ".";
            SmsSender.smsSender(applicationDTO.getContact_No(), "", "", smsContent, "Successfully sent SMS to registered Phone Number.");
            /*String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ applicationDTO.getApplication_Number()+" is closed by Prime Minister on "+new Date()+ "Stay Home, Stay Safe!";
            if(!applicationDTO.getEmail_Id().isEmpty()) {
                MailSender.sendMail(applicationDTO.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully sent SMS to registered Email.");
            }*/
        }
        else{
            model.addAttribute("acknowledgement_Msg", "Failed, please try again!");
        }
        return "/ackMsg/acknowledgementMsg";
    }

    @RequestMapping(value = "/submitPMOForward",method = RequestMethod.POST)
    public String submitPMOForward(HttpServletRequest request,ApplicationDTO dto,@RequestParam("filler") MultipartFile[] files, ModelMap model) throws Exception {
        String status="";
        String document_type=request.getParameter("document_type");
        dto.setDocument_Type(document_type);
        dto.setAction_date(new Date());
        dto.setPrevious_stats("Pmo_forward_to_Pm");
        //status=ministryService.submit_update(dto,files);
        if(dto.getpM_Remark().indexOf(',')!=-1){
            dto.setpM_Remark(dto.getpM_Remark().replace(',',' '));
        }
        status=publicService.submit_update(dto,files);
        if(files != null){
            publicService.saveDocument(files, dto.getApplication_Number(), dto.getDocument_Type());
        }
        if(status.equalsIgnoreCase("Success")){
            model.addAttribute("message", "Your forward of this application of application number "+dto.getApplication_Number()+ " to Prime Minister's Office is successful! Thank you.");
            String smsContent = "Dear user, "+
                    "Thank you for your patience. Your application with your application number :"+ dto.getApplication_Number()+" has been returned to Prime Minister's Office on "+new Date()+ ".";
            SmsSender.smsSender(dto.getContact_No(), "", "", smsContent, "Successfully sent SMS to the Registered Phone Number.");
           /* String mailContent="Dear user,<br>"+
                    "Thank you for your patience. Your application with your : "+ dto.getApplication_Number()+"  has been returned to Prime Minister's Office on "+new Date()+ "Stay Home, Stay Safe!";
            if(!dto.getEmail_Id().isEmpty()) {
                MailSender.sendMail(dto.getEmail_Id(), "ekaasal@gov.bt", null, mailContent, "Successfully Sent SMS to the Registered Email.");
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
        model.addAttribute("ministry_list", pmService.getMinistries(request));
        applicationDTOs = pmService.getTask(appNo);
        request.setAttribute("ClaimedLists", applicationDTOs);
        model.addAttribute("cid", applicationDTOs.get(0).getcID_Number());
        return "pm/pm_tdetails";
    }
    @ResponseBody
    @RequestMapping(value = "/fetchDocuments", method = RequestMethod.GET)
    public List<Attachmentdto> fetchDocuments(HttpServletRequest request, ModelMap model){
        String appNo = request.getParameter("appNo");
        return pmService.getDocumnets(appNo);
    }

    @RequestMapping(value="/grievance_status",  method = RequestMethod.GET)
    public String check_status(HttpServletRequest request){
        return "pm/pm_status";
    }

    @ResponseBody
    @RequestMapping(value = "/view_status", method = RequestMethod.GET)
    public ResponseMessage view_status(HttpServletRequest request) {
        String appNo = request.getParameter("appNo");
        return pmService.checkStatus(appNo);
    }




    @RequestMapping(value="/generate_report",  method = RequestMethod.GET)
    public String report(ModelMap modelMap, HttpServletRequest request){
        modelMap.addAttribute("ministries", publicService.getMinistriesReport(request));
        return "pm/pm_report";
    }

    @ResponseBody
    @RequestMapping(value = "/generate",  method = RequestMethod.GET)
    public ResponseMessage generate_report(ModelMap modelMap, HttpServletRequest request) {
        String select_cat=request.getParameter("select_cat");
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
        return pmService.generate_report(select_cat,fromDate,toDate);
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
