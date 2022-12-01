package com.dev.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.backend.entity.Imagem;

import java.util.List;

public interface ProdutoImagensRepository extends JpaRepository<Imagem, Long> {
    List<Imagem> findByProdutoId(Long id);
}
