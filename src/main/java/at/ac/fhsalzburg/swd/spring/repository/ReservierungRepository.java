package at.ac.fhsalzburg.swd.spring.repository;

import at.ac.fhsalzburg.swd.spring.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReservierungRepository extends CrudRepository<Reservation, Integer> {

	@Transactional(timeout = 10)
    Reservation findByReservierungsId(Integer reservierungsId);

}
