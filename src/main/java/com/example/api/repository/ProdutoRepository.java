package com.example.api.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
