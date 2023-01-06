package at.ac.fhsalzburg.swd.spring.dto;

import at.ac.fhsalzburg.swd.spring.model.Service;

public class TournamentDTO extends Service {

    public TournamentDTO(String name, Double preis, Integer serviceId) {
        super(name, preis, serviceId);
    }

    protected TournamentDTO() {}

    @Override
    public String toString() {
        return super.toString();
    }
}
