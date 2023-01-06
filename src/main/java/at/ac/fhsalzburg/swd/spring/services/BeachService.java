package at.ac.fhsalzburg.swd.spring.services;


import at.ac.fhsalzburg.swd.spring.model.Beach;
import at.ac.fhsalzburg.swd.spring.repository.StrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class BeachService implements BeachServicenterface {


    @Autowired
    private StrandRepository repo;

    public BeachService() {

    }


    @Override
    public Beach addBeach(String name) {
            Beach newBeach = new Beach(name);
            repo.save(newBeach);
            return newBeach;
    }

    @Override
    public Collection<Beach> getAll() {
        List<Beach> result = new ArrayList<Beach>();
        repo.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Beach getById(String name) {
        return repo.findById(name).get();
    }

    @Override
    public void deleteById(String name) {
        repo.deleteById(name);
    }


}
