package at.ac.fhsalzburg.swd.spring.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "KUEHLSCHRANK")
public class Refrigerator extends Service{

    private Integer kapazitaet;

    public Refrigerator(String name, Double preis, Integer serviceId, Integer kapazitaet) {
        super(name, preis, serviceId);
        this.kapazitaet = kapazitaet;
    }

    public Refrigerator() {
    }

    public Integer getKapazitaet() {
        return kapazitaet;
    }
    public void setKapazitaet(Integer kapazitaet) {
        this.kapazitaet = kapazitaet;
    }

    @Override
    public String toString() {
        return "Kuehlschrank{" +
            "kapazitaet=" + kapazitaet +
            '}';
    }
}
