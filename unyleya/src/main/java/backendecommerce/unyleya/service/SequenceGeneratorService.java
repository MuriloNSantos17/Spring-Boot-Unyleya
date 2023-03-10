package backendecommerce.unyleya.service;

import backendecommerce.unyleya.model.DatabaseSequence;
import java.util.Objects;

import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;








@Service
public class SequenceGeneratorService {
    
    private MongoOperations mongoOperations;
    
    public SequenceGeneratorService(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }
    
    public long generateSequence(String seqName){
        
      DatabaseSequence counter = mongoOperations.findAndModify(
        query(where("_id").is(seqName)),
        new Update().inc("seq", 1),
        new FindAndModifyOptions().returnNew(true).upsert(true),
        DatabaseSequence.class);
      
        return !Objects.isNull(counter) ? counter.getSeq() :1;
        
    }
}
