package com.dev.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dev.backend.entity.Categoria;
import com.dev.backend.entity.Marca;
import com.dev.backend.entity.Produto;
import com.dev.backend.exception.InfoException;
import com.dev.backend.repository.CategoriaRepository;
import com.dev.backend.repository.MarcaRepository;
import com.dev.backend.repository.ProdutoRepository;
import com.dev.backend.util.UtilProduto;
import com.dev.repository.service.IProdutoService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService implements IProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(Long id) throws InfoException {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (optionalProduto.isPresent()) {
            return optionalProduto.get();
        } else {
            throw new InfoException("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }

    public Produto inserir(Produto produto) {		
		produto.setDataCriacao(new Date());
		Produto produtoNovo = produtoRepository.saveAndFlush(produto);
		return produtoNovo;
	}
   

    @Override
    public Produto alterar(Long id, Produto produto) throws InfoException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(produto.getCategoria().getId());
        Optional<Marca> marcaOptional = marcaRepository.findById(produto.getMarca().getId());

        if (categoriaOptional == null) {
            throw new InfoException("Categoria não encontrada", HttpStatus.BAD_REQUEST);
        } else if (marcaOptional == null) {
            throw new InfoException("Marca não encontrada", HttpStatus.BAD_REQUEST);
        } else {
            if (produtoOptional.isPresent()) {
                Produto produtoBuilder = Produto.builder()
                        .id(id)
                        .nome(produto.getNome() != null ? produto.getNome() : null)
                        .descricao(produto.getDescricao() != null ? produto.getDescricao() : null)
                        .valorCusto(produto.getValorCusto() != null ? produto.getValorCusto() : null)
                        .valorVenda(produto.getValorVenda() != null ? produto.getValorVenda() : null)
                        .marca(produto.getMarca() != null ? produto.getMarca() : null)
                        .categoria(produto.getCategoria() != null ? produto.getCategoria() : null)
                        .build();

                if (UtilProduto.validarProduto(produtoBuilder)) {
                    produtoRepository.save(produtoBuilder);
                }
                return produtoBuilder;
            } else {
                throw new InfoException("Produto não encontrado", HttpStatus.NOT_FOUND);
            }
        }
    }

    @Override
    public void excluir(Long id) throws InfoException {
        Optional<Produto> produto = produtoRepository.findById(id);

        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
        } else {
            throw new InfoException("Produto não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
