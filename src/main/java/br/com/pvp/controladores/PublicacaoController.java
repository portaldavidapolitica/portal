package br.com.pvp.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.pvp.dao.DAOPartido;
import br.com.pvp.dao.DAOPolitico;
import br.com.pvp.dao.DAOPublicacao;
import br.com.pvp.dao.DAOStatusPublicacao;
import br.com.pvp.dao.DAOUsuario;
import br.com.pvp.entidades.Politico;
import br.com.pvp.entidades.Publicacao;
import br.com.pvp.entidades.Usuario;
import br.com.pvp.enums.EnumStatusPublicacao;

@Controller
public class PublicacaoController {

	@Autowired
	private DAOPolitico daoPolitico;

	@Autowired
	private DAOPublicacao daoPublicacao;

	@Autowired
	private DAOPartido daoPartido;

	@Autowired
	private DAOStatusPublicacao daoStatusPublicacao;

	@Autowired
	private DAOUsuario daoUsuario;

	@RequestMapping("/publicacao")
	public ModelAndView publicacoes(final ModelAndView mav) {

		if (mav.getModelMap().get("publicacoes") == null) {
			mav.addObject("publicacoes", daoPublicacao.getPublicacoesAprovadas());
		}

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.list(0, 100));
		}

		mav.setViewName("publicacao/listarPublicacoes");
		return mav;
	}

	@RequestMapping("publicacao/publicar")
	public ModelAndView publicar(final ModelAndView mav) {

		if (mav.getModelMap().get("publicacao") == null) {
			mav.addObject("publicacao", new Publicacao());
		}
		if (mav.getModelMap().get("acao") == null) {
			mav.addObject("acao", "salvar");
		}

		mav.setViewName("publicacao/publicar");
		return mav;
	}

	@RequestMapping("publicacao/gerenciarPublicacoes")
	public ModelAndView gerenciar(final ModelAndView mav) {

		if (mav.getModelMap().get("publicacoes") == null) {
			mav.addObject("publicacoes", daoPublicacao.getPublicacoesAguardandoAprovacao());
		}

		mav.setViewName("publicacao/gerenciarPublicacoes");
		return mav;
	}

	@RequestMapping(value = "publicacao/procurar/publicacoes")
	public ModelAndView procurar(@RequestParam(value = "nomePolitico")
	final String nomePolitico, @RequestParam(value = "id_partido")
	final Long id_partido) {

		final ModelAndView mav = new ModelAndView();
		final List<Publicacao> publicacoes = daoPublicacao.getPublicacoesPorPoliticoEPartido(nomePolitico, id_partido);

		if (publicacoes.size() > 0) {
			mav.addObject("publicacoes", publicacoes);
		}

		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/pesquisar/{id}")
	public ModelAndView pesquisar(@RequestParam(value = "nomePolitico", required = false)
	final String nomePolitico, @PathVariable("id")
	final Long id_partido) {

		final ModelAndView mav = new ModelAndView();
		Politico politico = new Politico();
		List<Politico> listaPolitico = new ArrayList<Politico>();

		if (!nomePolitico.equals("") && nomePolitico != null && id_partido != 0) {
			politico = daoPolitico.getPoliticoPorNomeEPartido(nomePolitico, id_partido);
			listaPolitico.add(politico);
			mav.addObject("politico", listaPolitico);
		} else if (!nomePolitico.equals("") && nomePolitico != null) {
			politico = daoPolitico.getPoliticoPorNome(nomePolitico);
			listaPolitico.add(politico);
			mav.addObject("politico", listaPolitico);
		} else if (id_partido != 0) {
			listaPolitico = daoPolitico.getPoliticosPorPartido(daoPartido.get(id_partido));
			mav.addObject("politico", listaPolitico);
		}

		if (listaPolitico.size() == 0) {
			mav.addObject("mensagem", "Não foi encontrado nenhum politico com esses dados");
		}

		mav.addObject("nomePolitico", nomePolitico);
		mav.addObject("id_partido", id_partido);
		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/publicar/{politico}")
	public ModelAndView publicar(@PathVariable("politico")
	final String politico) {
		final ModelAndView mav = new ModelAndView();
		final Publicacao publicacao = new Publicacao();
		final String[] politic = politico.split(",");
		final Politico pol = daoPolitico.getPoliticoPorNomeEPartido(politic[0], Long.parseLong(politic[1]));
		publicacao.setPolitico(pol);
		mav.addObject("publicacao", publicacao);
		return publicar(mav);
	}

	@RequestMapping(value = "publicacao/visualizar/{id}")
	public ModelAndView visualizar(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final List<Publicacao> publicacoes = daoPublicacao.getPublicacoesPorIdPolitico(id);

		if (publicacoes.size() == 0) {
			mav.addObject("publicacao", daoPolitico.get(id));
		} else {
			mav.addObject("publicacoes", publicacoes);
		}

		mav.addObject("acao", "editar");
		return publicar(mav);
	}

	@RequestMapping(value = "publicacao/visualizarPublicacao/{id}")
	public ModelAndView visualizarPublicacao(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Publicacao publicacao = daoPublicacao.get(id);
		mav.addObject("publicacao", publicacao);
		mav.addObject("acao", "visualizar");
		return publicar(mav);
	}

	@RequestMapping(value = "publicacao/cadastrar")
	public ModelAndView cadastrar(@RequestParam(value = "titulo")
	final String titulo, @RequestParam(value = "texto")
	final String texto, @RequestParam(value = "nomePolitico")
	final String nomePolitico, @RequestParam(value = "id_partido")
	final Long id_partido, @RequestParam(value = "acao")
	final String acao) {

		final ModelAndView mav = new ModelAndView();
		final Publicacao publicacao = new Publicacao();

		try {
			publicacao.setTitulo(titulo);
			publicacao.setTexto(texto);
			publicacao.setPolitico(daoPolitico.getPoliticoPorNomeEPartido(nomePolitico, id_partido));
			publicacao.setStatusPublicacao(daoStatusPublicacao.get((long) EnumStatusPublicacao.AGUARDANDO_APROVACAO.getAcao()));
			final Usuario usuario = daoUsuario.getUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
			publicacao.setUsuario(usuario);
			daoPublicacao.persistir(publicacao);
		} catch (final Exception e) {
			mav.addObject("mensagem", "Erro ao salvar a publicacao, motivo do erro: " + e.getCause() + " : " + e.getMessage());
			mav.addObject("acao", acao);
			mav.addObject("publicacao", publicacao);
			return publicar(mav);
		}

		mav.addObject("mensagem", "Publicacao salva com sucesso!");
		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/gerenciarPublicacoes/aprovar/{id}")
	public ModelAndView aprovarPublicacao(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Publicacao publicacao = daoPublicacao.get(id);
		publicacao.setStatusPublicacao(daoStatusPublicacao.get((long) EnumStatusPublicacao.APROVADO.getAcao()));
		daoPublicacao.persistir(publicacao);
		mav.addObject("mensagem", "Publicacao aprovada com sucesso!");
		return gerenciar(mav);
	}

	@RequestMapping(value = "publicacao/gerenciarPublicacoes/reprovar/{id}")
	public ModelAndView reprovarPublicacao(@PathVariable("id")
	final Long id) {
		final ModelAndView mav = new ModelAndView();
		final Publicacao publicacao = daoPublicacao.get(id);
		publicacao.setStatusPublicacao(daoStatusPublicacao.get((long) EnumStatusPublicacao.REPROVADO.getAcao()));
		daoPublicacao.persistir(publicacao);
		mav.addObject("mensagem", "Publicacao reprovada com sucesso!");
		return gerenciar(mav);
	}

}
