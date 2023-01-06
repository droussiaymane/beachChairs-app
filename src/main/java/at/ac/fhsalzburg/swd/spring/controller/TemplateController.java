package at.ac.fhsalzburg.swd.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import at.ac.fhsalzburg.swd.spring.dto.*;
import at.ac.fhsalzburg.swd.spring.model.*;
import at.ac.fhsalzburg.swd.spring.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import at.ac.fhsalzburg.swd.spring.TestBean;
import at.ac.fhsalzburg.swd.spring.model.Service;

import at.ac.fhsalzburg.swd.spring.util.ObjectMapperUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller // marks the class as a web controller, capable of handling the HTTP requests. Spring
            // will look at the methods of the class marked with the @Controller annotation and
            // establish the routing table to know which methods serve which endpoints.
public class TemplateController {

    // Dependency Injection
    // ----------------------------------------------------------------------
    @Autowired BeachServicenterface beachService;

    @Autowired // To wire the application parts together, use @Autowired on the fields,
               // constructors, or methods in a component. Spring's dependency injection mechanism
               // wires appropriate beans into the class members marked with @Autowired.
    private ApplicationContext context;

    @Autowired
    private EntityManager entityManager;

    @Autowired UserServiceInterface userService;

    @Autowired ProductServiceInterface productService;

    @Autowired
    BeachServicenterface strandService;

    @Autowired ServiceServiceInterface serviceService;

    @Autowired ChairManagementServiceInterface chairManagementService;

    @Autowired
    TournamentServiceInterface turnierService;

    @Autowired ReservationServiceInterface reservationService;

    @Resource(name = "sessionBean") // The @Resource annotation is part of the JSR-250 annotation
                                    // collection and is packaged with Jakarta EE. This annotation
                                    // has the following execution paths, listed by Match by Name,
                                    // Match by Type, Match by Qualifier. These execution paths are
                                    // applicable to both setter and field injection.
                                    // https://www.baeldung.com/spring-annotations-resource-inject-autowire
    TestBean sessionBean;

    @Autowired
    TestBean singletonBean;

    // HTTP Request Mappings GET/POST/... and URL Paths
    // ----------------------------------------------------------------------


