package backendposts.backendpostunyleya.repository;

import backendposts.backendpostunyleya.model.Publicacao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacaoRepository extends MongoRepository<Publicacao, Long> {
    
}
