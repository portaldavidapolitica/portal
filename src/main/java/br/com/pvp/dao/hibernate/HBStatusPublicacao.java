package br.com.pvp.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOStatusPublicacao;
import br.com.pvp.entidades.StatusPublicacao;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoStatusPublicacao")
public class HBStatusPublicacao extends HBDAO<StatusPublicacao> implements DAOStatusPublicacao {

	@Override
	public StatusPublicacao getStatusPublicacaoPorNome(final String nome) {
		final Query query = getSession().createQuery("from StatusPublicacao st where st.nome = ?");
		query.setString(0, nome);
		return (StatusPublicacao) query.uniqueResult();
	}

	@Override
	protected Class getClazz() {
		return StatusPublicacao.class;
	}

}
