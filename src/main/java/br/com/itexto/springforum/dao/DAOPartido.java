package br.com.itexto.springforum.dao;

import java.util.List;

import br.com.itexto.springforum.entidades.Partido;

public interface DAOPartido extends DAOBase<Partido> {

	Partido getPartidoPorNome(String nome);
	
	Partido getPartidoPorSigla(String sigla);
	
	List<Partido> getPartidosAprovados();
}
