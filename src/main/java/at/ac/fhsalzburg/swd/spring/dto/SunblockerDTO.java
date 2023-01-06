package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Service;


public class SunblockerDTO extends Service {

    public SunblockerDTO(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    public SunblockerDTO() {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
