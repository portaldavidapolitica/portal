package br.com.pvp.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOPublicacao;
import br.com.pvp.entidades.Publicacao;
import br.com.pvp.enums.EnumStatusPublicacao;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoPublicacao")
public class HBPublicacao extends HBDAO<Publicacao> implements DAOPublicacao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicacao> getPublicacoesPorPoliticoEPartido(final String nomePolitico, final Long idPartido) {

		final StringBuilder sql = new StringBuilder();
		sql.append("select pub from Publicacao pub ");

		if (!nomePolitico.equals("") && nomePolitico != null && !idPartido.equals("") && idPartido != null) {
			sql.append("where pub.politico.nome = ? and pub.politico.partido.id = ? and pub.statusPublicacao.id = ?");
			final Query query = getSession().createQuery(sql.toString());
			query.setString(0, "%" + nomePolitico + "%");
			query.setLong(1, idPartido);
			query.setLong(2, EnumStatusPublicacao.APROVADO.getAcao());
			return query.list();
		} else if (!nomePolitico.equals("") && nomePolitico != null) {
			sql.append("where pub.politico.nome = ? and pub.statusPublicacao.id = ?");
			final Query query = getSession().createQuery(sql.toString());
			query.setString(0, "%" + nomePolitico + "%");
			query.setLong(1, EnumStatusPublicacao.APROVADO.getAcao());
			return query.list();
		} else if (!idPartido.equals("") && idPartido != null) {
			sql.append("where and pub.politico.partido.id = ? and pub.statusPublicacao.id = ?");
			final Query query = getSession().createQuery(sql.toString());
			query.setLong(0, idPartido);
			query.setLong(1, EnumStatusPublicacao.APROVADO.getAcao());
			return query.list();
		} else {
			return null;
		}
	}

	@Override
	protected Class getClazz() {
		return Publicacao.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicacao> getPublicacoesAprovadas() {
		final String sql = "select pub from Publicacao pub where pub.statusPublicacao.id = ? group by pub.politico.id";
		final Query query = getSession().createQuery(sql.toString());
		query.setLong(0, EnumStatusPublicacao.APROVADO.getAcao());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicacao> getPublicacoesAguardandoAprovacao() {
		final String sql = "select pub from Publicacao pub where pub.statusPublicacao.id = ?";
		final Query query = getSession().createQuery(sql.toString());
		query.setLong(0, EnumStatusPublicacao.AGUARDANDO_APROVACAO.getAcao());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicacao> getPublicacoesPorIdPolitico(final Long idPolitico) {
		final String sql = "select pub from Publicacao pub where pub.politico.id = ? and pub.statusPublicacao.id = ?";
		final Query query = getSession().createQuery(sql.toString());
		query.setLong(0, idPolitico);
		query.setLong(1, EnumStatusPublicacao.APROVADO.getAcao());
		return query.list();
	}

}
