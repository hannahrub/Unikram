package softwarequali.seminarprojekt;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

@SpringBootApplication
public class MqttJavaApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }


    /* In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container
     are called beans.
      A bean is an object that is instantiated, assembled, and otherwise managed by a Spring IoC container.*/
    @Bean
    public IntegrationFlow mqttInbound() {

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
                .handle(m -> System.out.println(m.getPayload()))
                .get();
    }

}

