package br.com.pvp.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOPartido;
import br.com.pvp.entidades.Partido;
import br.com.pvp.enums.EnumStatusPartido;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoPartido")
public class HBPartido extends HBDAO<Partido> implements DAOPartido {

	@Override
	protected Class getClazz() {
		return Partido.class;
	}

	@Override
	public Partido getPartidoPorNome(final String nome) {
		final Query query = getSession().createQuery("from Partido p where p.nome = ? and p.");
		query.setString(0, nome);
		return (Partido) query.uniqueResult();
	}

	@Override
	public Partido getPartidoPorSigla(final String sigla) {
		final Query query = getSession().createQuery("from Partido p where p.sigla = ?");
		query.setString(0, sigla);
		return (Partido) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getPartidosAprovados() {
		final Query query = getSession().createQuery("from Partido p where p.statusPartido.idStatusPartido = ?");
		query.setInteger(0, EnumStatusPartido.APROVADO.getAcao());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partido> getPartidosEmAprovacao() {
		final Query query = getSession().createQuery("from Partido p where p.statusPartido.idStatusPartido = ?");
		query.setInteger(0, EnumStatusPartido.AGUARDANDO_APROVACAO.getAcao());
		return query.list();
	}

}
