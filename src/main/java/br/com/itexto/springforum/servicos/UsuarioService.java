package br.com.itexto.springforum.servicos;

import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioService {
	
	public String getNomeUsuarioLogado() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
    
}
