package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.model.TournamentList;

import java.util.Collection;
import java.util.Date;

public interface TournamentServiceInterface {

    void addTournament(Date beginn, Date ende, String name);

    TournamentList getByTurnierName(String name);

    boolean deleteTournament(String name);

    Collection<TournamentList> getAll();
}
