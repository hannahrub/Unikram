package softqarequali_seminar.seminarprojekt;


/**Diese klasse stellt den Datentyp bereit der als Post request übergeben wird
 * wenn man auf der website einen modus wählt*/


public class FormEval {
    private long id;
    private String data;

    public FormEval() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
