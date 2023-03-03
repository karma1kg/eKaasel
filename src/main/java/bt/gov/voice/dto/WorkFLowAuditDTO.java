package bt.gov.voice.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by USER on 8/3/2021.
 */
public class WorkFLowAuditDTO implements Serializable {
private int workflow_audit_id;
    private int workflow_id;
    private String application_number;
    private String previous_stats;
    private String current_status;
    private String assigned_user;
    private String assigned_role;
    private String assigned_ministry;
    private String assigned_dept;
    private String assigned_secretary;
    private Date action_date;
    private String action_trail;
    private Date record_date;
    private String appl_Close_Remark;

    public WorkFLowAuditDTO() {
    }

    public WorkFLowAuditDTO(int workflow_audit_id, int workflow_id, String application_number, String previous_stats, String current_status, String assigned_user, String assigned_role, String assigned_ministry, String assigned_dept, String assigned_secretary, Date action_date, String action_trail, Date record_date, String appl_Close_Remark) {
        this.workflow_audit_id = workflow_audit_id;
        this.workflow_id = workflow_id;
        this.application_number = application_number;
        this.previous_stats = previous_stats;
        this.current_status = current_status;
        this.assigned_user = assigned_user;
        this.assigned_role = assigned_role;
        this.assigned_ministry = assigned_ministry;
        this.assigned_dept = assigned_dept;
        this.assigned_secretary = assigned_secretary;
        this.action_date = action_date;
        this.action_trail = action_trail;
        this.record_date = record_date;
        this.appl_Close_Remark = appl_Close_Remark;
    }

    public int getWorkflow_audit_id() {
        return workflow_audit_id;
    }

    public void setWorkflow_audit_id(int workflow_audit_id) {
        this.workflow_audit_id = workflow_audit_id;
    }

    public int getWorkflow_id() {
        return workflow_id;
    }

    public void setWorkflow_id(int workflow_id) {
        this.workflow_id = workflow_id;
    }

    public String getApplication_number() {
        return application_number;
    }

    public void setApplication_number(String application_number) {
        this.application_number = application_number;
    }

    public String getPrevious_stats() {
        return previous_stats;
    }

    public void setPrevious_stats(String previous_stats) {
        this.previous_stats = previous_stats;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }

    public String getAssigned_user() {
        return assigned_user;
    }

    public void setAssigned_user(String assigned_user) {
        this.assigned_user = assigned_user;
    }

    public String getAssigned_role() {
        return assigned_role;
    }

    public void setAssigned_role(String assigned_role) {
        this.assigned_role = assigned_role;
    }

    public String getAssigned_ministry() {
        return assigned_ministry;
    }

    public void setAssigned_ministry(String assigned_ministry) {
        this.assigned_ministry = assigned_ministry;
    }

    public String getAssigned_dept() {
        return assigned_dept;
    }

    public void setAssigned_dept(String assigned_dept) {
        this.assigned_dept = assigned_dept;
    }

    public String getAssigned_secretary() {
        return assigned_secretary;
    }

    public void setAssigned_secretary(String assigned_secretary) {
        this.assigned_secretary = assigned_secretary;
    }

    public Date getAction_date() {
        return action_date;
    }

    public void setAction_date(Date action_date) {
        this.action_date = action_date;
    }

    public String getAction_trail() {
        return action_trail;
    }

    public void setAction_trail(String action_trail) {
        this.action_trail = action_trail;
    }

    public Date getRecord_date() {
        return record_date;
    }

    public void setRecord_date(Date record_date) {
        this.record_date = record_date;
    }

    public String getAppl_Close_Remark() {
        return appl_Close_Remark;
    }

    public void setAppl_Close_Remark(String appl_Close_Remark) {
        this.appl_Close_Remark = appl_Close_Remark;
    }
}
