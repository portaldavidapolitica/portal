package br.com.itexto.springforum.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.dao.DAOPartido;
import br.com.itexto.springforum.dao.DAOPolitico;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Politico;

@Controller
public class PoliticoController {

	@Autowired
	private DAOPartido daoPartido;

	@Autowired
	private DAOPolitico daoPolitico;

	@Autowired
	private DAOUsuario daoUsuario;

	@RequestMapping("/cadastro/politico")
	public ModelAndView politico(ModelAndView mav) {

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.list(0, 100));
		}
		if (mav.getModelMap().get("idPolitico") == null) {
			mav.addObject("politicos", daoPolitico.list(0, 100));
		}

		mav.setViewName("cadastro/politico");
		return mav;
	}

	@RequestMapping(value = "cadastro/cadastrarPolitico/{idPartido}", method = RequestMethod.POST)
	public ModelAndView politico(
			@PathVariable("idPartido") Long idPartido,
			@RequestParam(value = "nome") String nome,
			@RequestParam(value = "acao") String acao,
			@RequestParam(value = "idPolitico") String idPolitico,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar) {

		ModelAndView mav = new ModelAndView();
		Politico politico = new Politico();

//		 SecurityContextHolder.getContext().getAuthentication().getName();
//		 retorna o login do usuario.

		if (acao.equals("editar")) {
			politico = daoPolitico.get(Long.parseLong(idPolitico));
		}

		politico.setNome(nome);
		politico.setPartido(daoPartido.get(idPartido));
		try {
			daoPolitico.persistir(politico);
			processaPoliticoAvatar(avatar, politico);
		} catch (Exception e) {
			mav.addObject("mensagem", "Politico com o nome: " + nome
					+ " j� Cadastrado!");
			if (acao.equals("editar")) {
				return editar(politico.getId());
			} else {
				return politico(mav);
			}
		}

		mav.addObject("mensagem", "Politico Cadastrado com Sucesso!");
		return politico(mav);
	}

	@RequestMapping(value = "cadastro/politico/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();

		Politico politico = daoPolitico.get(id);

		mav.addObject("idPolitico", politico.getId());
		mav.addObject("partidos", daoPartido.list(0, 100));
		mav.addObject("nome", politico.getNome());
		mav.addObject("idPartido", politico.getPartido().getId());
		mav.addObject("acao", "editar");

		return politico(mav);
	}

	@RequestMapping(value = "cadastro/politico/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();

		Politico politico = daoPolitico.get(id);
		daoPolitico.excluir(politico);
		mav.addObject("mensagem", "Politico excluido com Sucesso!");

		return politico(mav);
	}

	private void processaPoliticoAvatar(MultipartFile avatar, Politico politico) {
		File diretorio = new File("/springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(
					diretorio.getAbsolutePath() + "/" + politico.getNome()
							+ ".png");
			arquivo.write(avatar.getBytes());
			arquivo.close();
		} catch (IOException ex) {

		}
	}

}
