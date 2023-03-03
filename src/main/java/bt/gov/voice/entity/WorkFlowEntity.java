package bt.gov.voice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Karma Gayleg on 6/22/2021.
 */
@Entity
@Table(name="t_voc_workflow")
public class WorkFlowEntity {
    @Id
    @Column(name = "workflow_id")
    private int workflow_id;
    @Column(name = "application_number")
    private String application_number;
    @Column(name = "previous_stats")
    private String previous_stats;
    @Column(name ="current_status")
    private String current_status;
    @Column(name = "assigned_user")
    private String assigned_user;
    @Column(name = "assigned_role")
    private String assigned_role;
    @Column(name = "assigned_ministry")
    private String assigned_ministry;
    @Column(name = "assigned_dept")
    private String assigned_dept;
    @Column(name = "assigned_secretary")
    private String assigned_secretary;
    @Column(name = "action_date")
    private Date action_date;
    @Column(name = "action_trail")
    private  String action_trail;
    @Column(name = "record_date")
    private Date record_date;
    @Column(name = "App_Submission_Date")
    private Date app_Submission_Date;
    @Column(name = "Appl_Close_Remark")
    private String appl_Close_Remark;

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

    public Date getApp_Submission_Date() {
        return app_Submission_Date;
    }

    public void setApp_Submission_Date(Date app_Submission_Date) {
        this.app_Submission_Date = app_Submission_Date;
    }

    public String getAppl_Close_Remark() {
        return appl_Close_Remark;
    }

    public void setAppl_Close_Remark(String appl_Close_Remark) {
        this.appl_Close_Remark = appl_Close_Remark;
    }
}
