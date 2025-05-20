package softqarequali_seminar.seminarprojekt;

import org.springframework.data.annotation.Id;

import java.util.Map;

public class Wago750 {
    @Id
    public String id;

    public String payload;
    public Map<String, Object> headers;
    public Long timestamp;
    public int[] binaryArray;



    public Wago750() {}

    public Wago750(String data, Map<String,Object> headers, Long timestamp) {

        this.payload = data;
        this.headers = headers;
        this.timestamp = timestamp;
        this.binaryArray = convertToBinArr();
    }

    // liefert bool 16bit array version des strings mit [the lowest bit - highest bit]
    public int[] convertToBinArr(){

        try {
            int[] binArray = new int[16]; // wir haben 14 leds

            // remove brackets from string
            String data = payload;
            data = data.replace("[", "");
            data = data.replace("]", "");

            // convert to binary number representation string
            int number = Integer.parseInt(data);
            String binstring = Integer.toBinaryString(number);
            //System.out.println("new wago: --payload: " + payload + "  --parsed number: " + number + "  --bin string: " + binstring);

            // füge erstmal alle string bits dem array hinzu
            int arrayindex = 0;
            for (int i = binstring.length() - 1; i >= 0; i--) {
                if (binstring.charAt(i) == '1') {
                    binArray[arrayindex] = 1;
                } else {
                    binArray[arrayindex] = 0;
                }
                arrayindex++;
            }
            // padding
            while (arrayindex < 16) {
                binArray[arrayindex] = 0;
                arrayindex++;
            }
            return binArray;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("etwas ging schief bei der konversion zum binary Array...");
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[payload=%s, headers=%s, timestamp=%s, ]",
                payload,headers, timestamp);
    }

}

