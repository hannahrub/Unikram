package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**Diese klasse macht die normalen get requests aus aufgabe 3 und darunter.
 * die funktionieren in @Controller annotated klassen nämlich nicht und müssen dehalb hier bleiben*/
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


    @GetMapping("/hello")
        public String hello(){
            return "Guten Tag";
    }

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