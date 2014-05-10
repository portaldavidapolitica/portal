package br.com.itexto.springforum.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.itexto.springforum.dao.DAOStatusPolitico;
import br.com.itexto.springforum.entidades.StatusPolitico;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoStatusPolitico")
public class HBStatusPolitico extends HBDAO<StatusPolitico> implements
		DAOStatusPolitico {
	
	@Override
	public StatusPolitico getStatusPoliticoPorNome(String nome) {
		Query query = getSession().createQuery("from StatusPolitico st where st.nome = ?");
		query.setString(0, nome);
		return (StatusPolitico) query.uniqueResult();
	}

	@Override
	protected Class getClazz() {
		return StatusPolitico.class;
	}

}
