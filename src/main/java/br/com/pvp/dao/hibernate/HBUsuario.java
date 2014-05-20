package br.com.pvp.dao.hibernate;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOUsuario;
import br.com.pvp.entidades.Usuario;

@Transactional(propagation = Propagation.SUPPORTS)
@Repository("daoUsuario")
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario {

	@Override
	protected Class getClazz() {
		return Usuario.class;
	}

	public Usuario getUsuario(final String login, final String senha) {
		final Query query = getSession().createQuery("from Usuario usr where usr.login = ? and usr.hashSenha = ?");
		query.setString(0, login);
		query.setString(1, DigestUtils.sha256Hex(senha));
		return (Usuario) query.uniqueResult();
	}

	public Usuario getUsuario(final String login) {
		final Query query = getSession().createQuery("from Usuario usr where usr.login = ?");
		query.setString(0, login);
		return (Usuario) query.uniqueResult();
	}

}
