package br.com.itexto.springforum.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.Enum.EnumPoliticoAcao;
import br.com.itexto.springforum.Enum.EnumStatusPolitico;
import br.com.itexto.springforum.dao.DAOPartido;
import br.com.itexto.springforum.dao.DAOPolitico;
import br.com.itexto.springforum.dao.DAOPublicacao;
import br.com.itexto.springforum.dao.DAOStatusPolitico;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Politico;
import br.com.itexto.springforum.entidades.Publicacao;

@Controller
public class PoliticoController {

	@Autowired
	private DAOPartido daoPartido;

	@Autowired
	private DAOPolitico daoPolitico;

	@Autowired
	private DAOUsuario daoUsuario;

	@Autowired
	private DAOStatusPolitico daoStatusPolitico;

	@Autowired
	private DAOPublicacao daoPublicacao;

	@RequestMapping("/cadastro/politico")
	public ModelAndView politico(final ModelAndView mav) {

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.getPartidosAprovados());
		}
		if (mav.getModelMap().get("idPolitico") == null) {
			mav.addObject("politicos", daoPolitico.getPoliticosAprovados());
		}

		mav.setViewName("cadastro/politico");
		return mav;
	}

	@RequestMapping("politico/gerenciarPoliticos")
	public ModelAndView gerenciar(final ModelAndView mav) {

		if (mav.getModelMap().get("politicos") == null) {
			mav.addObject("politicos", daoPolitico.getPoliticosEmAprovacao());
		}

		mav.setViewName("politico/gerenciarPoliticos");
		return mav;
	}

	@RequestMapping(value = "cadastro/cadastrarPolitico/{idPartido}", method = RequestMethod.POST)
	public ModelAndView politico(@PathVariable("idPartido")
	final Long idPartido, @RequestParam(value = "nome")
	final String nome, @RequestParam(value = "acao")
	final String acao, @RequestParam(value = "idPolitico")
	final String idPolitico, @RequestParam(value = "avatar", required = false)
	final MultipartFile avatar) {

		final ModelAndView mav = new ModelAndView();
		Politico politico = new Politico();

		// SecurityContextHolder.getContext().getAuthentication().getName();
		// retorna o login do usuario.

		if (acao.equals("editar")) {
			politico = daoPolitico.get(Long.parseLong(idPolitico));
		} else {
			politico.setStatusPolitico(daoStatusPolitico.get((long) EnumStatusPolitico.AGUARDANDO_APROVACAO.getAcao()));
		}

		politico.setNome(nome);
		politico.setPartido(daoPartido.get(idPartido));
		try {
			daoPolitico.persistir(politico);
			processaPoliticoAvatar(avatar, politico);
		} catch (final Exception e) {
			mav.addObject("mensagem", "Politico com o nome: " + nome + " j� Cadastrado!");
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
	public ModelAndView editar(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();

		final Politico politico = daoPolitico.get(id);

		mav.addObject("idPolitico", politico.getId());
		mav.addObject("partidos", daoPartido.list(0, 100));
		mav.addObject("nome", politico.getNome());
		mav.addObject("idPartido", politico.getPartido().getId());
		mav.addObject("acao", "editar");

		return politico(mav);
	}

	@RequestMapping(value = "cadastro/politico/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();

		final Politico politico = daoPolitico.get(id);
		final List<Publicacao> publicacoes = daoPublicacao.getPublicacoesPorIdPolitico(politico.getId());
		if (publicacoes != null & publicacoes.size() > 0) {
			mav.addObject("mensagem", "Existem publica�oes relacionadas a esse pol�tico!");
		} else {
			try {
				daoPolitico.excluir(politico);
			} catch (final Exception e) {
				mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
			}
			mav.addObject("mensagem", "Politico excluido com Sucesso!");
		}
		mav.setViewName("redirect:/cadastro/politico");
		return mav;
	}

	private void processaPoliticoAvatar(final MultipartFile avatar, final Politico politico) {
		final File diretorio = new File("/springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			final FileOutputStream arquivo = new FileOutputStream(diretorio.getAbsolutePath() + "/" + politico.getNome() + ".png");
			arquivo.write(avatar.getBytes());
			arquivo.close();
		} catch (final IOException ex) {

		}
	}

	@RequestMapping(value = "politico/gerenciarPoliticos/aprovar/{id}")
	public ModelAndView aprovarPartido(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Politico politico = daoPolitico.get(id);
		politico.setStatusPolitico(daoStatusPolitico.get((long) EnumStatusPolitico.APROVADO.getAcao()));
		try {
			daoPolitico.persistir(politico);
			mav.addObject("mensagem", "Politico aprovado com sucesso!");
		} catch (final Exception e) {
			mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
		}

		return gerenciar(mav);
	}

	@RequestMapping(value = "politico/gerenciarPoliticos/reprovar/{id}")
	public ModelAndView reprovarPublicacao(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Politico politico = daoPolitico.get(id);
		politico.setStatusPolitico(daoStatusPolitico.get((long) EnumStatusPolitico.REPROVADO.getAcao()));
		try {
			daoPolitico.persistir(politico);
			mav.addObject("mensagem", "Politico reprovado com sucesso!");
		} catch (final Exception e) {
			mav.addObject("mensagem", "Houve um erro no processamento, por favor envie um email para o administrador");
		}
		return gerenciar(mav);
	}

	private void gravaLogPartido(final EnumPoliticoAcao acao, final Politico politico) {

	}

}
