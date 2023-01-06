package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Reservation;
import at.ac.fhsalzburg.swd.spring.model.Service;
import at.ac.fhsalzburg.swd.spring.model.Beach;
import java.util.Set;


public class BeachChairDTO {


    private Integer korbId;
    private String status;
    private Beach beach;
    private Set<Reservation> reservation;
    Set<Service> services;


    protected BeachChairDTO() {}

    public BeachChairDTO(Beach beach, Integer korbId, String status) {
        this.beach = beach;
        this.korbId = korbId;
        this.status = status;
    }

    public Integer getKorbId() {
        return korbId;
    }

    public void setKorbId(Integer korbId){
        this.korbId = korbId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
