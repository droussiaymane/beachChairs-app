package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "GETRAENK")
public class Drink extends Service{

    public Drink(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    public Drink() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
