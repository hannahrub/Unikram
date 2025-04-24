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
    private DataRepository repository;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    public String saveData(Message m){
        repository.save(new Data((String) m.getPayload()));

        // fetch all data
        System.out.println("Data found with findAll():");
        System.out.println("-------------------------------");
        for (Data d : repository.findAll()) {
            System.out.println(d);
        }
        System.out.println();
        return m.getPayload().toString();
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
