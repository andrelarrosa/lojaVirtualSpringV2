package com.dev.backend.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dev.backend.entity.Cidade;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name="pessoa")
@Data
public class Pessoa implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	private String cpf;
	private String email;
	private String codigoRecuperacaoSenha;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataEnvioCodigo;
	private String senha;
	private String endereco;
	private String cep;
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permissaoPessoa;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
