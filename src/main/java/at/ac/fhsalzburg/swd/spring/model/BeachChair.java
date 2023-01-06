package at.ac.fhsalzburg.swd.spring.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="chair")
public class BeachChair {

    @Id
    @Column(name = "id")
    private Integer korbId;
    private String status;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name="strand_id", nullable=false)
    private Beach beach;

    @OneToMany(mappedBy= "beachChair")
    private Set<Reservation> reservation;

    @ManyToMany
    @JoinTable(
        name = "korb_services",
        joinColumns = @JoinColumn(name = "korb_id"),
        inverseJoinColumns = @JoinColumn(name = "service_id"))
    Set<Service> services;



    protected BeachChair() {}

    public BeachChair(Beach beach, Integer korbId, String status) {
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

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
