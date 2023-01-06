package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "SERVICE_TYPE",
    discriminatorType = DiscriminatorType.STRING
)
public abstract class Service {


    protected String name;
    protected Double preis;
    @Id
    protected Integer serviceId;

    @ManyToOne
    @JoinColumn(name="reservierung_id", nullable=true)
    private Reservation reservation;

    @ManyToMany(mappedBy = "services")
    Set<BeachChair> chairs;

    public Service(String name, Double preis, Integer serviceId) {
        this.name = name;
        this.preis = preis;
        this.serviceId = serviceId;
    }

    public Service() {
    }

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


    @Override
    public String toString() {
        return "Service{" +
            "name='" + name + '\'' +
            ", preis=" + preis +
            ", serviceId=" + serviceId +
            ", reservierung=" + reservation +
            '}';
    }
}
