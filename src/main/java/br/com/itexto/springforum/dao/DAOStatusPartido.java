package br.com.itexto.springforum.dao;

import br.com.itexto.springforum.entidades.StatusPartido;

public interface DAOStatusPartido extends DAOBase<StatusPartido> {

	StatusPartido getStatusPartidoPorNome(String nome);
}
