package com.dev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Cidade;
import com.dev.backend.entity.Estado;
import com.dev.backend.exception.InfoException;
import com.dev.backend.repository.CidadeRepository;
import com.dev.backend.repository.EstadoRepository;
import com.dev.backend.util.UtilCidade;
import com.dev.repository.service.ICidadeService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService implements ICidadeService {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public List<Cidade> buscarTodos() {
        return cidadeRepository.findAll();
    }

    public Cidade inserir(Cidade cidade) {		
		cidade.setDataCriacao(new Date	());
		Cidade cidadeNovo = cidadeRepository.saveAndFlush(cidade);
		return cidadeNovo;
	}

    @Override
    public Cidade alterar(Long id, Cidade cidade) throws InfoException {
        Optional<Cidade> cidadeOptional = cidadeRepository.findById(id);
        Optional<Estado> estadoOptional = estadoRepository.findById(cidade.getEstado().getId());

        if (estadoOptional == null) {
            throw new InfoException("Estado não encontrado", HttpStatus.BAD_REQUEST);
        } else {
            if (cidadeOptional.isPresent()) {
                Cidade cidadeBuilder = Cidade.builder()
                        .id(id)
                        .nome(cidade.getNome() != null ? cidade.getNome() : null)
                        .estado(cidade.getEstado() != null ? cidade.getEstado() : null)
                        .build();

                if (UtilCidade.validarCidade(cidadeBuilder)) {
                    cidadeRepository.save(cidadeBuilder);
                }
                return cidadeBuilder;
            } else {
                throw new InfoException("Cidade não encontrada", HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public void excluir(Long id) throws InfoException {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        if (cidade.isPresent()) {
            cidadeRepository.delete(cidade.get());
        } else {
            throw new InfoException("Cidade não encontrada", HttpStatus.NOT_FOUND);
        }
    }
}
