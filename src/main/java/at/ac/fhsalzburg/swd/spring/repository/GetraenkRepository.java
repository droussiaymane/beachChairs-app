package at.ac.fhsalzburg.swd.spring.repository;

import at.ac.fhsalzburg.swd.spring.model.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GetraenkRepository extends CrudRepository<Drink, Integer> {

	@Transactional(timeout = 10)
    Drink findByServiceId(Integer serviceId);

}
