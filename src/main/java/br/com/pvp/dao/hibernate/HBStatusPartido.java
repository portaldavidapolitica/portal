package br.com.pvp.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOStatusPartido;
import br.com.pvp.entidades.StatusPartido;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoStatusPartido")
public class HBStatusPartido extends HBDAO<StatusPartido> implements DAOStatusPartido {

	@Override
	public StatusPartido getStatusPartidoPorNome(final String nome) {
		final Query query = getSession().createQuery("from StatusPartido st where st.nome = ?");
		query.setString(0, nome);
		return (StatusPartido) query.uniqueResult();
	}

	@Override
	protected Class getClazz() {
		return StatusPartido.class;
	}

}
