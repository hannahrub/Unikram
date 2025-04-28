package softqarequali_seminar.seminarprojekt;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface S7_1500_Differenz_Repository extends MongoRepository<S7_1500_Differenz, String> {
    public S7_1500_Differenz findTopByOrderByTimestampDesc();
}

