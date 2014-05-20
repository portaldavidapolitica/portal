package br.com.pvp.dao;

import java.util.List;

import br.com.pvp.entidades.Partido;

public interface DAOPartido extends DAOBase<Partido> {

	Partido getPartidoPorNome(String nome);

	Partido getPartidoPorSigla(String sigla);

	List<Partido> getPartidosAprovados();

	List<Partido> getPartidosEmAprovacao();
}
