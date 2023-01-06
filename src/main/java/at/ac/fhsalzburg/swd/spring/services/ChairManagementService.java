package at.ac.fhsalzburg.swd.spring.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import at.ac.fhsalzburg.swd.spring.model.Beach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.repository.StrandkorbRepository;


@Service
public class ChairManagementService implements ChairManagementServiceInterface {


    @Autowired
    private StrandkorbRepository repo;

    public ChairManagementService() {

    }


    @Override
    public BeachChair addChair(Beach beach, Integer korbId, String status) {
            BeachChair newChair = new BeachChair(beach, korbId, status);
            repo.save(newChair);
            return newChair;
    }

    @Override
    public boolean deleteChair(Integer korbId) {
        repo.deleteById(korbId);
        return true;
    }

    @Override
    public BeachChair getByChairId(Integer korbId) {
        return repo.findByKorbId(korbId);
    }


    @Override
    public Collection<BeachChair> getAll() {
        List<BeachChair> result = new ArrayList<>();

        repo.findAll().forEach(result::add);
        return result;
    }

}
