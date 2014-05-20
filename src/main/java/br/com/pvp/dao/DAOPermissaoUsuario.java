package br.com.pvp.dao;

import java.util.List;

import br.com.pvp.entidades.PermissaoUsuario;
import br.com.pvp.entidades.Usuario;

public interface DAOPermissaoUsuario extends DAOBase<PermissaoUsuario> {

	public List<PermissaoUsuario> getPermissoesUsuario(Usuario usuario);

	public void addRole(String role, Usuario usuario);

}
