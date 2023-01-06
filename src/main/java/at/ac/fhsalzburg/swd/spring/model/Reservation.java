package at.ac.fhsalzburg.swd.spring.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
public class Reservation {


    @DateTimeFormat(pattern = "dd-mm-yyyy hh-mm")
    private Date beginn;
    @DateTimeFormat(pattern = "dd-mm-yyyy hh-mm")
    private Date ende;
    private Double preis;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reservierungsId;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="korb_id")
    private BeachChair beachChair;

    @OneToMany(mappedBy= "reservation")
    private Set<Service> bookedService;

    protected Reservation() {}

    public Reservation(Date beginn, Date ende, Double preis, User user, BeachChair beachChair, Set<Service> bookedService) {
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


    public BeachChair getBeachChair() {
        return beachChair;
    }

    public void setBeachChair(BeachChair beachChair) {
        this.beachChair = beachChair;
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
