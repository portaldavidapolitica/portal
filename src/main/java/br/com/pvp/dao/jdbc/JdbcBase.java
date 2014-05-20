package br.com.pvp.dao.jdbc;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import br.com.pvp.dao.DAOBase;

public abstract class JdbcBase<T> extends JdbcDaoSupport implements DAOBase<T> {

}
