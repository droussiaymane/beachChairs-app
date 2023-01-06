package at.ac.fhsalzburg.swd.spring.services;

import java.util.ArrayList;
import java.util.List;
import at.ac.fhsalzburg.swd.spring.model.*;
import at.ac.fhsalzburg.swd.spring.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class ServiceService implements ServiceServiceInterface {

    @Autowired
    private ServiceRepository repo;

    public ServiceService() {}

    @Override
    public void addDrink(String name, Double preis, Integer serviceId) {
        Drink newDrink = new Drink(name, preis, serviceId);
        repo.save(newDrink);
    }

    @Override
    public void addRefrigerator(String name, Double preis, Integer serviceId, Integer kapazitaet) {
        Refrigerator newRefrigerator = new Refrigerator(name, preis, serviceId, kapazitaet);
        repo.save(newRefrigerator);
    }

    @Override
    public void addSunblocker(String name, Double preis, Integer serviceId) {
        Sunblocker newSunblocker = new Sunblocker(name, preis, serviceId);
        repo.save(newSunblocker);
    }

    @Override
    public void addMusic(String name, Double preis, Integer serviceId, String streamingUrl) {
        Music newMusic = new Music(name, preis, serviceId, streamingUrl);
        repo.save(newMusic);
    }

    @Override
    public void addTournament(String name, Double preis, Integer serviceId) {
        Tournament newTournament = new Tournament(name, preis, serviceId);
        repo.save(newTournament);
    }

    @Override
    public at.ac.fhsalzburg.swd.spring.model.Service getById(Integer id) {
        return repo.findById(id).get();
    }


    @Override
    public boolean deleteService(Integer serviceId) {
        repo.deleteById(serviceId);
        return true;
    }

    @Override
    public Collection<at.ac.fhsalzburg.swd.spring.model.Service> getAll() {
        List<at.ac.fhsalzburg.swd.spring.model.Service> result = new ArrayList<at.ac.fhsalzburg.swd.spring.model.Service>();
        repo.findAll().forEach(result::add);
        return result;
    }

}
