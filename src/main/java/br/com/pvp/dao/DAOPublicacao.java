package br.com.pvp.dao;

import java.util.List;

import br.com.pvp.entidades.Publicacao;

public interface DAOPublicacao extends DAOBase<Publicacao> {

	List<Publicacao> getPublicacoesPorPoliticoEPartido(String nomePolitico, Long idPartido);

	List<Publicacao> getPublicacoesAprovadas();

	List<Publicacao> getPublicacoesAguardandoAprovacao();

	List<Publicacao> getPublicacoesPorIdPolitico(Long idPolitico);
}
