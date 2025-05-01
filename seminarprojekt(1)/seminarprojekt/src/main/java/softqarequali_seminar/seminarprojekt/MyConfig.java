package softqarequali_seminar.seminarprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Autowired
    MqttConnection.MyGateway myGateway;

    @Bean(name = "dostuff")
    public MyInterface mymethod(){

        return () -> "angefügt von meiner metthode - hier iet dein entry " ;
    }

    /*
    @Bean
    public MyInterface muster1(){
        return () -> myGateway.sendToMqtt("1");
    }*/
}

