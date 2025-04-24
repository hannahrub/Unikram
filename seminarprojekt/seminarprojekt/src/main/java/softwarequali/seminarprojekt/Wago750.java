package softwarequali.seminarprojekt;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Wago750 {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;


    public Wago750() {}

    public Wago750(String data, Map<String,Object> headers) {

        this.payload = data;
        this.headers = headers;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[payload=%s, headers=%s, id=%s, ]",
                payload,headers, id);
    }

}
