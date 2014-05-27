package br.com.itexto.springforum.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.dao.DAOTopico;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Topico;
import br.com.itexto.springforum.entidades.Usuario;

@Controller("usuario")
@SessionAttributes("usuario")
public class UsuarioController {

	@Autowired
	private DAOUsuario daoUsuario;

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(final DAOUsuario dao) {
		daoUsuario = dao;
	}

	@Autowired
	private DAOTopico daoTopico;

	public DAOTopico getDaoTopico() {
		return daoTopico;
	}

	public void setDaoTopico(final DAOTopico dao) {
		daoTopico = dao;
	}

	/**
	 * Exemplo de como lidar com requisições com variáveis embutidas.
	 * @param id
	 * @return
	 */
	@RequestMapping("/usuario/show/{id}")
	public ModelAndView usuario(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		System.out.println("Vou expor o usuario");
		final Usuario usuario = getDaoUsuario().get(id);
		System.out.println("Encontrei o usuario: " + usuario.getNome());
		mav.getModel().put("usuario", usuario);
		mav.setViewName("usuario/show");
		return mav;
	}

	@RequestMapping("/usuario/autenticado")
	public ModelAndView infoAutenticado(@ModelAttribute("usuario")
	final Usuario usuario) {
		final ModelAndView mav = new ModelAndView("usuario/show");
		mav.getModel().put("usuario", usuario);
		return mav;
	}

	@RequestMapping("/usuario/avatar/{login}")
	@ResponseBody
	public byte[] avatar(@PathVariable("login")
	final String login) throws IOException {
		File arquivo = new File("/springForum/avatares/" + login + ".png");

		if (!arquivo.exists()) {
			arquivo = new File("/springForum/avatares/avatar.png");
		}

		final byte[] resultado = new byte[(int) arquivo.length()];
		final FileInputStream input = new FileInputStream(arquivo);
		input.read(resultado);
		input.close();

		return resultado;
	}

	@RequestMapping("/usuario/posts/{login}")
	public String topicosUsuario(@PathVariable("login")
	final String login, final Map<String, Object> model) {
		model.put("topicos", getDaoTopico().getTopicosPorAutor(getDaoUsuario().getUsuario(login)));

		return "usuario/posts";
	}

	@RequestMapping("/usuario/postsJSON/{login}")
	@ResponseBody
	public List<Topico> topicosUsuarioJson(@PathVariable("login")
	final String login) {
		return getDaoTopico().getTopicosPorAutor(getDaoUsuario().getUsuario(login));
	}

	public String getNomeUsuarioLogado() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
