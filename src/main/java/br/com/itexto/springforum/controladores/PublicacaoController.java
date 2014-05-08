package br.com.itexto.springforum.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.itexto.springforum.Enum.EnumStatusPublicacao;
import br.com.itexto.springforum.dao.DAOPartido;
import br.com.itexto.springforum.dao.DAOPolitico;
import br.com.itexto.springforum.dao.DAOPublicacao;
import br.com.itexto.springforum.dao.DAOStatusPublicacao;
import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Politico;
import br.com.itexto.springforum.entidades.Publicacao;
import br.com.itexto.springforum.entidades.Usuario;

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
	public ModelAndView publicacoes(ModelAndView mav) {

		if (mav.getModelMap().get("publicacoes") == null) {
			mav.addObject("publicacoes",
					daoPublicacao.getPublicacoesAprovadas());
		}

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.list(0, 100));
		}

		mav.setViewName("publicacao/listarPublicacoes");
		return mav;
	}

	@RequestMapping("publicacao/publicar")
	public ModelAndView publicar(ModelAndView mav) {

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
	public ModelAndView gerenciar(ModelAndView mav) {

		if (mav.getModelMap().get("publicacoes") == null) {
			mav.addObject("publicacoes", daoPublicacao.list(0, 200));
		}

		mav.setViewName("publicacao/gerenciarPublicacoes");
		return mav;
	}

	@RequestMapping(value = "publicacao/procurar/publicacoes")
	public ModelAndView procurar(
			@RequestParam(value = "nomePolitico") String nomePolitico,
			@RequestParam(value = "id_partido") Long id_partido) {

		ModelAndView mav = new ModelAndView();
		List<Publicacao> publicacoes = daoPublicacao
				.getPublicacoesPorPoliticoEPartido(nomePolitico, id_partido);

		if (publicacoes.size() > 0) {
			mav.addObject("publicacoes", publicacoes);
		}

		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/pesquisar")
	public ModelAndView pesquisar(
			@RequestParam(value = "nomePolitico") String nomePolitico,
			@RequestParam(value = "id_partido") String id_partido) {

		ModelAndView mav = new ModelAndView();
		Politico politico = new Politico();
		List<Politico> listaPolitico = new ArrayList<Politico>();

		if ((!nomePolitico.equals("") && nomePolitico != null)
				&& (!id_partido.equals("") && id_partido != null)) {
			politico = daoPolitico.getPoliticoPorNomeEPartido(nomePolitico,
					Long.parseLong(id_partido));
		} else if (!nomePolitico.equals("") && nomePolitico != null) {
			politico = daoPolitico.getPoliticoPorNome(nomePolitico);
		} else if (!id_partido.equals("") && id_partido != null) {
			listaPolitico = daoPolitico.getPoliticosPorPartido(daoPartido
					.get(Long.parseLong(id_partido)));
		}

		if (listaPolitico.size() == 0) {
			listaPolitico.add(politico);
		}

		mav.addObject("nomePolitico", nomePolitico);
		mav.addObject("id_partido", id_partido);
		mav.addObject("politico", listaPolitico);
		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/publicar/{politico}")
	public ModelAndView publicar(@PathVariable("politico") String politico) {
		ModelAndView mav = new ModelAndView();
		Publicacao publicacao = new Publicacao();
		String[] politic = politico.split(",");
		Politico pol = daoPolitico.getPoliticoPorNomeEPartido(politic[0],
				Long.parseLong(politic[1]));
		publicacao.setPolitico(pol);
		mav.addObject("publicacao", publicacao);
		return publicar(mav);
	}

	@RequestMapping(value = "publicacao/visualizar/{id}")
	public ModelAndView visualizar(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Publicacao publicacao = daoPublicacao.get(id);
		mav.addObject("publicacao", publicacao);
		mav.addObject("acao", "editar");
		return publicar(mav);
	}

	@RequestMapping(value = "publicacao/cadastrar")
	public ModelAndView cadastrar(
			@RequestParam(value = "titulo") String titulo,
			@RequestParam(value = "texto") String texto,
			@RequestParam(value = "nomePolitico") String nomePolitico,
			@RequestParam(value = "id_partido") Long id_partido,
			@RequestParam(value = "acao") String acao) {

		ModelAndView mav = new ModelAndView();
		Publicacao publicacao = new Publicacao();

		try {
			publicacao.setTitulo(titulo);
			publicacao.setTexto(texto);
			publicacao.setPolitico(daoPolitico.getPoliticoPorNomeEPartido(
					nomePolitico, id_partido));
			publicacao.setStatusPublicacao(daoStatusPublicacao
					.get((long) EnumStatusPublicacao.AGUARDANDO_APROVACAO
							.getAcao()));
			Usuario usuario = daoUsuario.getUsuario(SecurityContextHolder
					.getContext().getAuthentication().getName());
			publicacao.setUsuario(usuario);
			daoPublicacao.persistir(publicacao);
		} catch (Exception e) {
			mav.addObject(
					"mensagem",
					"Erro ao salvar a publicacao, motivo do erro: "
							+ e.getCause() + " : " + e.getMessage());
			mav.addObject("acao", acao);
			mav.addObject("publicacao", publicacao);
			return publicar(mav);
		}

		mav.addObject("mensagem", "Publicacao salva com sucesso!");
		return publicacoes(mav);
	}

	@RequestMapping(value = "publicacao/gerenciarPublicacoes/aprovar/{id}")
	public ModelAndView aprovarPublicacao(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Publicacao publicacao = daoPublicacao.get(id);
		publicacao.setStatusPublicacao(daoStatusPublicacao
				.get((long) EnumStatusPublicacao.APROVADO.getAcao()));
		daoPublicacao.persistir(publicacao);
		mav.addObject("mensagem", "Publicacao aprovada com sucesso!");
		return gerenciar(mav);
	}

	@RequestMapping(value = "publicacao/gerenciarPublicacoes/reprovar/{id}")
	public ModelAndView reprovarPublicacao(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Publicacao publicacao = daoPublicacao.get(id);
		publicacao.setStatusPublicacao(daoStatusPublicacao
				.get((long) EnumStatusPublicacao.REPROVADO.getAcao()));
		daoPublicacao.persistir(publicacao);
		mav.addObject("mensagem", "Publicacao reprovada com sucesso!");
		return gerenciar(mav);
	}

}
