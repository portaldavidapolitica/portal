package br.com.pvp.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOPermissaoUsuario;
import br.com.pvp.entidades.PermissaoUsuario;
import br.com.pvp.entidades.Usuario;

@Repository("daoPermissaoUsuario")
@Transactional
public class HBPermissaoUsuario extends HBDAO<PermissaoUsuario> implements DAOPermissaoUsuario {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<PermissaoUsuario> getPermissoesUsuario(final Usuario usuario) {
		if (usuario == null) {
			return new ArrayList<PermissaoUsuario>();
		}
		final Query query = sessionFactory.getCurrentSession().createQuery("from PermissaoUsuario pu where pu.usuario = ?");
		query.setEntity(0, usuario);
		return query.list();
	}

	public void addRole(final String role, final Usuario usuario) {
		if (role != null && usuario != null) {
			final PermissaoUsuario permissao = new PermissaoUsuario();
			permissao.setRole(role);
			permissao.setUsuario(usuario);
			sessionFactory.getCurrentSession().saveOrUpdate(permissao);
		}

	}

	@Override
	protected Class getClazz() {
		return PermissaoUsuario.class;
	}
}
