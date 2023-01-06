package at.ac.fhsalzburg.swd.spring.repository;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StrandkorbRepository extends CrudRepository<BeachChair, Integer> {

    @Transactional(timeout = 10)
    BeachChair findByKorbId(Integer korbId);

}
