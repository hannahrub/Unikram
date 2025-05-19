package softqarequali_seminar.seminarprojekt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

@Component
public class SaveDataService {

    @Autowired
    S7_1500_Differenz_Repository s7_1500_Differenz_repository;
    @Autowired
    S7_1500_Ist_Repository s7_1500_ist_repository;
    @Autowired
    S7_1500_Soll_Repository s7_1500_soll_repository;
    @Autowired
    Wago750_Repository wago750_repository;

    public SaveDataService() {
    }

    public String[] saveData(Message m) {

        Long timestamp_from_header = m.getHeaders().getTimestamp();
        System.out.println("topic: " + (String) m.getHeaders().get("mqtt_receivedTopic")+ " --payload: " + m.getPayload() + " --timestamp: " + timestamp_from_header);

        String topic = (String) m.getHeaders().get("mqtt_receivedTopic");
        String[] topic_ID = new String[2];

        switch(topic) {
            case "Wago750/Status":
                Wago750 wago750 = wago750_repository.save(new Wago750((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                topic_ID[0] = "Wago750/Status"; topic_ID[1] = wago750.id;
                return topic_ID;
            case "S7_1500/Temperatur/Ist":
                S7_1500_Ist s71500Ist =  s7_1500_ist_repository.save(new S7_1500_Ist((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                topic_ID[0] = "S7_1500/Temperatur/Ist"; topic_ID[1] = s71500Ist.id;
                return topic_ID;
            case"S7_1500/Temperatur/Differenz":
                S7_1500_Differenz s71500Differenz =  s7_1500_Differenz_repository.save(new S7_1500_Differenz((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                topic_ID[0] = "S7_1500/Temperatur/Differenz"; topic_ID[1] = s71500Differenz.id;
                return topic_ID;
            case "S7_1500/Temperatur/Soll":
                S7_1500_Soll s71500Soll = s7_1500_soll_repository.save(new S7_1500_Soll((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                topic_ID[0] = "S7_1500/Temperatur/Soll"; topic_ID[1] = s71500Soll.id;
                return topic_ID;
            case "Random/Integer":
                // code block
                break;
            case null, default:
                break;
        }
        return null;
    }
}
