package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Service;

public class RefrigeratorDTO extends Service {

    private Integer kapazitaet;

    public RefrigeratorDTO(String name, Double preis, Integer serviceId, Integer kapazitaet) {
        super(name, preis, serviceId);
        this.kapazitaet = kapazitaet;
    }

    public RefrigeratorDTO() {
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
