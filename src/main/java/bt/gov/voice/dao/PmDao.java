package bt.gov.voice.dao;

import bt.gov.g2c.framework.common.vo.UserRolePrivilege;
import bt.gov.voice.base.baseDao;
import bt.gov.voice.dto.ApplicationDTO;
import bt.gov.voice.dto.Attachmentdto;
import bt.gov.voice.dto.MinistryDTO;
import bt.gov.voice.dto.WorkFlowDTO;
import bt.gov.voice.entity.ApplicationEntity;
import bt.gov.voice.entity.WorkFlowEntity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 7/5/2021.
 */
@Repository
public class PmDao extends baseDao {

    public List<MinistryDTO> getMinistries(HttpServletRequest request) {
        List<MinistryDTO> ministryDTOs = new ArrayList<MinistryDTO>();
        try {
            String sqlQuery = properties.getProperty("publicDao.getMinistries");
            org.hibernate.Query hQuery = hibernateQuery(sqlQuery, MinistryDTO.class);
            ministryDTOs = hQuery.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ministryDTOs;
    }

    public String saveData(ApplicationEntity applicationEntity) {
        String result = "Failed";
        try {
            saveOrUpdate(applicationEntity);
            result="Success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveUpdate(Object object){

        saveOrUpdate(object);
    }

    public void saveUpdate(WorkFlowEntity workFlowEntity) {
        return;
    }

    public List<WorkFlowDTO> getMyTaskList(UserRolePrivilege user) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        try {
            String sql = "SELECT \n" +
                    "  a.application_number, \n" +
                    "  a.current_status,\n" +
                    "  a.record_date, \n" +
                    "  a.action_trail \n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    "WHERE a.assigned_role=?\n"+
                    " AND a.current_status='Pmo_forward_to_Pm'";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1, user.getCurrentRole().getRoleId());
            workFlowDTOs = hquery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }

    /*@Transactional(rollbackOn = Exception.class)*/
    public List<ApplicationDTO> getTaskList(String appNo) {
        List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
        try {
            String sql = "SELECT \n" +
                    "  a.Application_Number AS application_Number, \n" +
                    "  a.CID_Number AS cID_Number,\n" +
                    "  a.First_Name AS first_Name,\n" +
                    "  a.Middle_Name AS middle_Name,\n" +
                    "  a.Last_Name AS last_Name,\n" +
                    "  a.dzongkhag AS dzongkhag,\n" +
                    "  a.gewog AS gewog ,\n" +
                    "  a.village AS village ,\n" +
                    "  a.Contact_No AS contact_No,\n" +
                    "  a.Email_Id AS email_Id ,\n" +
                    "  a.Appl_Type_Remark AS appl_Type_Remark, \n" +
                    "  a.PM_Remark AS pM_Remark, \n" +
                    "   DATE_FORMAT(a.App_Submission_Date, '%d/%m/%Y') AS app_Submission_Date, \n" +
                    "  a.PMO_Remark AS pMO_Remark,\n" +
                    "  a.Ministry_Remark AS ministry_Remark,\n" +
                    "  a.Dept_Remark AS dept_Remark,\n" +
                    "  a.Division_Remark AS division_Remark,\n" +
                    "  v.current_status current_status, \n" +
                    "  v.previous_stats previous_stats \n" +
                    "FROM\n" +
                    "  t_grv_application a, \n"+
                    "   t_voc_workflow v\n"+
                    "WHERE a.application_Number=? \n"+
                    "AND v.application_number = a.Application_Number";

            Query hquery = hibernateQuery(sql, ApplicationDTO.class).setParameter(1,appNo);
            applicationDTOs = hquery.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return applicationDTOs;
    }
    /* @Transactional(readOnly=true)*/
    public ApplicationDTO getApplicantDetail(String application_no) {
        String query = properties.getProperty("CommonDao.getApplicantDetail");
        Query hQuery = hibernateQuery(query, ApplicationDTO.class);
        hQuery.setParameter("applicationNo", application_no);
        return (ApplicationDTO) hQuery.uniqueResult();
    }

    public Attachmentdto getDocumentDetailsByDocId(String uploadDocId) {
        Attachmentdto dto=new Attachmentdto();
        try {
            String sql = "SELECT d.Document_Name filename,d.Upload_URL uploadUrl,d.UUID uuid FROM t_document d WHERE d.UUID=?";
            Query hquery = hibernateQuery(sql, Attachmentdto.class).setParameter(1,uploadDocId);
            dto = (Attachmentdto) hquery.list().get(0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return dto;
    }

    public String closed_update(ApplicationDTO applicationDTO) {
        int close=0;
        try{
            org.hibernate.query.Query WORKFLOW = sqlQuery("INSERT INTO voicedb.t_voc_workflow_audit\n" +
                    "            (workflow_id,\n" +
                    "             application_number,\n" +
                    "             previous_stats,\n" +
                    "             current_status,\n" +
                    "             assigned_user,\n" +
                    "             assigned_role,\n" +
                    "             assigned_ministry,\n" +
                    "             assigned_dept,\n" +
                    "             assigned_secretary,\n" +
                    "             action_date,\n" +
                    "             action_trail,\n" +
                    "             record_date,\n" +
                    "             appl_Close_Remark)\n" +
                    "SELECT\n" +
                    "  workflow_id,\n" +
                    "  application_number,\n" +
                    "  previous_stats,\n" +
                    "  current_status,\n" +
                    "  assigned_user,\n" +
                    "  assigned_role,\n" +
                    "  assigned_ministry,\n" +
                    "  assigned_dept,\n" +
                    "  assigned_secretary,\n" +
                    "  action_date,\n" +
                    "  action_trail,\n" +
                    "  record_date,\n" +
                    "  appl_Close_Remark\n" +
                    "FROM voicedb.t_voc_workflow\n" +
                    "WHERE application_number=?\n");
            WORKFLOW.setParameter(1, applicationDTO.getApplication_Number());
            WORKFLOW.executeUpdate();
            String sql="UPDATE t_voc_workflow a SET a.current_status='Pm_Closed', a.previous_stats='Pmo_forward_to_Pm', a.Appl_Close_Remark=?, a.assigned_role=4003, a.action_date=?, a.action_trail='Closed by PM' WHERE a.application_number=?";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1, applicationDTO.getAppl_Close_Remark()).setParameter(2,applicationDTO.getAction_date()).setParameter(3, applicationDTO.getApplication_Number());
            close=hquery.executeUpdate();
            if(close>0){
                org.hibernate.query.Query application_audit = sqlQuery("INSERT INTO voicedb.t_grv_application_audit\n" +
                        "            (Application_Number,\n" +
                        "             Request_Service_Id,\n" +
                        "             G2C_Service_Tag,\n" +
                        "             GRV_Catagory_Id,\n" +
                        "             Application_Type,\n" +
                        "             G2C_Application_No,\n" +
                        "             CID_Number,\n" +
                        "             First_Name,\n" +
                        "             Middle_Name,\n" +
                        "             Last_Name,\n" +
                        "             Contact_No,\n" +
                        "             dzongkhag,\n" +
                        "             gewog,\n" +
                        "             village,\n" +
                        "             Appl_Type_Remark,\n" +
                        "             Legal_Action_Tag,\n" +
                        "             Expt_Date_Recv_Service,\n" +
                        "             App_Submission_Date,\n" +
                        "             PMO_Remark,\n" +
                        "             PMO_Forward_Date,\n" +
                        "             PM_Remark,\n" +
                        "             Ministry_Remark,\n" +
                        "             Ministry_Forward_Date,\n" +
                        "             Secretary_Remark,\n" +
                        "             Secretary_Forward_Date,\n" +
                        "             Dept_Remark,\n" +
                        "             Dept_Action_Date,\n" +
                        "             Division_Remark,\n" +
                        "             Division_Forward_Date,\n" +
                        "             Appl_Close_Tag,\n" +
                        "             Appl_Close_Date,\n" +
                        "             Appl_Close_Remark,\n" +
                        "             Service_Fee,\n" +
                        "             Application_Fee,\n" +
                        "             Closed_By_Type,\n" +
                        "             Closed_By,\n" +
                        "             Ministry_Id,\n" +
                        "             Department_Id,\n" +
                        "             action_date)\n" +
                        "SELECT\n" +
                        "            Application_Number,\n" +
                        "             Request_Service_Id,\n" +
                        "             G2C_Service_Tag,\n" +
                        "             GRV_Catagory_Id,\n" +
                        "             Application_Type,\n" +
                        "             G2C_Application_No,\n" +
                        "             CID_Number,\n" +
                        "             First_Name,\n" +
                        "             Middle_Name,\n" +
                        "             Last_Name,\n" +
                        "             Contact_No,\n" +
                        "             dzongkhag,\n" +
                        "             gewog,\n" +
                        "             village,\n" +
                        "             Appl_Type_Remark,\n" +
                        "             Legal_Action_Tag,\n" +
                        "             Expt_Date_Recv_Service,\n" +
                        "             App_Submission_Date,\n" +
                        "             PMO_Remark,\n" +
                        "             PMO_Forward_Date,\n" +
                        "             PM_Remark,\n" +
                        "             Ministry_Remark,\n" +
                        "             Ministry_Forward_Date,\n" +
                        "             Secretary_Remark,\n" +
                        "             Secretary_Forward_Date,\n" +
                        "             Dept_Remark,\n" +
                        "             Dept_Action_Date,\n" +
                        "             Division_Remark,\n" +
                        "             Division_Forward_Date,\n" +
                        "             Appl_Close_Tag,\n" +
                        "             Appl_Close_Date,\n" +
                        "             Appl_Close_Remark,\n" +
                        "             Service_Fee,\n" +
                        "             Application_Fee,\n" +
                        "             Closed_By_Type,\n" +
                        "             Closed_By,\n" +
                        "             Ministry_Id,\n" +
                        "             Department_Id,\n" +
                        "             action_date\n"+
                        "FROM voicedb.t_grv_application\n" +
                        "WHERE application_number=?\n");
                application_audit.setParameter(1, applicationDTO.getApplication_Number());
                application_audit.executeUpdate();
                String sqll="UPDATE t_grv_application a SET  a.Closed_By='PM',  a.action_date=?,a.Appl_CLose_Date=?, a.Appl_Close_Remark=? WHERE a.application_number=?";
                Query hquery1 = hibernateQuery(sqll, ApplicationDTO.class).setParameter(1,applicationDTO.getAction_date()).setParameter(2, applicationDTO.getAppl_Close_Date()).setParameter(3,applicationDTO.getAppl_Close_Remark()).setParameter(4, applicationDTO.getApplication_Number());
                close=hquery1.executeUpdate();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if(close>0) {
            return "1";
        } else{
            return "0";
        }
    }

    public String updateApplicationDetails(ApplicationDTO dto) {
        int save=0; int update_t_voc_workflow=0; int saveMinistry=0;
        String returnStatus="Failed";
        try {
            String sql = "UPDATE t_voc_workflow a SET a.current_status='Pmo_forward', a.previous_stats='SUBMITTED', a.assigned_ministry=?, a.assigned_role=? WHERE a.application_number=?";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1, dto.getMinistry_Id()).setParameter(2,dto.getAssigned_role()).setParameter(3,dto.getApplication_Number());
            update_t_voc_workflow=hquery.executeUpdate();
            if (update_t_voc_workflow >0){
                String sql1 = "UPDATE t_grv_application a SET a.PM_Remark=? WHERE a.application_number=?";
                Query hquery2 = hibernateQuery(sql1, ApplicationDTO.class).setParameter(1,dto.getpM_Remark()).setParameter(2, dto.getApplication_Number());
                saveMinistry=hquery2.executeUpdate();
                returnStatus = "Success";
            }
         /*   else if(save2>0){
                String sq="UPDATE t_grv_application a SET a.PM_Remark=? WHERE a.application_number=?";
                Query hquery2 = hibernateQuery(sq, ApplicationDTO.class).setParameter(1,dto.getpM_Remark()).setParameter(2, dto.getApplication_Number());
                save=hquery2.executeUpdate();
            }*/

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return returnStatus;
    }

    public String getRemark(String appNo) {
        return null;
    }

    public String pmo_forward(ApplicationDTO applicationDTO) {
        int save=0; int update_t_voc_workflow=0;
        String returnStatus="Failed";
        try {
            org.hibernate.query.Query WORKFLOW = sqlQuery("INSERT INTO voicedb.t_voc_workflow_audit\n" +
                    "            (workflow_id,\n" +
                    "             application_number,\n" +
                    "             previous_stats,\n" +
                    "             current_status,\n" +
                    "             assigned_user,\n" +
                    "             assigned_role,\n" +
                    "             assigned_ministry,\n" +
                    "             assigned_dept,\n" +
                    "             assigned_secretary,\n" +
                    "             action_date,\n" +
                    "             action_trail,\n" +
                    "             record_date,\n" +
                    "             appl_Close_Remark)\n" +
                    "SELECT\n" +
                    "  workflow_id,\n" +
                    "  application_number,\n" +
                    "  previous_stats,\n" +
                    "  current_status,\n" +
                    "  assigned_user,\n" +
                    "  assigned_role,\n" +
                    "  assigned_ministry,\n" +
                    "  assigned_dept,\n" +
                    "  assigned_secretary,\n" +
                    "  action_date,\n" +
                    "  action_trail,\n" +
                    "  record_date,\n" +
                    "  appl_Close_Remark\n" +
                    "FROM voicedb.t_voc_workflow\n" +
                    "WHERE application_number=?\n");
            WORKFLOW.setParameter(1, applicationDTO.getApplication_Number());
            WORKFLOW.executeUpdate();

            String sql = "UPDATE t_voc_workflow a SET a.current_status='Pmo_forward_to_Pm', a.previous_stats=?, a.action_date=?, a.assigned_role=4003,a.action_trail='Forwarded To Pm' WHERE a.application_number=?";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1,applicationDTO.getPrevious_stats()).setParameter(2, applicationDTO.getAction_date()).setParameter(3, applicationDTO.getApplication_Number());
            update_t_voc_workflow=hquery.executeUpdate();
            if (update_t_voc_workflow >0){
                String sql1 = "UPDATE t_grv_application a SET a.PMO_Remark=?,  a.action_date=? WHERE a.application_number=?";
                Query hquery2 = hibernateQuery(sql1, ApplicationDTO.class).setParameter(1,applicationDTO.getpMO_Remark()).setParameter(2,applicationDTO.getAction_date()).setParameter(3, applicationDTO.getApplication_Number());
                update_t_voc_workflow=hquery2.executeUpdate();
                returnStatus = "Success";
            }
         /*   else if(save2>0){
                String sq="UPDATE t_grv_application a SET a.PM_Remark=? WHERE a.application_number=?";
                Query hquery2 = hibernateQuery(sq, ApplicationDTO.class).setParameter(1,dto.getpM_Remark()).setParameter(2, dto.getApplication_Number());
                save=hquery2.executeUpdate();
            }*/
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return returnStatus;
    }

    public List<WorkFlowDTO> checkStatus(String appNo) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        try {
            String sql = "SELECT DISTINCT\n" +
                    "  a.Application_Number AS application_number, \n" +
                    "  a.action_trail AS action_trail, \n" +
                    "  a.current_status AS current_status, \n" +
                    "  a.previous_stats AS previous_stats, \n" +
                    "  a.appl_Close_Remark AS appl_Close_Remark, \n" +
                    "  DATE_FORMAT(a.action_date, '%d/%m/%Y') AS action_date \n" +
                    "FROM\n" +
                    "t_voc_workflow a\n"+
                    "WHERE a.application_number=?\n"+
                    "UNION ALL\n"+
                    "SELECT DISTINCT\n" +
                    "  b.Application_Number AS application_number, \n" +
                    "  b.action_trail AS action_trail, \n" +
                    "  b.current_status AS current_status, \n" +
                    "  b.previous_stats AS previous_stats, \n" +
                    "  b.appl_Close_Remark AS appl_Close_Remark, \n" +
                    "  DATE_FORMAT(b.action_date, '%d/%m/%Y') AS action_date \n"+
                    "FROM\n"+
                    "t_voc_workflow_audit b\n"+
                    "WHERE b.application_number=?";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1,appNo).setParameter(2,appNo);
            workFlowDTOs = hquery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }

    public List<Attachmentdto> getDocumnets(String appNo) {
        try{
            String query = properties.getProperty("publicDao.getDocumnets");
            Query hQuery = hibernateQuery(query, Attachmentdto.class);
            hQuery.setParameter(1, appNo);
            return hQuery.list();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public BigInteger closed_updates(UserRolePrivilege user) {
        WorkFlowDTO workFlowDTOs = new WorkFlowDTO();
        BigInteger count = null;
        try {
            String sql = "SELECT \n" +
                    "  COUNT(*) \n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    " WHERE a.assigned_role=? \n" +
                    " AND a.current_status='Pm_Closed'";
            Query hquery = hibernateQuery(sql).setParameter(1,user.getCurrentRole().getRoleId());
            count =  (BigInteger) hquery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public BigInteger myforwards(UserRolePrivilege user) {
        WorkFlowDTO workFlowDTOs = new WorkFlowDTO();
        BigInteger count = null;
        try {
            String sql = "SELECT \n" +
                    "  COUNT(*)\n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    "WHERE a.current_status='PM_Return_PMO'";
            Query hquery = hibernateQuery(sql);
            count =(BigInteger) hquery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public BigInteger task(UserRolePrivilege user) {
        WorkFlowDTO workFlowDTOs = new WorkFlowDTO();
        BigInteger count = null;
        try {
            String sql = "SELECT \n" +
                    "  COUNT(*)\n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    "  WHERE  a.current_status='Pmo_forward_to_Pm'";
            Query hquery = hibernateQuery(sql);
            count =(BigInteger) hquery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public List<WorkFlowDTO> closed_ones(UserRolePrivilege user) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        try {
            String sql = "SELECT \n" +
                    "  a.application_number, \n" +
                    "  a.current_status,\n" +
                    "  a.record_date, \n" +
                    "  a.action_trail \n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    "WHERE a.assigned_role=?\n"+
                    " AND a.current_status='Pm_Closed'";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1, user.getCurrentRole().getRoleId());
            workFlowDTOs = hquery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }

    public List<WorkFlowDTO> forwards(UserRolePrivilege user) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        try {
            String sql = "SELECT \n" +
                    "  a.application_number, \n" +
                    "  a.current_status,\n" +
                    "  a.record_date, \n" +
                    "  a.action_trail \n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    " WHERE a.current_status='PM_Return_PMO'";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class);
            workFlowDTOs = hquery.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }

    public List<WorkFlowDTO> delivered(UserRolePrivilege user) {
        List<WorkFlowDTO> workFlowDTOs = new ArrayList<WorkFlowDTO>();
        try {
            String sql = "SELECT \n" +
                    "  a.application_number, \n" +
                    "  a.current_status,\n" +
                    "  a.record_date, \n" +
                    "  a.action_trail \n" +
                    "FROM\n" +
                    "  t_voc_workflow a\n" +
                    " WHERE a.current_status IN ('PM_Return_PMO','Pm_Closed')";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class);
            workFlowDTOs = hquery.list();

        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }

    public List<ApplicationDTO> generate_report(String select_cat, String fromDate, String toDate) {
        List<ApplicationDTO> applicationDTOs = new ArrayList<ApplicationDTO>();
        if(select_cat.equalsIgnoreCase("22")) {
            try {
                String sql = "SELECT\n" +
                        "  a.Application_Number AS application_Number, \n" +
                        "  DATE_FORMAT(a.App_Submission_Date, '%d/%m/%Y') AS app_Submission_Date, \n" +
                        "  a.Appl_Type_Remark AS appl_Type_Remark, \n" +
                        "  a.PMO_Remark AS pMO_Remark, \n" +
                        "  a.PM_Remark AS pM_Remark, \n" +
                        "  a.Ministry_Remark AS ministry_Remark, \n" +
                        "  a.Dept_Remark AS dept_Remark, \n" +
                        "  a.Division_Remark AS division_Remark, \n" +
                        "  a.Appl_Close_Remark AS appl_Close_Remark\n" +
                        "FROM\n" +
                        "t_grv_application a\n" +
                        // "WHERE a.Ministry_Id IN('1','2','3','4','6','7','8','9','10','11','21','22')\n"+
                        "WHERE a.App_Submission_Date BETWEEN ? AND ?\n";
                Query hquery1 = hibernateQuery(sql, ApplicationDTO.class).setParameter(1, fromDate).setParameter(2, toDate);
                applicationDTOs = hquery1.list();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        else{
            try {
                String sqll = "SELECT\n" +
                        "  a.Application_Number AS application_Number, \n" +
                        "  DATE_FORMAT(a.App_Submission_Date, '%d/%m/%Y') AS app_Submission_Date, \n" +
                        "  a.Appl_Type_Remark AS appl_Type_Remark, \n" +
                        "  a.PMO_Remark AS pMO_Remark, \n" +
                        "  a.PM_Remark AS pM_Remark, \n" +
                        "  a.Ministry_Remark AS ministry_Remark, \n" +
                        "  a.Dept_Remark AS dept_Remark, \n" +
                        "  a.Division_Remark AS division_Remark, \n" +
                        "  a.Appl_Close_Remark AS appl_Close_Remark\n" +
                        "FROM\n" +
                        "t_grv_application a\n" +
                        "WHERE a.Ministry_Id=?\n"+
                        "AND a.App_Submission_Date BETWEEN ? AND ?\n";
                Query hquery = hibernateQuery(sqll, ApplicationDTO.class).setParameter(1,select_cat).setParameter(2, fromDate).setParameter(3, toDate);
                applicationDTOs = hquery.list();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return applicationDTOs;
    }

 /*   public WorkFlowDTO checkstats(String appNo) {
        WorkFlowDTO workFlowDTOs = new WorkFlowDTO();
        try {
            String sql = "SELECT \n" +
                    "  a.application_number AS application_number, \n" +
                    "  a.current_status AS current_status, \n" +
                    "  a.record_date AS record_date \n" +
                    "FROM\n" +
                    "  t_voc_workflow a \n"+
                    "WHERE a.application_number=?\n";
            Query hquery = hibernateQuery(sql, WorkFlowDTO.class).setParameter(1,appNo);
            workFlowDTOs = (WorkFlowDTO) hquery.list().get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return workFlowDTOs;
    }*/
}
