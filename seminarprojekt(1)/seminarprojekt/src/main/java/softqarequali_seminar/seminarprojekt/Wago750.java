package softqarequali_seminar.seminarprojekt;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Map;

public class Wago750 {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;
    public Long timestamp;
    public String binaryArray; //todo: make actual array?? from int??



    public Wago750() {}

    public Wago750(String data, Map<String,Object> headers, Long timestamp) {

        this.payload = data;
        this.headers = headers;
        this.timestamp = timestamp;
        this.binaryArray = convertToBinArr();
    }

    public String convertToBinArr(){
        int[] binArray = new int[0];
        String data = payload;
        data = data.replace("[", "");
        data = data.replace("]", "");

        int number = Integer.parseInt(data);
        String binstring = Integer.toBinaryString(number);
        System.out.println("payload: " + payload + "parsed number " + number + "bin string: " + binstring);
        return binstring;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[payload=%s, headers=%s, timestamp=%s, ]",
                payload,headers, timestamp);
    }

}

