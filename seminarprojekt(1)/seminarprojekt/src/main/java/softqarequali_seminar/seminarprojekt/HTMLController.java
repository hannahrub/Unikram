package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class HTMLController {
    @Autowired
    ProjectController projectController;

    @Autowired
    MqttConfig.MyGateway gateway;



    // die annotationen mappen spezifische http requests auf bestimmte controller methoden
    // !!!!! html templates gehören in src/main/resources/templates/ !!!!!


    /*Das hier emfängt das form was durch modus wahl abgesender wird*/
    @PostMapping("/homepage")
    public String buttonSubmit(@ModelAttribute FormEval wahl, Model model){
        model.addAttribute("wahl", wahl);
        System.out.println("hallo aus dem post von homepage....... " + wahl.getData()) ;
        gateway.sendToMqtt(wahl.getData());
        return "displaydata";
    }

    @GetMapping("/homepage")
    public String viewHomePage(Model model) {
        int[] myarr = new int[28];
        int[] bin =  projectController.wago().binaryArray;

        model.addAttribute("wahl", new FormEval());

        model.addAttribute("wago750", projectController.wago().payload);
        model.addAttribute("wago_timestamp", projectController.wago().timestamp);
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
