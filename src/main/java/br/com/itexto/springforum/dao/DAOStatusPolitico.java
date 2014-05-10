package br.com.itexto.springforum.dao;

import br.com.itexto.springforum.entidades.StatusPolitico;

public interface DAOStatusPolitico extends DAOBase<StatusPolitico> {

	StatusPolitico getStatusPoliticoPorNome(String nome);
}
