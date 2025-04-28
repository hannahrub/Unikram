package softqarequali_seminar.seminarprojekt;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class S7_1500_Soll {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;
    public Long timestamp;


    public S7_1500_Soll() {}

    public S7_1500_Soll(String payload, Map<String, Object> headers, Long timestamp) {

        this.payload = payload;
        this.headers = headers;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[payload=%s, headers=%s, timestamp=%s, ]",
                payload,headers, timestamp);
    }

}

