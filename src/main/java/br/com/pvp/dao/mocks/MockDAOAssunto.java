package br.com.pvp.dao.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.pvp.dao.DAOAssunto;
import br.com.pvp.entidades.Assunto;

@Component("daoAssunto")
public class MockDAOAssunto extends MockDAOBase<Assunto> implements DAOAssunto {

	private List<Assunto> todos;

	@Override
	public List<Assunto> list(final int offset, final int max) {
		if (todos == null) {
			todos = new ArrayList<Assunto>();
			final String[] assuntos = { "Container IoC", "AOP", "Spring MVC", "ORM", "Spring Batch", "Spring WebFlow" };
			for (int i = 0; i < assuntos.length; i++) {
				final Assunto assunto = new Assunto();
				assunto.setNome(assuntos[i]);
				assunto.setId(i + 1);
				todos.add(assunto);
			}
			java.util.Collections.sort(todos);
		}
		return todos;
	}

	@Override
	public void excluir(final Assunto objeto) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

}
