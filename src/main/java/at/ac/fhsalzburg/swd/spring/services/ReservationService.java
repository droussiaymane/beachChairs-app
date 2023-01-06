package at.ac.fhsalzburg.swd.spring.services;


import at.ac.fhsalzburg.swd.spring.model.Reservation;
import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.model.User;
import at.ac.fhsalzburg.swd.spring.repository.ReservierungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class ReservationService implements ReservationServiceInterface {

    @Autowired
    private ReservierungRepository repo;

    public ReservationService() {}

    @Override
    public Reservation addReservation(Date beginn, Date ende, Double preis, User user, BeachChair beachChair, Set<at.ac.fhsalzburg.swd.spring.model.Service> bookedService) {
        Reservation newReservation = new Reservation(beginn, ende, preis, user, beachChair, bookedService);
        repo.save(newReservation);
        return newReservation;
    }


    @Override
    public Collection<Reservation> getAll() {
        List<Reservation> result = new ArrayList<Reservation>();
        repo.findAll().forEach(result::add);
        return result;
    }

    @Override
    public Collection<Reservation> getAllByUserId(String username) {
        Collection<Reservation> reservations=getAll();
        Collection<Reservation> result=reservations.stream().filter(reservation -> reservation.getUser().getUsername().equals(username)).collect(Collectors.toList());
        return result;
    }

}
