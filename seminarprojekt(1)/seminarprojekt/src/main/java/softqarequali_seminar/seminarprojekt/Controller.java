package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
public class Controller {

    @Autowired
    Wago750_Repository wago750_repository;
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

    @GetMapping("/Wago750")
    public Wago750 wago(){
       return  wago750_repository.findTopByOrderByTimestampDesc();
    }
}