package at.ac.fhsalzburg.swd.spring.startup;

import at.ac.fhsalzburg.swd.spring.model.*;
import at.ac.fhsalzburg.swd.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Profile("!test")
@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    UserServiceInterface userService;

    @Autowired
    ProductServiceInterface productService;

    @Autowired
    OrderServiceInterface orderService;

    @Autowired
    BeachServicenterface strandService;

    @Autowired
    ChairManagementServiceInterface chairManagementService;

    @Autowired
    ServiceServiceInterface serviceService;

    @Autowired
    ReservationServiceInterface reservierungService;

    @Autowired
    TournamentServiceInterface turnierService;



    // Initialize System with preset accounts and stocks
    @Override
    @Transactional // this method runs within one database transaction; performing a commit at the
                   // end
    public void run(String... args) throws Exception {

    	if (userService.getByUsername("admin")!=null) return; // data already exists -> return

        userService.addUser("admin", "Administrator", "admin@work.org", "123", new Date(), "admin","ADMIN", "1234", "4321");

        // Additions
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm");

        userService.addUser("marcus", "Marcus Schneider", "marcus-schneider@gmail.com", "0660/1234567", DateFormat.parse("21-05-1996 00-00-00"), "marcus123","USER", "AT12 1234 1234 1234", "1234");
        userService.addUser("daria", "Daria Moscvinteva", "daria-moscvinteva@gmail.com", "0660/7654321", DateFormat.parse("09-05-2000 00-00-00"), "daria123","USER", "MD43 4321 4321 4321", "4321");


        strandService.addBeach("FH Beach");

        // 3 Beachkörbe werden erstellt auf "FH Beach"
        chairManagementService.addChair(strandService.getById("FH Beach"), 101, "enabled");
        chairManagementService.addChair(strandService.getById("FH Beach"), 102, "enabled");
        chairManagementService.addChair(strandService.getById("FH Beach"), 103, "enabled");

        // jeder Korb hat 2 Getränke
        serviceService.addDrink("Coca-Cola", 3.50, 1001);
        serviceService.addDrink("Sprite", 3.00, 1002);

        // jeder Korb kann einen kleinen 5 oder großen 10 Kühlschrank dazubuchen
        serviceService.addRefrigerator("refrigerator small",8.00,2001, 5);
        serviceService.addRefrigerator("refrigerator big",10.00,2002, 10);

        serviceService.addMusic("Spotify Premium",7.90,3001, "https://open.spotify.com/track/4cOdK2wGLETKBW3PvgPWqT");

        serviceService.addSunblocker("La Roche Posay", 4.50, 4001);
        serviceService.addSunblocker("Nivea Sun", 3.50, 4002);

        serviceService.addTournament("Tournament", 8.00, 5001);

        reservierungService.addReservation(DateFormat.parse("05-12-2022 12-00"), DateFormat.parse("05-12-2022 17-00"), 0.0,userService.getByUsername("marcus"), chairManagementService.getByChairId(101), null);
        reservierungService.addReservation(DateFormat.parse("06-12-2022 12-00"), DateFormat.parse("06-12-2022 17-00"), 0.0,userService.getByUsername("daria"), chairManagementService.getByChairId(101), null);
        reservierungService.addReservation(DateFormat.parse("06-12-2022 17-30"), DateFormat.parse("06-12-2022 20-00"), 0.0,userService.getByUsername("marcus"), chairManagementService.getByChairId(101), null);
        reservierungService.addReservation(DateFormat.parse("05-12-2022 12-00"), DateFormat.parse("05-12-2022 17-00"), 0.0,userService.getByUsername("daria"), chairManagementService.getByChairId(102), null);
        reservierungService.addReservation(DateFormat.parse("06-12-2022 14-00"), DateFormat.parse("06-12-2022 18-00"), 0.0,userService.getByUsername("marcus"), chairManagementService.getByChairId(102), null);

        turnierService.addTournament(DateFormat.parse("05-12-2022 12-00"), DateFormat.parse("05-12-2022 18-00"), "FH Semesterclosing");
        turnierService.addTournament(DateFormat.parse("23-12-2022 12-00"), DateFormat.parse("25-12-2022 18-00"), "FH Winter-Tournament");
        turnierService.addTournament(DateFormat.parse("28-01-2023 12-00"), DateFormat.parse("29-01-2023 18-00"), "FH Holiday-Tournament");
        turnierService.addTournament(DateFormat.parse("14-02-2023 12-00"), DateFormat.parse("14-02-2023 18-00"), "Valentines Tournament");
        turnierService.addTournament(DateFormat.parse("08-03-2023 12-00"), DateFormat.parse("10-03-2023 20-00"), "FH Opening");
        turnierService.addTournament(DateFormat.parse("02-06-2023 16-00"), DateFormat.parse("07-06-2022 22-00"), "FH Summer-Tournament");

        // End Addition

        productService.addProduct("first product", 3.30f);
        productService.addProduct("second product", 6.30f);
        productService.addProduct("third product", 9.30f);

        User user = userService.getAll().iterator().next();
        user.setCredit(100l);
        user = userService.getByUsername("admin");
        orderService.addOrder(new Date(), user, productService.getAll());

    }
}
