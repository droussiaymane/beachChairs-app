package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SONNENSCHUTZ")
public class Sunblocker extends Service{

    public Sunblocker(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    public Sunblocker() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
