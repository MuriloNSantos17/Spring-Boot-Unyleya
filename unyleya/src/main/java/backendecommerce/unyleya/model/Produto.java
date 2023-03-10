package backendecommerce.unyleya.model;


import jakarta.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Murilo
 */
@Document(collection = "Produtos")
public class Produto {
    
    @Transient 
    public static final String SEQUENCE_NAME = "users_sequence";
    
    @Id
    private long codigo;
     
    @NotNull
    @Size(max=100)
    @Indexed(unique = true)
    private String nome;
    
    @NotNull
    @Indexed(unique = true)
    private float preco;
    
    public Produto(){
        
    }
    
    public Produto(String nome, float preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    
    public long getCodigo() {
        return codigo;
    }

    
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    
    
}
