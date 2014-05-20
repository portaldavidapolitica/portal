package br.com.pvp.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOStatusPolitico;
import br.com.pvp.entidades.StatusPolitico;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoStatusPolitico")
public class HBStatusPolitico extends HBDAO<StatusPolitico> implements DAOStatusPolitico {

	@Override
	public StatusPolitico getStatusPoliticoPorNome(final String nome) {
		final Query query = getSession().createQuery("from StatusPolitico st where st.nome = ?");
		query.setString(0, nome);
		return (StatusPolitico) query.uniqueResult();
	}

	@Override
	protected Class getClazz() {
		return StatusPolitico.class;
	}

}
