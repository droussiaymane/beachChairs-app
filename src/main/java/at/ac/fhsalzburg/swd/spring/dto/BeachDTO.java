package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import java.util.Set;

public class BeachDTO {

    private String strandName;

    private Set<BeachChair> beachChair;

    protected BeachDTO() {}

    public BeachDTO(String strandName) {
        this.strandName = strandName;
    }

    public String getStrandName(){
        return strandName;
    }

    public void setStrandName(String strandName) {
        this.strandName = strandName;
    }
}
