package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.model.Reservation;

import java.util.Set;


public abstract class ServiceDTO {


    protected String name;
    protected Double preis;
    protected Integer serviceId;
    private Reservation reservation;
    Set<BeachChair> chairs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPreis() {
        return preis;
    }

    public void setPreis(Double preis) {
        this.preis = preis;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public Reservation getReservierung() {
        return reservation;
    }

    public void setReservierung(Reservation reservation) {
        this.reservation = reservation;
    }

    public Set<BeachChair> getChairs() {
        return chairs;
    }

    public void setChairs(Set<BeachChair> chairs) {
        this.chairs = chairs;
    }
}
