package com.dev.backend.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dev.backend.entity.Pessoa;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {
	private String chaveSecreta = "chaveSecretaParaGerarToken";
	private int validadeToken = 900000;
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	
	public String gerarTokenUsername(Pessoa pessoa) {
		return Jwts.builder().setSubject(pessoa.getUsername()).setIssuedAt( new Date()).signWith(SignatureAlgorithm.HS512, chaveSecreta).setExpiration(new Date(new Date().getTime()+validadeToken)).compact();
	}
	
	public String getEmailToken(String token) {
		return Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validarToken(String token, HttpServletRequest request) {
		try {
			Jwts.parser().setSigningKey(chaveSecreta).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			logger.error("Assinatura inválida", e.getMessage());
		}catch(ExpiredJwtException e) {
			logger.error("Token expirado", e.getMessage());
			request.setAttribute("validacaoToken", "Token expirado");
		}catch(UnsupportedJwtException e ) {
			logger.error("Token não suportado", e.getMessage());
		}
		return false;
	}
}