package softqarequali_seminar.seminarprojekt;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface Wago750_Repository extends MongoRepository<Wago750, String> {

    public Wago750 findTopByOrderByTimestampDesc();
}
