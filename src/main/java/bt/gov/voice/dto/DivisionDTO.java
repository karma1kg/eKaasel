package bt.gov.voice.dto;

import java.io.Serializable;

/**
 * Created by USER on 7/15/2021.
 */
public class DivisionDTO implements Serializable {
    private Integer dept_Division_Id;
    private String dept_Division;
    private Integer department_id;

    public DivisionDTO() {
    }

    public DivisionDTO(Integer dept_Division_Id, String dept_Division, Integer department_id) {
        this.dept_Division_Id = dept_Division_Id;
        this.dept_Division = dept_Division;
        this.department_id = department_id;
    }

    public Integer getDept_Division_Id() {
        return dept_Division_Id;
    }

    public void setDept_Division_Id(Integer dept_Division_Id) {
        this.dept_Division_Id = dept_Division_Id;
    }

    public String getDept_Division() {
        return dept_Division;
    }

    public void setDept_Division(String dept_Division) {
        this.dept_Division = dept_Division;
    }

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }
}
