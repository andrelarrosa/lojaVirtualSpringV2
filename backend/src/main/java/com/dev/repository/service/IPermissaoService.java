package com.dev.repository.service;

import java.util.List;

import com.dev.backend.entity.Permissao;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.exception.InfoException;

public interface IPermissaoService {
    List<Permissao> buscarTodos();

    Permissao inserir(Permissao permissao) throws InfoException;

    Permissao alterar(Long id, Permissao permissao) throws InfoException;

    void excluir(Long id) throws InfoException;

    void vincularPessoaPermissaoCliente(Pessoa pessoa) throws InfoException;
}
