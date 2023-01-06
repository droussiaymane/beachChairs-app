package at.ac.fhsalzburg.swd.spring.services;

import at.ac.fhsalzburg.swd.spring.model.Reservation;
import at.ac.fhsalzburg.swd.spring.model.Service;
import at.ac.fhsalzburg.swd.spring.model.BeachChair;
import at.ac.fhsalzburg.swd.spring.model.User;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

public interface ReservationServiceInterface {

    Reservation addReservation(Date beginn, Date ende, Double preis, User user, BeachChair beachChair, Set<Service> bookedService);

    Collection<Reservation> getAll();

    Collection<Reservation> getAllByUserId(String username);


}
