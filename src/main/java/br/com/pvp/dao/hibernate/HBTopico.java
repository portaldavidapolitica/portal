package br.com.pvp.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.pvp.dao.DAOTopico;
import br.com.pvp.entidades.Assunto;
import br.com.pvp.entidades.Topico;
import br.com.pvp.entidades.Usuario;

@Repository
public class HBTopico extends HBDAO<Topico> implements DAOTopico {

	@Override
	protected Class getClazz() {
		return Topico.class;
	}

	public List<Topico> getTopicosPorAutor(final Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Topico> getTopicosPorAssunto(final Assunto assunto, final int offset, final int max) {
		final Query busca = getSession().createQuery("from Topico topico where topico.assunto = ?");
		busca.setEntity(0, assunto);
		busca.setMaxResults(max);
		busca.setFirstResult(offset);
		return busca.list();
	}

}
