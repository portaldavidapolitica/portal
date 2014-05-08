package br.com.itexto.springforum.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.itexto.springforum.dao.DAOStatusPublicacao;
import br.com.itexto.springforum.entidades.StatusPublicacao;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoStatusPublicacao")
public class HBStatusPublicacao extends HBDAO<StatusPublicacao> implements
		DAOStatusPublicacao {

	@Override
	public StatusPublicacao getStatusPublicacaoPorNome(String nome) {
		Query query = getSession().createQuery("from StatusPublicacao st where st.nome = ?");
		query.setString(0, nome);
		return (StatusPublicacao) query.uniqueResult();
	}

	@Override
	protected Class getClazz() {
		return StatusPublicacao.class;
	}

}
