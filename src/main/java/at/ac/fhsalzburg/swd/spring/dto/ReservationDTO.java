package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.model.Service;
import at.ac.fhsalzburg.swd.spring.model.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;


public class ReservationDTO {


    @DateTimeFormat(pattern = "dd-mm-yyyy hh-mm")
    private Date beginn;
    @DateTimeFormat(pattern = "dd-mm-yyyy hh-mm")
    private Date ende;
    private Double preis;
    private Integer reservierungsId;
    private User user;
    private BeachChair beachChair;
    private Set<Service> bookedService;

    protected ReservationDTO() {}

    public ReservationDTO(Date beginn, Date ende, Double preis, User user, BeachChair beachChair, Set<Service> bookedService) {
        this.beginn = beginn;
        this.ende = ende;
        this.preis = preis;
        this.user = user;
        this.beachChair = beachChair;
        this.bookedService = bookedService;
    }


    public Date getBeginn() {
        return beginn;
    }

    public void setBeginn(Date beginn) {
        this.beginn = beginn;
    }

    public Date getEnde() {
        return ende;
    }

    public void setEnde(Date ende) {
        this.ende = ende;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public Integer getReservierungsId() {
        return reservierungsId;
    }

    public void setReservierungsId(Integer reservierungsId) {
        this.reservierungsId = reservierungsId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BeachChair getStrandkorb() {
        return beachChair;
    }

    public void setStrandkorb(BeachChair beachChair) {
        this.beachChair = beachChair;
    }

    public Set<Service> getBookedService() {
        return bookedService;
    }

    public void setBookedService(Set<Service> bookedService) {
        this.bookedService = bookedService;
    }
}
