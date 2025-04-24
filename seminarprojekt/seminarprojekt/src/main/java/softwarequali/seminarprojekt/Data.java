package softwarequali.seminarprojekt;

import org.springframework.data.annotation.Id;

public class Data {
    @Id
    public String id;

    public String number;


    public Data() {}

    public Data(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format(
                "Data[number=%s, id=%s]",
                number, id);
    }

}

