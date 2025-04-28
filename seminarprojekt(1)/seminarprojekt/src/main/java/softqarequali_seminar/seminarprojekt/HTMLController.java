package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalTime;

@Controller
public class HTMLController {
    @Autowired
    Wago750_Repository wago750_repository;
    @Autowired
    S7_1500_Ist_Repository s7_1500_ist_repository;
    @Autowired
    S7_1500_Soll_Repository s7_1500_soll_repository;
    @Autowired
    S7_1500_Differenz_Repository s7_1500_differenz_repository;


    // !!!!! html templates gehören in src/main/resources/templates/ !!!!!

    @GetMapping("/testThymeleaf")
    public String viewHomePage(Model model) {
        model.addAttribute("wert", 215);
        model.addAttribute("auslesen", "hallolo ich wurde ausgeleen");
        model.addAttribute("payload", s7_1500_ist_repository.findTopByOrderByTimestampDesc().payload);
        model.addAttribute("object", s7_1500_ist_repository.findTopByOrderByTimestampDesc());
        return "index";
    }
}
