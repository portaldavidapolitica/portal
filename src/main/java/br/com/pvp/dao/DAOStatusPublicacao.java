package br.com.pvp.dao;

import br.com.pvp.entidades.StatusPublicacao;

public interface DAOStatusPublicacao extends DAOBase<StatusPublicacao> {

	StatusPublicacao getStatusPublicacaoPorNome(String nome);

}
