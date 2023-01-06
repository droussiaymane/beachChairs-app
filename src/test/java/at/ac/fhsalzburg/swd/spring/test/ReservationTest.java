package at.ac.fhsalzburg.swd.spring.test;

import at.ac.fhsalzburg.swd.spring.model.Reservation;
import at.ac.fhsalzburg.swd.spring.model.User;
import at.ac.fhsalzburg.swd.spring.repository.ReservierungRepository;
import at.ac.fhsalzburg.swd.spring.services.ReservationService;
import at.ac.fhsalzburg.swd.spring.services.ReservationServiceInterface;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReservationTest {

    @InjectMocks
    ReservationService reservationService;

    @Mock
    ReservierungRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void getAllReservations()
    {
        List<Reservation> list = new ArrayList<Reservation>();
        Reservation reservation1 = new Reservation(new Date(),new Date(),30.0,null,null,null);
        Reservation reservation2 = new Reservation(new Date(),new Date(),30.0,null,null,null);
        Reservation reservation3 = new Reservation(new Date(),new Date(),30.0,null,null,null);

        list.add(reservation1);
        list.add(reservation1);
        list.add(reservation1);

        when(repository.findAll()).thenReturn(list);

        //test
        List<Reservation> empList = reservationService.getAll().stream().toList();

        assertEquals(3, empList.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    public void getAllByUserId()
    {
        List<Reservation> list = new ArrayList<Reservation>();

        User user1=new User("test1","test1","test1@","66666666",new Date(),"123","admin","sfdsfdsgfdgfd","dsfdsgdfg","fdsfdsgdfg");
        User user2=new User("test2","test1","test1@","66666666",new Date(),"123","admin","sfdsfdsgfdgfd","dsfdsgdfg","fdsfdsgdfg");

        Reservation reservation1 = new Reservation(new Date(),new Date(),30.0,user1,null,null);
        Reservation reservation2 = new Reservation(new Date(),new Date(),32.0,user2,null,null);
        Reservation reservation3 = new Reservation(new Date(),new Date(),33.0,user1,null,null);

        list.add(reservation1);
        list.add(reservation2);
        list.add(reservation3);

        when(repository.findAll()).thenReturn(list);


        List<Reservation> reservationlist = reservationService.getAllByUserId("test1").stream().toList();

        assertEquals(33.0,reservationlist.stream().filter(reservation -> reservation.getPreis().equals(33.0)).findFirst().get().getPreis() );

    }

    @Test
    public void createReservation()
    {
        User user1=new User("test1","test1","test1@","66666666",new Date(),"123","admin","sfdsfdsgfdgfd","dsfdsgdfg","fdsfdsgdfg");

       Reservation reservation=new Reservation(new Date(),new Date(),30.0,user1,null,null);
        when(repository.save(reservation)).thenReturn(reservation);

        Reservation myreservation=reservationService.addReservation(new Date(),new Date(),30.0,user1,null,null);

        assertEquals(30.0,myreservation.getPreis() );
    }


}
