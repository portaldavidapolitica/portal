package br.com.itexto.springforum.dao;

import java.util.List;

import br.com.itexto.springforum.entidades.Partido;
import br.com.itexto.springforum.entidades.Politico;

public interface DAOPolitico extends DAOBase<Politico> {

	Politico getPoliticoPorNome(String nome);
	
	List<Politico> getPoliticosPorPartido(Partido partido);
	
	Politico getPoliticoPorNomeEPartido(String nome, Long idPartido);
	
	List<Politico> getPoliticosAprovados();

	List<Politico> getPoliticosEmAprovacao();
}
