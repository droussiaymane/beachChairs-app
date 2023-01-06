package at.ac.fhsalzburg.swd.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import at.ac.fhsalzburg.swd.spring.model.Beach;

@Repository
public interface StrandRepository extends CrudRepository<Beach, String> {

	@Transactional(timeout = 10)
    Beach findByStrandName(String name);

}
