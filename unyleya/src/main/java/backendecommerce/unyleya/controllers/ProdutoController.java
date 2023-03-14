package backendecommerce.unyleya.controllers;

import backendecommerce.unyleya.exceptions.ResourceNotFoundException;
import backendecommerce.unyleya.model.Produto;
import backendecommerce.unyleya.repository.ProdutoRepository;
import backendecommerce.unyleya.service.SequenceGeneratorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class ProdutoController {
    
     @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    
    @PostMapping("/produtos")
    public Produto createProducts(@Valid @RequestBody Produto produto){
        produto.setCodigo(sequenceGeneratorService.generateSequence(Produto.SEQUENCE_NAME));
        return produtoRepository.save(produto);
    }
    
    @GetMapping("/produtos")
    public List<Produto> getAllProducts(){
        return produtoRepository.findAll();
    }
    
    @GetMapping("/produtos/{codigo}")
    public ResponseEntity<Produto> getProductById(@PathVariable(value="codigo") 
    Long codigoProduto) throws ResourceNotFoundException{
        Produto produto = produtoRepository.findById(codigoProduto)
        .orElseThrow(()-> new ResourceNotFoundException("Produto não encontrado!"));
        
        return ResponseEntity.ok().body(produto);
    }
    
    @DeleteMapping("/produtos/{codigo}")
    public Map<String,Boolean> deleteProduct(@PathVariable (value="codigo") Long codigoProduto)
    throws ResourceNotFoundException{
        
        Produto produto = produtoRepository.findById(codigoProduto)
        .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado!"));
        
        produtoRepository.delete(produto);
        Map<String,Boolean> response = new HashMap<>();
        response.put("Produto removido:",Boolean.TRUE);
        return response;
    }
    
    @PutMapping("/produtos/{codigo}")
    public ResponseEntity<Produto> updateProduct(@PathVariable(value="codigo") 
        Long produtoId, @Valid @RequestBody Produto produtoDetails) throws ResourceNotFoundException{
        
        Produto produto = produtoRepository.findById(produtoId).orElseThrow(()-> 
        new ResourceNotFoundException("Produto não encontrado!"));
        
        produto.setNome(produtoDetails.getNome());
        produto.setPreco(produtoDetails.getPreco());
       
        
        final Produto updatedProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(updatedProduto);
        
    }
}
