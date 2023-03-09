package backendposts.backendpostunyleya.model;

import jakarta.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document (collection = "Publicacao")
public class Publicacao {
    
    @Transient //Sequência de usuários não vai ter persistência de usuários
    public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
    private long id;
    
    @NotNull
    @Size(max=100)
    private String titulo;
    private String doi;
    
    @NotNull
    @Size(max=100)
    @Indexed(unique = true)
    private String nomeAutor;
    
    public Publicacao(){
        
    }
    
    public Publicacao(String titulo, String doi, String nomeAutor){
        this.titulo = titulo;
        this.doi = doi;
        this.nomeAutor = nomeAutor;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

 
    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
    
    @Override
    public String toString(){
        return "Employee [id ="+id+", firstName="+titulo +", lastName"+
        titulo+"]";
    
    }
}
