package softqarequali_seminar.seminarprojekt;

import org.springframework.data.mongodb.repository.MongoRepository;


/**Hier wird die eine custom funktion angegeben die ich für das entsprechende Repository habe
 * es gibt solch ein interface wieder für jede collection*/
public interface S7_1500_Differenz_Repository extends MongoRepository<S7_1500_Differenz, String> {
    public S7_1500_Differenz findTopByOrderByTimestampDesc();
}

