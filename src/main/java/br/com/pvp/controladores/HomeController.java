package br.com.pvp.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.pvp.dao.DAOAssunto;
import br.com.pvp.dao.DAOPermissaoUsuario;
import br.com.pvp.dao.DAOTopico;
import br.com.pvp.dao.DAOUsuario;
import br.com.pvp.dao.mocks.MockDAOAssunto;
import br.com.pvp.entidades.Usuario;

// import br.com.pvp.entidades.Assunto;

@Controller
public class HomeController {

	@Autowired
	private DAOUsuario daoUsuario;
	@Autowired
	private DAOTopico daoTopico;
	@Autowired
	private DAOAssunto daoAssunto;
	@Autowired
	private DAOPermissaoUsuario daoPermissaoUsuario;

	public DAOPermissaoUsuario getDaoPermissaoUsuario() {
		return daoPermissaoUsuario;
	}

	public void setDaoPermissaoUsuario(final DAOPermissaoUsuario dao) {
		daoPermissaoUsuario = dao;
	}

	public DAOUsuario getDaoUsuario() {
		return daoUsuario;
	}

	public void setDaoUsuario(final DAOUsuario daoUsuario) {
		this.daoUsuario = daoUsuario;
	}

	public DAOTopico getDaoTopico() {
		return daoTopico;
	}

	public void setDaoTopico(final DAOTopico daoTopico) {
		this.daoTopico = daoTopico;
	}

	public DAOAssunto getDaoAssunto() {
		return daoAssunto;
	}

	public void setDaoAssunto(final MockDAOAssunto daoAssunto) {
		this.daoAssunto = daoAssunto;
	}

	/**
	 * A anotação @RequestMapping identifica qual a URL relacionada ao método (action)
	 * a ser executado.
	 *
	 * Neste exemplo, vemos que a URL padrão para nosso sistema, o "/" sempre apontará para
	 * esta chamada.
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(final Map<String, Object> model) {
		model.put("assuntos", getDaoAssunto().list(0, 100));
		model.put("usuarios", getDaoUsuario().list(0, 100));
		return "index";
	}

	@RequestMapping("/registro")
	public String registro(final Map<String, Object> model) {
		if (model.get("usuario") == null) {
			final Usuario usr = new Usuario();

			model.put("usuario", usr);
		}
		return "registro";
	}

	@RequestMapping(value = "/executarRegistro", method = RequestMethod.POST)
	public String executarRegistro(@Valid
	final Usuario usuario, final BindingResult bindingResult, final HttpSession sessao, @RequestParam(value = "avatar", required = false)
	final MultipartFile avatar) {
		if (bindingResult.hasErrors()) {
			final Map<String, Object> model = new HashMap<String, Object>();
			model.put("usuario", usuario);
			return registro(model);
		}
		getDaoUsuario().persistir(usuario);
		getDaoPermissaoUsuario().addRole("ROLE_MEMBRO", usuario);
		if (!avatar.isEmpty()) {
			processarAvatar(usuario, avatar);
		}

		sessao.setAttribute("usuario", usuario);
		return "redirect:/";
	}

	private void processarAvatar(final Usuario usuario, final MultipartFile avatar) {
		final File diretorio = new File("/springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			final FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + usuario.getLogin() + ".png");
			arquivo.write(avatar.getBytes());
			arquivo.close();
		} catch (final IOException ex) {

		}

	}

}
