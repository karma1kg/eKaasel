package bt.gov.voice.dto;

import java.io.Serializable;

/**
 * Created by USER on 6/25/2021.
 */

public class MinistryDTO implements Serializable {
   private int ministry_Id;
    private String ministryName;
    private String ministry_Short_Desc;

    public MinistryDTO() {
    }

    public MinistryDTO(int ministry_Id, String ministryName, String ministry_Short_Desc) {
        this.ministry_Id = ministry_Id;
        this.ministryName = ministryName;
        this.ministry_Short_Desc = ministry_Short_Desc;
    }

    public int getMinistry_Id() {
        return ministry_Id;
    }

    public void setMinistry_Id(int ministry_Id) {
        this.ministry_Id = ministry_Id;
    }

    public String getMinistryName() {
        return ministryName;
    }

    public void setMinistryName(String ministryName) {
        this.ministryName = ministryName;
    }

    public String getMinistry_Short_Desc() {
        return ministry_Short_Desc;
    }

    public void setMinistry_Short_Desc(String ministry_Short_Desc) {
        this.ministry_Short_Desc = ministry_Short_Desc;
    }
}

