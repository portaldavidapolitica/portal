package br.com.pvp.dao.mocks;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.pvp.dao.DAOTopico;
import br.com.pvp.entidades.Assunto;
import br.com.pvp.entidades.Topico;
import br.com.pvp.entidades.Usuario;

@Component("daoTopico")
public class MockDAOTopico extends MockDAOBase<Topico> implements DAOTopico {

	private final List<Topico> topicos = new ArrayList<Topico>();

	@Autowired
	private MockDAOAssunto daoAssunto;

	public MockDAOAssunto getDaoAssunto() {
		return daoAssunto;
	}

	public void setDaoAssunto(final MockDAOAssunto dao) {
		daoAssunto = dao;
	}

	private final String[] titulos = { "Como eu configuro o Spring?", "Problema com suporte a DAO", "Como funciona AOP?", "D�vida com Spring Security", "JDBCTemplate: como eu uso?", "Configura��o XML: problema", "Projeto de exemplo", "Hibernate: como definir o SessionFactory?", "DataSource: como obter via JNDI?", "Como funciona a requisi��o no MVC?", "Aonde uso o Spring?" };

	public List<Topico> getTopicosPorAutor(final Usuario usuario) {
		final List<Topico> resultado = new ArrayList<Topico>();
		if (usuario != null) {
			final int num_registros = (int) (Math.random() * titulos.length);
			final List<Assunto> assuntos = getDaoAssunto().list(-1, -1);
			for (int i = 0; i < num_registros; i++) {
				final Topico topico = new Topico();
				topico.setTitulo(titulos[i]);
				topico.setAutor(usuario);
				topico.setAssunto(assuntos.get((int) (Math.random() * assuntos.size())));
				resultado.add(topico);
			}
		}
		return resultado;
	}

	@Override
	public Class getClazz() {
		return Topico.class;
	}

	public List<Topico> getTopicosPorAssunto(final Assunto assunto, final int offset, final int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
