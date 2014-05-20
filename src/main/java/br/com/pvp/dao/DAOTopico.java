package br.com.pvp.dao;

import java.util.List;

import br.com.pvp.entidades.Assunto;
import br.com.pvp.entidades.Topico;
import br.com.pvp.entidades.Usuario;

public interface DAOTopico extends DAOBase<Topico> {

	public List<Topico> getTopicosPorAutor(Usuario usuario);

	public List<Topico> getTopicosPorAssunto(Assunto assunto, int offset, int max);

}
