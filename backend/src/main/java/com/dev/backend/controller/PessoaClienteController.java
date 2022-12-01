package com.dev.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.dev.backend.dto.ClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.exception.InfoException;
import com.dev.repository.service.IClienteService;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class PessoaClienteController {

    private final IClienteService clienteService;

    @PostMapping("/cadastrar")
    @CrossOrigin("http://localhost:3000")
    public Pessoa inserir(@RequestBody ClienteRequestDTO clienteRequestDTO) throws InfoException {
        return clienteService.inserir(clienteRequestDTO);
    }
}
