package br.com.pvp.dao;

import br.com.pvp.entidades.StatusPolitico;

public interface DAOStatusPolitico extends DAOBase<StatusPolitico> {

	StatusPolitico getStatusPoliticoPorNome(String nome);
}
