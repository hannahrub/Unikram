package softqarequali_seminar.seminarprojekt;

import org.springframework.data.annotation.Id;
import java.util.Map;


/**So werden die Differenz daten in einer eigenen Collection gespeichert.
 * so eine klasse gibt es für jede meiner collections*/
public class S7_1500_Differenz {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;
    public Long timestamp;


    public S7_1500_Differenz() {}

    public S7_1500_Differenz(String payload, Map<String, Object> headers, Long timestamp) {

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

