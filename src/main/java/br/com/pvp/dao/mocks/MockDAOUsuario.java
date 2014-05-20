package br.com.pvp.dao.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.pvp.dao.DAOUsuario;
import br.com.pvp.entidades.Usuario;

@Component("daoUsuario")
public class MockDAOUsuario extends MockDAOBase<Usuario> implements DAOUsuario {

	private List<Usuario> todos;

	@Override
	public Usuario get(final Long id) {
		for (final Usuario usuario : list(-1, -1)) {
			if (usuario.getId() == id) {
				return usuario;
			}
		}
		return null;
	}

	public Usuario getUsuario(final String login, final String senha) {
		final Usuario resultado = null;
		for (final Usuario usuario : list(-1, -1)) {
			if (usuario.getLogin().equals(login) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		return resultado;
	}

	public Usuario getUsuario(final String login) {
		for (final Usuario usuario : list(-1, -1)) {
			if (usuario.getLogin().equals(login)) {
				return usuario;
			}
		}
		return null;
	}

	@Override
	public List<Usuario> list(final int offset, final int max) {
		if (todos == null) {
			todos = new ArrayList<Usuario>();
			final String[] nomes = { "Henrique Lobo", "Kico", "Josué", "Amadeus", "Carvalhinho", "John McCarty" };
			for (int i = 0; i < nomes.length; i++) {
				final Usuario usuario = new Usuario();
				usuario.setNome(nomes[i]);
				usuario.setId(i + 1);
				usuario.setEmail(nomes[i] + "@spring.com");
				usuario.setEmail(usuario.getEmail().toLowerCase().replaceAll(" ", "_"));
				usuario.setLogin(nomes[i].replaceAll(" ", "_").toLowerCase());
				usuario.setSenha(usuario.getLogin());
				usuario.setTwitter("@" + usuario.getLogin());
				todos.add(usuario);
			}
		}
		return todos;
	}

	@Override
	public void persistir(final Usuario obj) {
		final List<Usuario> usuarios = list(-1, -1);
		if (!usuarios.contains(obj)) {
			usuarios.add(obj);
		}
	}

	@Override
	public void excluir(final Usuario obj) {
		list(-1, -1).remove(obj);
	}

	@Override
	public Class getClazz() {
		return Usuario.class;
	}

}
