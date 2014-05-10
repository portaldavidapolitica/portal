package br.com.itexto.springforum.dao;

import br.com.itexto.springforum.entidades.Publicacao;

import java.util.List;

public interface DAOPublicacao extends DAOBase<Publicacao> {

    List<Publicacao> getPublicacoesPorPoliticoEPartido(String nomePolitico, Long idPartido);

    List<Publicacao> getPublicacoesAprovadas();
    
    List<Publicacao> getPublicacoesAguardandoAprovacao();
}
