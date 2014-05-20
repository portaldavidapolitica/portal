package br.com.pvp.dao;

import java.util.List;

import br.com.pvp.entidades.Partido;
import br.com.pvp.entidades.Politico;

public interface DAOPolitico extends DAOBase<Politico> {

	Politico getPoliticoPorNome(String nome);

	List<Politico> getPoliticosPorPartido(Partido partido);

	Politico getPoliticoPorNomeEPartido(String nome, Long idPartido);

	List<Politico> getPoliticosAprovados();

	List<Politico> getPoliticosEmAprovacao();
}
