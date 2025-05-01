package softqarequali_seminar.seminarprojekt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Component;

@Component
public class MqttConnection {

    private char[] pw = "018435380".toCharArray();
    private String username = "hannah.beltz@stud.hs-bochum.de";
    private String url = "tcp://sr-labor.ddns.net:1883";
    private String clientId = "mqttx_887f4e58";
    private String topic = "#";

    @Autowired
    S7_1500_Differenz_Repository s7_1500_Differenz_repository;
    @Autowired
    S7_1500_Ist_Repository s7_1500_ist_repository;
    @Autowired
    S7_1500_Soll_Repository s7_1500_soll_repository;

    @Autowired
    Wago750_Repository wago750_repository;


    MqttConnection() {

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

    public DefaultMqttPahoClientFactory configureConnection(){
        // benutzername und passwort konfigurieren
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setPassword(pw);
        connectOptions.setUserName(username);
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(connectOptions);

        return factory;
    }

    @Bean
    public IntegrationFlow receiveAndSaveMessagesService() {

        DefaultMqttPahoClientFactory factory = configureConnection();

        // mit mqtt broker verbinden und Nachrichten annehmen
        return IntegrationFlow.from(
                        new MqttPahoMessageDrivenChannelAdapter(
                                url, clientId, factory, topic))
                .handle(this::saveData)
                .get();
    }

/*
    @Bean
    public IntegrationFlow mqttOutboundFlow() {

        DefaultMqttPahoClientFactory factory = configureConnection();

        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(url, clientId, factory);
        messageHandler.setDefaultTopic("Wago750/Control");

        // was returned das?? eine abstrakte lamda funktion
        return f -> f.handle(messageHandler);
    } */

    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound() {

        DefaultMqttPahoClientFactory factory = configureConnection();

        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(url, clientId, factory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("Wago750/Control");
        return messageHandler;
    }

    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }

    @MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
    public interface MyGateway {

        void sendToMqtt(String data);

    }
}


//. An die SPS k¨onnen
//¨uber MQTT an das Topic: Wago750/Control die Integer-Werte 0, 1, 2 oder 3 gesendet
//werden. Wenn die SPS einen dieser Control-Befehle erh¨alt, wechselt sie ihren Modus.
//Beim Control-Befehl 0 geht die SPS in einen Ruhemodus und schickt keine weiteren
//Daten mehr. Bei den Control-Befehlen 1, 2 oder 3, wechselt die SPS den Beleuchtungs-
//modus.