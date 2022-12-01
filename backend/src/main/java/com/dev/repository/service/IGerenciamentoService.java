package com.dev.repository.service;

import com.dev.backend.entity.Pessoa;

public interface IGerenciamentoService {
    String solicitarCodigo(String email);

    String alterarSenha(Pessoa pessoa);
}
