package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TURNIER")
public class Tournament extends Service{

    public Tournament(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    protected Tournament() {}

    @Override
    public String toString() {
        return super.toString();
    }
}
