package br.com.pvp.dao.hibernate;

import org.springframework.stereotype.Repository;

import br.com.pvp.dao.DAOAssunto;
import br.com.pvp.entidades.Assunto;

@Repository
public class HBAssunto extends HBDAO<Assunto> implements DAOAssunto {

	@Override
	protected Class getClazz() {
		return Assunto.class;
	}

}
