
package backendposts.backendpostunyleya.controllers;

import backendposts.backendpostunyleya.exceptions.ResourceNotFoundException;
import backendposts.backendpostunyleya.model.Publicacao;
import backendposts.backendpostunyleya.repository.PublicacaoRepository;
import backendposts.backendpostunyleya.service.SequenceGeneratorService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PublicacaoController {
    @Autowired
    private PublicacaoRepository publicacaoRepository;
    
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    
    @GetMapping("/publicacoes")
    public List<Publicacao> getAllPublicacoes(){
        return publicacaoRepository.findAll();
    }
    
    @GetMapping("/publicacoes/{id}")
    public ResponseEntity<Publicacao> getPublicacaoById(@PathVariable(value="id") 
    Long publicacaoId) throws ResourceNotFoundException{
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId)
        .orElseThrow(()-> new ResourceNotFoundException("Publicação não encontrada"));
        
        return ResponseEntity.ok().body(publicacao);
    }
    
    @PostMapping("/publicacoes")
    public Publicacao createPublicacao(@Valid @RequestBody Publicacao publicacao){
        publicacao.setId(sequenceGeneratorService.generateSequence(Publicacao.SEQUENCE_NAME));
        return publicacaoRepository.save(publicacao);
    }
    
    @DeleteMapping("/publicacoes/{id}")
    public Map<String,Boolean> deletePublicacao(@PathVariable (value="id") Long publicacaoId)
    throws ResourceNotFoundException{
        
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId)
        .orElseThrow(() -> new ResourceNotFoundException("Publicação não encontrada!"));
        
        publicacaoRepository.delete(publicacao);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
    
    @PutMapping("/publicacoes/{id}")
    public ResponseEntity<Publicacao> updatePublicacao(@PathVariable(value="id") 
        Long publicacaoId, @Valid @RequestBody Publicacao publicacaoDetails) throws ResourceNotFoundException{
        Publicacao publicacao = publicacaoRepository.findById(publicacaoId).orElseThrow(()-> 
        new ResourceNotFoundException("Publicação não encontrada!"));
        
        publicacao.setNomeAutor(publicacaoDetails.getNomeAutor());
        publicacao.setDoi(publicacaoDetails.getDoi());
        publicacao.setTitulo(publicacaoDetails.getTitulo());
        final Publicacao updatedPublicacao = publicacaoRepository.save(publicacao);
        return ResponseEntity.ok(updatedPublicacao);
        
    }
            
    
   
}
