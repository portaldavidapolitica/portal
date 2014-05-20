package br.com.pvp.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOPolitico;
import br.com.pvp.entidades.Partido;
import br.com.pvp.entidades.Politico;
import br.com.pvp.enums.EnumStatusPolitico;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoPolitico")
public class HBPolitico extends HBDAO<Politico> implements DAOPolitico {

	@Override
	public Politico getPoliticoPorNome(final String nome) {
		final Query query = getSession().createQuery("from Politico p where p.nome = ?");
		query.setString(0, nome);
		return (Politico) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Politico> getPoliticosPorPartido(final Partido partido) {
		if (partido == null) {
			return new ArrayList<Politico>();
		}
		final Query query = getSession().createQuery("from Politico p where p.partido = ?");
		query.setEntity(0, partido);
		return query.list();
	}

	@Override
	protected Class getClazz() {
		return Politico.class;
	}

	@Override
	public Politico getPoliticoPorNomeEPartido(final String nome, final Long idPartido) {
		final Query query = getSession().createQuery("from Politico p where p.nome = ? and p.partido.id = ?");
		query.setString(0, nome);
		query.setLong(1, idPartido);
		return (Politico) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Politico> getPoliticosAprovados() {
		final Query query = getSession().createQuery("from Politico p where p.statusPolitico.idStatusPolitico = ?");
		query.setInteger(0, EnumStatusPolitico.APROVADO.getAcao());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Politico> getPoliticosEmAprovacao() {
		final Query query = getSession().createQuery("from Politico p where p.statusPolitico.idStatusPolitico = ?");
		query.setInteger(0, EnumStatusPolitico.AGUARDANDO_APROVACAO.getAcao());
		return query.list();
	}

}
