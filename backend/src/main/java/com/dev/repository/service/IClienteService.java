package com.dev.repository.service;

import com.dev.backend.dto.ClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.exception.InfoException;

public interface IClienteService {
    Pessoa inserir(ClienteRequestDTO clienteRequestDTO) throws InfoException;
}
