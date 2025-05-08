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


/** Diese Klasse baut die mqtt verbindungen auf und konfiguriert sie
 * sie nimmt außerdem die nachrichten entgegen und speichert sie in der db*/

@Component
public class MqttConfig {

    // infos um die verbindung zu konfigurieren
    private final char[] pw = "018435380".toCharArray();
    private final String username = "hannah.beltz@stud.hs-bochum.de";
    private final String url = "tcp://sr-labor.ddns.net:1883";
    private final String clientId = "mqttx_887f4e58";
    private final String topic = "#";

    @Autowired
    SaveDataService service;

    MqttConfig() {

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
                                url, "mqttx_887f4e59", factory, topic))
                .handle(m -> service.saveData(m))
                .get();
    }


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
