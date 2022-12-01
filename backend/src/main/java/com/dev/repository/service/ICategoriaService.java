package com.dev.repository.service;

import java.util.List;

import com.dev.backend.entity.Categoria;
import com.dev.backend.exception.InfoException;

public interface ICategoriaService {
    List<Categoria> buscarTodos();

    Categoria inserir(Categoria categoria) throws InfoException;

    Categoria alterar(Long id, Categoria categoria) throws InfoException;

    void excluir(Long id) throws InfoException;
}
