package softqarequali_seminar.seminarprojekt;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface S7_1500_Soll_Repository extends MongoRepository<S7_1500_Soll, String> {
    public S7_1500_Soll findTopByOrderByTimestampDesc();
}