    @RequestMapping("/") // The @RequestMapping(method = RequestMethod.GET, value = "/path")
                         // annotation specifies a method in the controller that should be
                         // responsible for serving the HTTP request to the given path. Spring will
                         // work the implementation details of how it's done. You simply specify the
                         // path value on the annotation and Spring will route the requests into the
                         // correct action methods:
                         // https://springframework.guru/spring-requestmapping-annotation/#:~:text=%40RequestMapping%20is%20one%20of%20the,map%20Spring%20MVC%20controller%20methods.
    public String index(Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

        if (session == null) {
            model.addAttribute("message", "no session");
        } else {
            Integer count = (Integer) session.getAttribute("count");
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count++;
            session.setAttribute("count", count);
        }

        // check if user is logged in
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("user",currentUserName);
        }

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);

        return "index";
    }

    @RequestMapping("/chairs")
    public String chairs(final RedirectAttributes redirectAttributes,Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

    if(model.getAttribute("begin")==null || model.getAttribute("end")==null){
         return "redirect:/reservation";
    }

        List<BeachChairDTO> listOfChairs = ObjectMapperUtils.mapAll(chairManagementService.getAll(), BeachChairDTO.class);

        model.addAttribute("chairs", listOfChairs);

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "chairs";
    }

    @RequestMapping("/choose-service")
    public String chooseService(String begin,String end,Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication,@RequestParam("chair") int chairId ) throws ParseException {

        model.addAttribute("begin", begin);
        model.addAttribute("end", end);

        List<Service> listOfServices = ObjectMapperUtils.mapAll(serviceService.getAll(), Service.class);

        model.addAttribute("services", listOfServices);

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "services";
    }

    @PostMapping("/processReservation")
    public String processReservation(String begin,String end,Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication,Double price,@RequestParam("chair") int chairId ) throws ParseException {


        String username=authentication.getName();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date begining = (Date)formatter.parse(begin);
        Date ending = (Date)formatter.parse(end);
        reservationService.addReservation(begining,ending,price,userService.getByUsername(username),chairManagementService.getByChairId(chairId),null);
        List<Reservation> reservationList = ObjectMapperUtils.mapAll(reservationService.getAllByUserId(username), Reservation.class);

        model.addAttribute("reservations", reservationList);

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "redirect:/getallreservations";
    }

    @RequestMapping("/getallreservations")
    public String getallreservations(Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

        String username=authentication.getName();
        List<Reservation> reservationList = ObjectMapperUtils.mapAll(reservationService.getAllByUserId(username), Reservation.class);

        model.addAttribute("reservations", reservationList);

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "reservations";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

        if (session == null) {
            model.addAttribute("message", "no session");
        } else {
            Integer count = (Integer) session.getAttribute("count");
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count++;
            session.setAttribute("count", count);
        }

        // check if user is logged in
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("user",currentUserName);
        }

        model.addAttribute("message", userService.doSomething());

        model.addAttribute("halloNachricht", "welcome to SWD lab");

        // map list of entities to list of DTOs
        List<UserDTO> listOfUserTO = ObjectMapperUtils.mapAll(userService.getAll(), UserDTO.class);

        List<BeachDTO> listOfBeaches = ObjectMapperUtils.mapAll(strandService.getAll(), BeachDTO.class);

        List<BeachChairDTO> listOfChairs = ObjectMapperUtils.mapAll(chairManagementService.getAll(), BeachChairDTO.class);

        List<Service> listOfServices = ObjectMapperUtils.mapAll(serviceService.getAll(), Service.class);

        List<ReservationDTO> listOfReservation = ObjectMapperUtils.mapAll(reservationService.getAll(), ReservationDTO.class);

        List<TournamentListDTO> listOfTournament = ObjectMapperUtils.mapAll(turnierService.getAll(), TournamentListDTO.class);


        model.addAttribute("users", listOfUserTO);
        model.addAttribute("chairs", listOfChairs);
        model.addAttribute("beach", listOfBeaches);
        model.addAttribute("service", listOfServices);
        model.addAttribute("reservierung", listOfReservation);
        model.addAttribute("tournament", listOfTournament);

        model.addAttribute("beanSingleton", singletonBean.getHashCode());

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "dashboard";
    }

    @RequestMapping("/tournament")
    public String turniere(Model model, HttpSession session, @CurrentSecurityContext(expression = "authentication") Authentication authentication) {

        if (session == null) {
            model.addAttribute("message", "no session");
        } else {
            Integer count = (Integer) session.getAttribute("count");
            if (count == null) {
                count = Integer.valueOf(0);
            }
            count++;
            session.setAttribute("count", count);
        }

        // check if user is logged in
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            model.addAttribute("user",currentUserName);
        }

        List<TournamentListDTO> listOfTournament = ObjectMapperUtils.mapAll(turnierService.getAll(), TournamentListDTO.class);

        model.addAttribute("tournament", listOfTournament);

        model.addAttribute("beanSingleton", singletonBean.getHashCode());

        TestBean prototypeBean = context.getBean("prototypeBean", TestBean.class);
        model.addAttribute("beanPrototype", prototypeBean.getHashCode());

        model.addAttribute("beanSession", sessionBean.getHashCode());

        Authentication lauthentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authenticated", lauthentication);


        return "tournament";
    }

    @RequestMapping(value = {"/login"})
    public String login(Model model) {
        return "login";
    }

    @RequestMapping(value = {"/reservation"})
    public String reservierung(Model model) {

        return "reservation";
    }
    @PostMapping(value = {"/initReservation"})
    public String initreservation(final RedirectAttributes redirectAttributes, Model model, String begin, String end, @CurrentSecurityContext(expression = "authentication") Authentication authentication) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date begining = (Date)formatter.parse(begin);
        Date ending = (Date)formatter.parse(end);

        String username=authentication.getName();
        redirectAttributes.addFlashAttribute("begin",begin);

        redirectAttributes.addFlashAttribute("end",end);

        return "redirect:/chairs";
    }

    @RequestMapping(value = {"/login-error"})
    public String loginError(Model model) {
    	model.addAttribute("error","Login fehlgeschlagen");
    	return "login";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String showAddPersonPage(Model model, @RequestParam(value = "username", required = false) String username) {

    	User modUser = null;
    	UserDTO userDto = new UserDTO();

    	if (username!=null) {
    		modUser = userService.getByUsername(username);
    	}

    	if (modUser!=null) {
    		// map user to userDTO
    		userDto = ObjectMapperUtils.map(modUser, UserDTO.class);
    	} else {
    		userDto = new UserDTO();
    	}

        model.addAttribute("user", userDto);

        return "addUser";
    }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String addUser(Model model, //
            @ModelAttribute("UserForm") UserDTO userDTO) { // The @ModelAttribute is
                                                                         // an annotation that binds
                                                                         // a method parameter or
                                                                         // method return value to a
                                                                         // named model attribute
                                                                         // and then exposes it to a
                                                                         // web view:

        // merge instances
        User user = ObjectMapperUtils.map(userDTO, User.class);

        // if user already existed in DB, new information is already merged and saved
        // a new user must be persisted (because not managed by entityManager yet)
        if (!entityManager.contains(user)) userService.addUser(user);

        return "redirect:/";
    }

}
