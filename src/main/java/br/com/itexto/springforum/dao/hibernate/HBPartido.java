package br.com.itexto.springforum.dao.hibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.itexto.springforum.dao.DAOPartido;
import br.com.itexto.springforum.entidades.Partido;

@Transactional(propagation=Propagation.SUPPORTS)
@Repository("daoPartido")
public class HBPartido extends HBDAO<Partido> implements DAOPartido {

	@Override
	protected Class getClazz() {
		return Partido.class;
	}

	@Override
	public Partido getPartidoPorNome(String nome) {
		Query query = getSession().createQuery("from Partido p where p.nome = ?");
		query.setString(0, nome);
		return (Partido) query.uniqueResult();
	}

	@Override
	public Partido getPartidoPorSigla(String sigla) {
		Query query = getSession().createQuery("from Partido p where p.sigla = ?");
		query.setString(0, sigla);
		return (Partido) query.uniqueResult();
	}

}
