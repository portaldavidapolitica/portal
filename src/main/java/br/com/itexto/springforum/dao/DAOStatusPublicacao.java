package br.com.itexto.springforum.dao;

import br.com.itexto.springforum.entidades.StatusPublicacao;

public interface DAOStatusPublicacao extends DAOBase<StatusPublicacao> {

	StatusPublicacao getStatusPublicacaoPorNome(String nome);

}
