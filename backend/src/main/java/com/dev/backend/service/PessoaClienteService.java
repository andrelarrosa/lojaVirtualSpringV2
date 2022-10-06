package com.dev.backend.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.backend.dto.PessoaClienteRequestDTO;
import com.dev.backend.entity.Pessoa;
import com.dev.backend.repository.PessoaClienteRepository;

@Service
public class PessoaClienteService {

	@Autowired
	private PessoaClienteRepository pessoaClienteRepository;
	
	@Autowired
	private PermissaoPessoaService permissaoPessoaService;
	
	@Autowired
	private EmailService emailService;
	
	public Pessoa registrar(PessoaClienteRequestDTO pessoaClienteRequestDTO) {
		Pessoa pessoa = new PessoaClienteRequestDTO().converter(pessoaClienteRequestDTO);
		pessoa.setDataCriacao(new Date());
		Pessoa pessoaNovo = pessoaClienteRepository.saveAndFlush(pessoa);
		permissaoPessoaService.vincularPessoaPermissaoCliente(pessoaNovo);
		Map<String, Object> propMap = new HashMap<String, Object>();
		propMap.put("nome", pessoaNovo.getNome());
		propMap.put("mensagem", "o registro na loja foi realizado com sucesso");
		emailService.enviarEmailTemplate(pessoaNovo.getEmail(), "Cadastro na loja", propMap);
		// emailService.enviarEmailTexto(pessoaNovo.getEmail(), "Cadastro na loja", "o registro na loja foi realizado com sucesso");
		return pessoaNovo;
	}
	
	

}
