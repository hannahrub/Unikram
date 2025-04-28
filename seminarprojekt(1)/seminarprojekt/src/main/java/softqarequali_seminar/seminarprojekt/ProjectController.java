package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    Wago750_Repository wago750_repository;
    @Autowired
    S7_1500_Ist_Repository s7_1500_ist_repository;
    @Autowired
    S7_1500_Soll_Repository s7_1500_soll_repository;
    @Autowired
    S7_1500_Differenz_Repository s7_1500_differenz_repository;




    /*Hello() method
     * takes a string parameter called name
     * combines this parameter name with the world hello
     * The @GetMapping(“/hello”) tells Spring to use our hello() method to answer requests that get sent to the http://localhost:8080/hello address.
     * @RequestParam is tellingSpring to expect a name value in the request, but if it’s not there, it will use the word "World" by default.
     * http://localhost:8080/hello?name=hannah liefert dann Hallo Hannah*/
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello aus dem neuen Projekt %s!", name);
    }

    @GetMapping("/timestamp")
    public String timestamp(){
        LocalTime myObj = LocalTime.now();
        return String.format("Current time im neuen Projekt: %s", myObj);
    }

    // -----------------actual get routen die ich fürs seminar brauche ab hier:-----------------------------
    // todo: die annotation wieder zu restcontroller ändern und n eigenes @controller file für die html sachen machen
    @GetMapping("/Wago750")
    public Wago750 wago(){
       return  wago750_repository.findTopByOrderByTimestampDesc();
    }

    @GetMapping("/S7_1500/ist/latest")
    public S7_1500_Ist ist_latest(){
        return  s7_1500_ist_repository.findTopByOrderByTimestampDesc();
    }

    @GetMapping("/S7_1500/soll/latest")
    public S7_1500_Soll soll_latest(){
        return  s7_1500_soll_repository.findTopByOrderByTimestampDesc();
    }

    @GetMapping("/S7_1500/differenz/latest")
    public S7_1500_Differenz differenz_latest(){
        return  s7_1500_differenz_repository.findTopByOrderByTimestampDesc();
    }

    @GetMapping("/S7_1500/ist/all")
    public List<S7_1500_Ist> ist_all(){
        return  s7_1500_ist_repository.findAll();
    }

    @GetMapping("/S7_1500/soll/all")
    public List<S7_1500_Soll> soll_all(){
        return  s7_1500_soll_repository.findAll();
    }

    @GetMapping("/S7_1500/differenz/all")
    public List<S7_1500_Differenz> differenz_all(){
        return  s7_1500_differenz_repository.findAll();
    }
}