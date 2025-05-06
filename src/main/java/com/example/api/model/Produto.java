package com.example.api.model;

import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class Produto extends RepresentationModel<Produto> {

    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    private String nome;
    private Double preco;

    public Produto() {
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public UUID getId() {
        return id;
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
    
    // Método para atualizar os campos do produto (para operação PUT)
    public void update(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
