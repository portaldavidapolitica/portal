package br.com.itexto.springforum.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.dao.DAOPermissaoUsuario;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.PermissaoUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Controller
public class GerenciamentoUsuariosController {

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOPermissaoUsuario daoPermissaoUsuario;

	@RequestMapping("/gerenciar/usuarios")
	public ModelAndView gerenciarUsuario(final ModelAndView mav) {

		if (mav.getModelMap().get("usuario") == null) {
			final List<Usuario> usuario = daoUsuario.list(0, 200);
			final List<Usuario> usuarios = new ArrayList<Usuario>();

			for (final Usuario user : usuario) {
				user.setPermissoesUsuario(daoPermissaoUsuario.getPermissoesUsuario(user));
				usuarios.add(user);
			}
			mav.addObject("usuarios", usuarios);
		}

		final List<String> roles = new ArrayList<String>();
		roles.add("MEMBRO");
		roles.add("MODERADOR");
		roles.add("ADMIN");
		mav.addObject("roles", roles);

		mav.setViewName("gerenciamento/gerenciarUsuarios");
		return mav;
	}

	@RequestMapping(value = "gerenciamento/gerenciarUsuarios/editarUsuario")
	public ModelAndView editarUsuario(@RequestParam(value = "idUsuario")
	final String id, @RequestParam(value = "permissoes")
	final String[] permissoes) {
		final ModelAndView mav = new ModelAndView();
		final Usuario usuario = daoUsuario.get(Long.parseLong(id));
		final List<PermissaoUsuario> listaPermissoes = daoPermissaoUsuario.getPermissoesUsuario(usuario);
		if (listaPermissoes.size() > 0) {
			for (final PermissaoUsuario perm : listaPermissoes) {
				try {
					daoPermissaoUsuario.excluir(perm);
				} catch (final Exception e) {
					mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
				}
			}
		}

		for (final String permissao : permissoes) {
			daoPermissaoUsuario.addRole("ROLE_" + permissao, usuario);
		}

		mav.addObject("mensagem", "Usuario editado com sucesso!");
		return gerenciarUsuario(mav);
	}

	@RequestMapping(value = "gerenciamento/gerenciarUsuarios/editar/{id}")
	public ModelAndView editar(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Usuario usuario = daoUsuario.get(id);
		usuario.setPermissoesUsuario(daoPermissaoUsuario.getPermissoesUsuario(usuario));
		mav.addObject("usuario", usuario);
		mav.addObject("acao", "editar");
		return gerenciarUsuario(mav);
	}

	@RequestMapping(value = "gerenciamento/gerenciarUsuarios/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Usuario usuario = daoUsuario.get(id);
		final List<PermissaoUsuario> permissaoUsuario = daoPermissaoUsuario.getPermissoesUsuario(usuario);
		try {
			if (permissaoUsuario.size() > 0) {
				for (final PermissaoUsuario permissao : permissaoUsuario) {
					daoPermissaoUsuario.excluir(permissao);
				}
			}
			daoUsuario.excluir(usuario);
			mav.addObject("mensagem", "Usuario excluido com sucesso!");
		} catch (final Exception e) {
			mav.addObject("usuario", usuario);
			mav.addObject("mensagem", "Erro ao excluir o usuario, motivo do erro: " + e.getCause() + " : " + e.getMessage());
		}
		return gerenciarUsuario(mav);
	}

}
