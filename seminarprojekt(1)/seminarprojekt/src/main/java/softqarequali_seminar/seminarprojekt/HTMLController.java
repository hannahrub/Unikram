package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @Autowired
    ProjectController projectController;

// todo: wie man die seite automatisch refreshed https://www.geeksforgeeks.org/how-to-automatic-refresh-a-web-page-in-fixed-time/
    // todo: https://www.mongodb.com/docs/manual/changeStreams/
    // !!!!! html templates gehören in src/main/resources/templates/ !!!!!

    @GetMapping("/testThymeleaf")
    public String testPage(Model model) {
        model.addAttribute("wert", 215);
        model.addAttribute("auslesen", "hallolo ich wurde ausgeleen");
        model.addAttribute("payload", s7_1500_ist_repository.findTopByOrderByTimestampDesc().payload);
        model.addAttribute("object", s7_1500_ist_repository.findTopByOrderByTimestampDesc());

        return "index";
    }

    @GetMapping("/homepage")
    public String viewHomePage(Model model) {
        int[] myarr = new int[28];
        int[] bin =  projectController.wago().binaryArray;
        model.addAttribute("wago750", projectController.wago().payload);
        model.addAttribute("boolarray", projectController.wago().binaryArray);
        model.addAttribute("s7_ist", projectController.ist_latest().payload);
        model.addAttribute("s7_soll", projectController.soll_latest().payload);
        model.addAttribute("s7_diff", projectController.differenz_latest().payload);
        model.addAttribute("meineliste", myarr);
        model.addAttribute("meinezahl", 1);
        model.addAttribute("x_0", bin[0]);
        model.addAttribute("x_1", bin[1]);
        model.addAttribute("x_2", bin[2] );
        model.addAttribute("x_3", bin[3]);
        model.addAttribute("x_4", bin[4]);
        model.addAttribute("x_5", bin[5]);
        model.addAttribute("x_6", bin[6]);
        model.addAttribute("x_7", bin[7]);
        model.addAttribute("x_8", bin[8]);
        model.addAttribute("x_9", bin[9]);
        model.addAttribute("x_10", bin[10]);
        model.addAttribute("x_11", bin[11]);
        model.addAttribute("x_12", bin[12]);
        model.addAttribute("x_13", bin[13]);
        model.addAttribute("x_14", bin[14]);
        model.addAttribute("x_15", bin[15]);


        return "displaydata";
    }
}
