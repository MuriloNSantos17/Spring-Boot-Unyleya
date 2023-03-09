
package backendposts.backendpostunyleya.controllers;

import backendposts.backendpostunyleya.exceptions.ResourceNotFoundException;
import backendposts.backendpostunyleya.model.Publicacao;
import backendposts.backendpostunyleya.repository.PublicacaoRepository;
import backendposts.backendpostunyleya.service.SequenceGeneratorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/publicacoes")
    public Publicacao createPublicacao(@Valid @RequestBody Publicacao publicacao){
        publicacao.setId(sequenceGeneratorService.generateSequence(Publicacao.SEQUENCE_NAME));
        return publicacaoRepository.save(publicacao);
    }
    
    /*
        @GetMapping("/publicacoes/{id}")
        public ResponseEntity<Publicacao> getPublicacaoById(@PathVariable(value="id"))
        throws ResourceNotFoundException{
            Publicacao publicacao = publicacaoRepository.findBy(publicacaoId).
        }
    */
}