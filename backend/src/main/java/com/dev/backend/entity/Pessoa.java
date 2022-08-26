package com.dev.backend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dev.backend.entity.Cidade;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="pessoa")
@Data
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private String endereco;
	private String cep;
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@Setter(value = AccessLevel.NONE)
	private List<PermissaoPessoa> permissaoPessoa;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCriacao;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	public void setPermissaoPessoa(List<PermissaoPessoa> permissaoPessoas) {
		for(PermissaoPessoa permissaoPessoa:permissaoPessoas) {
			permissaoPessoa.setPessoa(this);
		}
		this.permissaoPessoa = permissaoPessoas;
	}
}