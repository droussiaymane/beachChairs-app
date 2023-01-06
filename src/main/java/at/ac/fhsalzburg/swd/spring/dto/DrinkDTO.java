package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Service;

public class DrinkDTO extends Service {

    public DrinkDTO(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    public DrinkDTO() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
