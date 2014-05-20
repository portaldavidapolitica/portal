package br.com.pvp.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import br.com.pvp.dao.DAOPermissaoUsuario;
import br.com.pvp.dao.DAOUsuario;
import br.com.pvp.entidades.PermissaoUsuario;
import br.com.pvp.entidades.Usuario;

/**
 * Exemplo de authentication provider
 * @author kicolobo
 */
public class SFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private DAOUsuario daoUsuario;

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(final DAOUsuario dao) {
		daoUsuario = dao;
	}

	@Autowired
	private DAOPermissaoUsuario daoPermissao;

	public DAOPermissaoUsuario getDaoPermissao() {
		return daoPermissao;
	}

	public void setDaoPermissao(final DAOPermissaoUsuario dao) {
		daoPermissao = dao;
	}

	public Authentication authenticate(final Authentication auth) throws AuthenticationException {
		final UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) auth;
		final String username = token.getName();
		final String senha = token.getCredentials() != null ? token.getCredentials().toString() : null;
		final Usuario usuario = getDaoUsuario().getUsuario(username, senha);
		if (usuario == null) {
			return null;
		}
		final List<PermissaoUsuario> permissoes = getDaoPermissao().getPermissoesUsuario(usuario);
		final SFAuthentication resultado = new SFAuthentication(usuario, permissoes);
		resultado.setAuthenticated(usuario != null);
		return resultado;
	}

	public boolean supports(final Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
