package com.dev.repository.service;

import java.util.List;

import com.dev.backend.entity.Marca;
import com.dev.backend.exception.InfoException;

public interface IMarcaService {
    List<Marca> buscarTodos();

    Marca inserir(Marca marca) throws InfoException;

    Marca alterar(Long id, Marca marca) throws InfoException;

    void excluir(Long id) throws InfoException;
}
