package softwarequali.seminarprojekt;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class S7_1500_Differenz {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;


    public S7_1500_Differenz() {}

    public S7_1500_Differenz(String payload, Map<String, Object> headers) {

        this.payload = payload;
        this.headers = headers;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[payload=%s, headers=%s, id=%s, ]",
                payload,headers, id);
    }

}
