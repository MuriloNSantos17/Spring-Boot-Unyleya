package backendecommerce.unyleya.repository;


import backendecommerce.unyleya.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, Long> {
    
}
