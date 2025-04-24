package softwarequali.seminarprojekt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.Message;

@SpringBootApplication
public class Application {

    @Autowired
    private S7_1500_Repository s7_1500_repository;

    @Autowired
    private Wago750_Repository wago750_repository;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }


    // todo: ocj brauch beide methoden nochmal für das andere gerät
    //  und die verschiedenen topics müssen unterschieden werden
    // und ich brauch dafür nen eigenen datentyp und repository etc. 

    public void saveData(Message m){

        System.out.println("topic: "+ (String) m.getHeaders().get("mqtt_receivedTopic")+ " payload: "+ m.getPayload());

        String topic = (String) m.getHeaders().get("mqtt_receivedTopic");
        switch(topic) {
            case "Wago750/Status":
                wago750_repository.save(new Wago750((String) m.getPayload(), m.getHeaders()));
                break;
            case "S7_1500/Temperatur/Ist", "S7_1500/Temperatur/Differenz", "S7_1500/Temperatur/Soll":
                s7_1500_repository.save(new S7_1500((String) m.getPayload(), m.getHeaders()));
                break;
            case "Random/Integer":
                // code block
                break;
            case null, default:
                break;
        }
    }

    @Bean
    public IntegrationFlow receiveAndSaveMessagesService() {

        // benutzername und passwort konfigurieren
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        char[] pw = "018435380".toCharArray();
        String username = "hannah.beltz@stud.hs-bochum.de";
        connectOptions.setPassword(pw);
        connectOptions.setUserName(username);
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(connectOptions);

        // mit mqtt broker verbinden und Nachrichten annehmen
        return IntegrationFlow.from(
                        new MqttPahoMessageDrivenChannelAdapter(
                                "tcp://sr-labor.ddns.net:1883",
                                "mqttx_887f4e58",
                                factory,
                                "#"))
                .handle(this::saveData)
                .get();
    }

}
