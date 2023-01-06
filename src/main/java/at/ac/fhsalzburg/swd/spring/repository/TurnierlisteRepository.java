package at.ac.fhsalzburg.swd.spring.repository;

import at.ac.fhsalzburg.swd.spring.model.TournamentList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TurnierlisteRepository extends CrudRepository<TournamentList, String> {

	@Transactional(timeout = 10)
    TournamentList findByName(String name);

}
