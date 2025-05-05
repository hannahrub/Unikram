package softqarequali_seminar.seminarprojekt;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface S7_1500_Ist_Repository extends MongoRepository<S7_1500_Ist, String> {
    public S7_1500_Ist findTopByOrderByTimestampDesc();
}

