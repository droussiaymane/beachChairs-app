package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.repository.TurnierlisteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import at.ac.fhsalzburg.swd.spring.model.TournamentList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


@Service
public class TournamentService implements TournamentServiceInterface {

    @Autowired
    private TurnierlisteRepository repo;

    public TournamentService() {}

    @Override
    public void addTournament(Date beginn, Date ende, String name) {
        TournamentList newTurnier = new TournamentList(beginn, ende, name);
        repo.save(newTurnier);
    }

    @Override
    public TournamentList getByTurnierName(String name) {
        return repo.findByName(name);
    }

    @Override
    public boolean deleteTournament(String name) {
        repo.deleteById(name);
        return true;
    }

    @Override
    public Collection<TournamentList> getAll() {
        List<TournamentList> result = new ArrayList<TournamentList>();
        repo.findAll().forEach(result::add);
        return result;
    }

}
