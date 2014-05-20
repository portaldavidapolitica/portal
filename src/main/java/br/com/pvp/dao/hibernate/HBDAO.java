package br.com.pvp.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.pvp.dao.DAOBase;

@Transactional(propagation = Propagation.SUPPORTS)
public abstract class HBDAO<T> implements DAOBase<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(final SessionFactory sf) {
		sessionFactory = sf;
	}

	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}

	protected abstract Class getClazz();

	public void persistir(final T objeto) {
		getSession().saveOrUpdate(objeto);
	}

	public void excluir(final T objeto) {
		getSession().delete(objeto);
	}

	public T get(final Long id) {
		return (T) getSession().get(getClazz(), id);
	}

	public List<T> list(final int offset, final int max) {
		return getSession().createCriteria(getClazz()).setMaxResults(max).setFirstResult(offset).list();
	}

}
