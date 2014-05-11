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
	public ModelAndView partido(ModelAndView model) {

		if (model.getModelMap().get("partido") == null) {
			model.addObject("partido", new Partido());
		}
		if (model.getModelMap().get("partidos") == null
				&& model.getModelMap().get("exibePartidos") == null) {
			model.addObject("partidos", daoPartido.getPartidosAprovados());
		}

		model.setViewName("cadastro/partido");
		return model;
	}

	@RequestMapping("partido/gerenciarPartidos")
	public ModelAndView gerenciar(ModelAndView mav) {

		if (mav.getModelMap().get("partidos") == null) {
			mav.addObject("partidos", daoPartido.getPartidosEmAprovacao());
		}

		mav.setViewName("partido/gerenciarPartidos");
		return mav;
	}
	
	@RequestMapping(value = "cadastro/cadastrarPartido/{idPartido}", method = RequestMethod.POST)
	public ModelAndView partido(
			@PathVariable("idPartido") Long idPartido,
			@RequestParam(value = "nome") String nome,
			@RequestParam(value = "sigla") String sigla,
			@RequestParam(value = "descricao") String descricao,
			@RequestParam(value = "avatar", required = false) MultipartFile avatar,
			@RequestParam(value = "acao") String acao) {

		ModelAndView model = new ModelAndView();
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
		} catch (Exception e) {
			model.addObject("partido", partido);
			model.addObject(
					"mensagem",
					"Erro ao cadastrar o partido, motivo do erro: Partido com o nome ou a sigla digitado já cadastrados");
			if (acao.equals("editar")) {
				model.addObject("exibePartidos", false);
			}
			return partido(model);
		}

		model.addObject("mensagem", "Partido Cadastrado com Sucesso!");
		return partido(model);
	}

	@RequestMapping(value = "cadastro/partido/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("partido", daoPartido.get(id));
		mav.addObject("exibePartidos", false);
		mav.addObject("acao", "editar");
		return partido(mav);
	}

	@RequestMapping(value = "cadastro/partido/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Partido partido = daoPartido.get(id);
		List<Politico> politicos = daoPolitico.getPoliticosPorPartido(partido);
		
		if (politicos.size() > 0) {
			for (Politico pol : politicos) {
				daoPolitico.excluir(pol);
			}
		}

		daoPartido.excluir(partido);
		mav.addObject("mensagem", "Partido excluido com Sucesso!");
		return partido(mav);
	}

	private void processaPartidoAvatar(MultipartFile avatar, Partido partido) {
		File diretorio = new File("/springForum/avatares");
		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		try {
			FileOutputStream arquivo = new FileOutputStream(
					diretorio.getAbsolutePath() + "/" + partido.getSigla()
							+ ".png");
			arquivo.write(avatar.getBytes());
			arquivo.close();
		} catch (IOException ex) {

		}
	}

	@RequestMapping(value = "partido/gerenciarPartidos/aprovar/{id}")
	public ModelAndView aprovarPartido(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Partido partido = daoPartido.get(id);
		partido.setStatusPartido(daoStatusPartido
				.get((long) EnumStatusPublicacao.APROVADO.getAcao()));
		daoPartido.persistir(partido);
		mav.addObject("mensagem", "Partido aprovado com sucesso!");
		return gerenciar(mav);
	}

	@RequestMapping(value = "partido/gerenciarPartidos/reprovar/{id}")
	public ModelAndView reprovarPublicacao(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		Partido partido = daoPartido.get(id);
		partido.setStatusPartido(daoStatusPartido
				.get((long) EnumStatusPublicacao.REPROVADO.getAcao()));
		daoPartido.persistir(partido);
		mav.addObject("mensagem", "Partido reprovado com sucesso!");
		return gerenciar(mav);
	}
	
}
