package com.example.api.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.example.api.exceptions.RecursoNaoEncontradoException;
import com.example.api.model.Produto;
import com.example.api.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (!produtos.isEmpty()) {
            // Adiciona self link para cada produto na listagem (conforme sua lógica original)
            for (Produto p : produtos) {
                UUID id = p.getId();
                p.add(org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
                        .linkTo(org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
                        .methodOn(com.example.api.controller.ProdutoController.class).buscarProduto(id))
                        .withSelfRel());
            }
        }
        return produtos;
    }
    
    public Produto buscarPorId(UUID id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado"));
    }
    
    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public Produto editarProduto(UUID id, Produto produtoDetalhes) {
        Produto produtoExistente = buscarPorId(id);
        produtoExistente.update(produtoDetalhes);
        return produtoRepository.save(produtoExistente);
    }
    
    public void deletarProduto(UUID id) {
        if (!produtoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado");
        }
        produtoRepository.deleteById(id);
    }
}
