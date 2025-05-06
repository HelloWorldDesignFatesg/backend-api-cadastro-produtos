package com.example.api.dto;

import java.util.UUID;
import org.springframework.hateoas.RepresentationModel;
import com.example.api.model.Produto;

/**
 * DTO para expor os dados de Produto na API, desacoplando a entidade da camada de apresentação.
 */
public class ProdutoDTO extends RepresentationModel<ProdutoDTO> {
    
    private UUID id;
    private String nome;
    private Double preco;
    
    public ProdutoDTO() {
    }
    
    public ProdutoDTO(UUID id, String nome, Double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }
    
    // Método para converter uma entidade Produto em ProdutoDTO
    public static ProdutoDTO fromEntity(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }
    
    // Getters e Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
