package softqarequali_seminar.seminarprojekt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

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

    public void saveData(Message m) {

        Long timestamp_from_header = m.getHeaders().getTimestamp();
        System.out.println("topic: " + (String) m.getHeaders().get("mqtt_receivedTopic")+ " --payload: " + m.getPayload() + " --timestamp: " + timestamp_from_header);

        String topic = (String) m.getHeaders().get("mqtt_receivedTopic");
        switch(topic) {
            case "Wago750/Status":
                wago750_repository.save(new Wago750((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                break;
            case "S7_1500/Temperatur/Ist":
                s7_1500_ist_repository.save(new S7_1500_Ist((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                break;
            case"S7_1500/Temperatur/Differenz":
                s7_1500_Differenz_repository.save(new S7_1500_Differenz((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                break;
            case "S7_1500/Temperatur/Soll":
                s7_1500_soll_repository.save(new S7_1500_Soll((String) m.getPayload(), m.getHeaders(), timestamp_from_header));
                break;
            case "Random/Integer":
                // code block
                break;
            case null, default:
                break;
        }
    }
}
