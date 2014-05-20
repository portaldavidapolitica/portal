package br.com.pvp.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import br.com.pvp.entidades.PermissaoUsuario;
import br.com.pvp.entidades.Usuario;

public class SFAuthentication implements Authentication {

	private final Usuario usuario;
	private boolean autenticado;

	public SFAuthentication(final Usuario usuario, final List<PermissaoUsuario> permissoes) {
		this.usuario = usuario;
		this.permissoes = permissoes;
	}

	public String getName() {
		return usuario != null ? usuario.getLogin() : null;
	}

	private final List<PermissaoUsuario> permissoes;

	public Collection<? extends GrantedAuthority> getAuthorities() {

		return permissoes;
	}

	public Object getCredentials() {
		return usuario != null ? usuario.getHashSenha() : null;
	}

	public Object getDetails() {
		return usuario;
	}

	public Object getPrincipal() {
		return usuario != null ? usuario.getLogin() : null;
	}

	public boolean isAuthenticated() {
		return autenticado;
	}

	public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
		autenticado = isAuthenticated;

	}

}
