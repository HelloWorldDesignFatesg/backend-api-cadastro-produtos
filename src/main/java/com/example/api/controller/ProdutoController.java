package com.example.api.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.example.api.dto.ProdutoDTO;
import com.example.api.model.Produto;
import com.example.api.service.ProdutoService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;
    
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }
    
    @GetMapping
    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        // Converte cada Produto para ProdutoDTO e adiciona os links HATEOAS
        return produtos.stream()
                       .map(this::buildProdutoDTO)
                       .collect(Collectors.toList());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarProduto(@PathVariable UUID id) {
        Produto produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(buildProdutoDTO(produto));
    }
    
    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvarProduto(produto);
        return ResponseEntity.ok(buildProdutoDTO(novoProduto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> editarProduto(@PathVariable UUID id, @RequestBody Produto produtoDetalhes) {
        Produto produtoAtualizado = produtoService.editarProduto(id, produtoDetalhes);
        return ResponseEntity.ok(buildProdutoDTO(produtoAtualizado));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable UUID id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Método auxiliar para construir o ProdutoDTO com os links HATEOAS.
     */
    private ProdutoDTO buildProdutoDTO(Produto produto) {
        ProdutoDTO dto = ProdutoDTO.fromEntity(produto);
        
        // Link para o próprio produto (self)
        dto.add(linkTo(methodOn(ProdutoController.class).buscarProduto(produto.getId())).withSelfRel());
        
        // Link para a listagem completa de produtos
        dto.add(linkTo(methodOn(ProdutoController.class).listarProdutos()).withRel("produtos"));
        
        return dto;
    }
}
