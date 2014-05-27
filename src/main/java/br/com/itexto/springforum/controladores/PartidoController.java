package br.com.itexto.springforum.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.Enum.EnumStatusPartido;
import br.com.itexto.springforum.Enum.EnumStatusPublicacao;
import br.com.itexto.springforum.dao.DAOPartido;
import br.com.itexto.springforum.dao.DAOPolitico;
import br.com.itexto.springforum.dao.DAOStatusPartido;
import br.com.itexto.springforum.entidades.Partido;
import br.com.itexto.springforum.entidades.Politico;

@Controller
public class PartidoController {

	@Autowired
	private DAOPartido daoPartido;

	@Autowired
	private DAOPolitico daoPolitico;

	@Autowired
	private DAOStatusPartido daoStatusPartido;

	@RequestMapping("/cadastro/partido")
	public ModelAndView partido(final ModelAndView model) {

		if (model.getModelMap().get("partido") == null) {
			model.addObject("partido", new Partido());
		}
		if (model.getModelMap().get("partidos") == null && model.getModelMap().get("exibePartidos") == null) {
			model.addObject("partidos", daoPartido.getPartidosAprovados());
		}

		model.setViewName("cadastro/partido");
		return model;
	}

	@RequestMapping("partido/gerenciarPartidos")
	public ModelAndView gerenciar(final ModelAndView mav) {

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.getPartidosEmAprovacao());
		}

		mav.setViewName("partido/gerenciarPartidos");
		return mav;
	}

	@RequestMapping(value = "cadastro/cadastrarPartido/{idPartido}", method = RequestMethod.POST)
	public ModelAndView partido(@PathVariable("idPartido")
	final Long idPartido, @RequestParam(value = "nome")
	final String nome, @RequestParam(value = "sigla")
	final String sigla, @RequestParam(value = "descricao")
	final String descricao, @RequestParam(value = "avatar", required = false)
	final MultipartFile avatar, @RequestParam(value = "acao")
	final String acao) {

		final ModelAndView model = new ModelAndView();
		Partido partido = new Partido();

		if (acao.equals("editar")) {
			partido = daoPartido.get(idPartido);
		} else {
			partido.setDataCriacao(new Date());
			partido.setStatusPartido(daoStatusPartido.get((long) EnumStatusPartido.AGUARDANDO_APROVACAO.getAcao()));
		}

		partido.setNome(nome);
		partido.setSigla(sigla);
		partido.setDescricao(descricao);
		try {
			daoPartido.persistir(partido);
			processaPartidoAvatar(avatar, partido);
		} catch (final Exception e) {
			model.addObject("partido", partido);
			model.addObject("mensagem", "Erro ao cadastrar o partido, motivo do erro: Partido com o nome ou a sigla digitado já cadastrados");
			if (acao.equals("editar")) {
				model.addObject("exibePartidos", false);
			}
			return partido(model);
		}

		model.addObject("mensagem", "Partido Cadastrado com Sucesso!");
		return partido(model);
	}

	@RequestMapping(value = "cadastro/partido/editar/{id}")
	public ModelAndView editar(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		mav.addObject("partido", daoPartido.get(id));
		mav.addObject("exibePartidos", false);
		mav.addObject("acao", "editar");
		return partido(mav);
	}

	@RequestMapping(value = "cadastro/partido/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Partido partido = daoPartido.get(id);
		final List<Politico> politicos = daoPolitico.getPoliticosPorPartido(partido);

		if (politicos != null && politicos.size() > 0) {
			mav.addObject("mensagem", "Existem políticos associados a esse partido!");
		} else {
			try {
				daoPartido.excluir(partido);
			} catch (final Exception e) {
				mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
			}
			mav.addObject("mensagem", "Partido excluido com Sucesso!");
		}
		mav.setViewName("redirect:/cadastro/partido");
		return mav;
	}

	private void processaPartidoAvatar(final MultipartFile avatar, final Partido partido) {
		final File diretorio = new File("/springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			final FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + partido.getSigla() + ".png");
			arquivo.write(avatar.getBytes());
			arquivo.close();
		} catch (final IOException ex) {

		}
	}

	@RequestMapping(value = "partido/gerenciarPartidos/aprovar/{id}")
	public ModelAndView aprovarPartido(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Partido partido = daoPartido.get(id);
		partido.setStatusPartido(daoStatusPartido.get((long) EnumStatusPublicacao.APROVADO.getAcao()));
		try {
			daoPartido.persistir(partido);
			mav.addObject("mensagem", "Partido aprovado com sucesso!");
		} catch (final Exception e) {
			mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
		}
		return gerenciar(mav);
	}

	@RequestMapping(value = "partido/gerenciarPartidos/reprovar/{id}")
	public ModelAndView reprovarPublicacao(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Partido partido = daoPartido.get(id);
		partido.setStatusPartido(daoStatusPartido.get((long) EnumStatusPublicacao.REPROVADO.getAcao()));
		try {
			daoPartido.persistir(partido);
			mav.addObject("mensagem", "Partido reprovado com sucesso!");
		} catch (final Exception e) {
			mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
		}

		return gerenciar(mav);
	}

}
