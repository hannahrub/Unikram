package softqarequali_seminar.seminarprojekt;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.integration.json.SimpleJsonSerializer.toJson;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SeminarprojektApplicationIntegrationTests {

    /*Implementieren Sie jeweils einen Integrationstest, der zusammenh¨angend testet,
• ob eine empfangene Nachricht in der Datenbank gespeichert wird und ob diese
¨uber den RESTful Web Service wieder korrekt abrufbar ist. Dazu k¨onnen Sie den
Empfang der Nachricht simulieren.
sowie
• ob ein gesendeter POST-Request (Control-Befehl) an den RESTful Web Service
das Senden einer Nachricht an die Wago-SPS ausl¨ost.*/

    @Autowired
    S7_1500_Ist_Repository s7_1500_ist_repository;

    @Autowired
    SaveDataService saveDataService;

    @Autowired
    ProjectController projectController;

    @Autowired
    HTMLController htmlController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MqttConfig.MyGateway gateway;

    @Test
    void shouldSaveMessageAndRetrieve() {

        //nachrichtenempfang simulieren
        Message m = Mockito.mock(Message.class);
        MessageHeaders mh = Mockito.mock(MessageHeaders.class);

        when(m.getHeaders()).thenReturn(mh);
        when(m.getPayload()).thenReturn("IT payload");
        long newest_ts = s7_1500_ist_repository.findTopByOrderByTimestampDesc().timestamp;
        when(mh.getTimestamp()).thenReturn(newest_ts + 100); // stelle sicher dass unsere fake nacjricht die neuste ist
        when(mh.get("mqtt_receivedTopic")).thenReturn("S7_1500/Temperatur/Ist");

        // Nachricht in Datenbank speichern
        String[] save_return = saveDataService.saveData(m);

        // Nachricht über Webservice abrufen
        assertThat(projectController.ist_latest().id).isEqualTo(save_return[1]);
    }


    @Test
    void shouldSendMqttMessageOnPostRequest() throws Exception {

        // wir erstellen kinda einen mock eines post request
        // dieser schickt von homepage einen JSON
        // und der entsprechende JSON soll ein formeval objekt mit entsprechender konfig sein
        FormEval formEval = new FormEval();
        formEval.setData("1");
        formEval.setId((long) 1);

        String formevalJson = toJson(formEval);

        MqttConfig.MyGateway gateway1 = Mockito.mock(MqttConfig.MyGateway.class);

        mockMvc.perform(post("/homepage")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(formevalJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("displaydata"));

        //MqttConfig.MyGateway gateway1 = Mockito.mock(MqttConfig.MyGateway.class);
    }
}


