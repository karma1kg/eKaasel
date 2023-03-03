package bt.gov.voice.dto;

import java.io.Serializable;

/**
 * Created by USER on 6/30/2021.
 */
public class DepartmentDTO implements Serializable{

    private int department_Id;
    private String department_Name;
    private String department_Short_Desc;
    private int ministry_Id;

    public DepartmentDTO() {
    }
    public DepartmentDTO(int department_Id, String department_Name, String department_Short_Desc, int ministry_Id) {
        this.department_Id = department_Id;
        this.department_Name = department_Name;
        this.department_Short_Desc = department_Short_Desc;
        this.ministry_Id = ministry_Id;
    }

    public int getDepartment_Id() {
        return department_Id;
    }

    public void setDepartment_Id(int department_Id) {
        this.department_Id = department_Id;
    }

    public String getDepartment_Name() {
        return department_Name;
    }

    public void setDepartment_Name(String department_Name) {
        this.department_Name = department_Name;
    }

    public String getDepartment_Short_Desc() {
        return department_Short_Desc;
    }

    public void setDepartment_Short_Desc(String department_Short_Desc) {
        this.department_Short_Desc = department_Short_Desc;
    }

    public int getMinistry_Id() {
        return ministry_Id;
    }

    public void setMinistry_Id(int ministry_Id) {
        this.ministry_Id = ministry_Id;
    }
}
