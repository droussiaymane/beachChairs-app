package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.model.Service;

import java.util.Collection;

public interface ServiceServiceInterface {

    void addDrink(String name, Double preis, Integer serviceId);

    void addRefrigerator(String name, Double preis, Integer serviceId, Integer kapazitaet);

    void addSunblocker(String name, Double preis, Integer serviceId);

    void addMusic(String name, Double preis, Integer serviceId, String streamingUrl);

    void addTournament(String name, Double preis, Integer serviceId);


    Service getById(Integer id);

    boolean deleteService(Integer serviceId);

    Collection<Service> getAll();
}
