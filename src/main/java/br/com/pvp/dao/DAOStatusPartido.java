package br.com.pvp.dao;

import br.com.pvp.entidades.StatusPartido;

public interface DAOStatusPartido extends DAOBase<StatusPartido> {

	StatusPartido getStatusPartidoPorNome(String nome);
}
