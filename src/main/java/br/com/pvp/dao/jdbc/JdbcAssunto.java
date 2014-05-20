package br.com.pvp.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import br.com.pvp.dao.DAOAssunto;
import br.com.pvp.entidades.Assunto;

public class JdbcAssunto extends JdbcBase<Assunto> implements DAOAssunto {

	public List<Assunto> list(final int offset, final int max) {
		final Integer[] parametros = { max, offset };

		return getJdbcTemplate().query("select id, nome from assunto limit ? offset ?", parametros, new RowMapper<Assunto>() {

			public Assunto mapRow(final ResultSet rs, final int rowNum) throws SQLException {

				final Assunto assunto = new Assunto();
				assunto.setId(rs.getLong(1));
				assunto.setNome(rs.getString(2));
				return assunto;
			}
		});

	}

	public List<Assunto> listJDBC(final int offset, final int max) {
		final List<Assunto> resultado = new ArrayList<Assunto>();
		Connection conexao = null;
		try {
			conexao = getConnection();
			conexao.setAutoCommit(false);
			final PreparedStatement stmt = conexao.prepareStatement("select id, nome from assunto limit ? offset ?");
			stmt.setInt(1, max);
			stmt.setInt(2, offset);
			final ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				resultado.add(new Assunto(rs.getLong(1), rs.getString(2)));
			}
			conexao.commit();
		} catch (final SQLException ex) {
			try {
				conexao.rollback();
			} catch (final SQLException ex2) {

			}
		} finally {
			if (conexao != null) {
				try {
					conexao.close();
				} catch (final SQLException ex) {
					// trate o erro
				}
			}
		}
		return resultado;
	}

	public void persistirJDBC(final Assunto assunto) {
		Connection conexao = null;
		try {
			conexao = getConnection();
			conexao.setAutoCommit(false);
			final PreparedStatement stmt = conexao.prepareStatement("insert into assunto (nome) values (?)");
			stmt.setString(1, assunto.getNome());
			stmt.executeUpdate();
			conexao.commit();
		} catch (final SQLException ex) {
			try {
				conexao.rollback();
			} catch (final SQLException ex2) {
				// n�o deu pra fazer rollback
			}
		} finally {
			if (conexao != null) {
				try {
					conexao.close();
				} catch (final SQLException ex2) {
					// n�o consegui liberar o recurso
				}
			}
		}
	}

	public void persistir(final Assunto assunto) {
		if (assunto != null) {
			if (assunto.getId() == 0l) {
				// estou inserindo
				final HashMap<String, Object> parametros = new HashMap<String, Object>();
				parametros.put("nome", assunto.getNome());
				new SimpleJdbcInsert(getDataSource()).withTableName("assunto").usingGeneratedKeyColumns("id");

				final SimpleJdbcInsert insert = new SimpleJdbcInsert(getJdbcTemplate());
				assunto.setId(insert.withTableName("assunto").usingGeneratedKeyColumns("id").executeAndReturnKey(parametros).longValue());

			} else {
				final Object[] parametros = { assunto.getNome(), assunto.getId() };
				getJdbcTemplate().update("update assunto set nome = ? where id = ?", parametros);
			}
		}
	}

	public void excluir(final Assunto assunto) {
		if (assunto != null && assunto.getId() > 0l) {
			getJdbcTemplate().update("delete from assunto where id = ?", assunto.getId());
		}
	}

	public Assunto get(final Long id) {
		final Object[] parametros = { id };
		try {
			return getJdbcTemplate().queryForObject("select id, nome from assunto where id = ?", parametros, new RowMapper<Assunto>() {

				public Assunto mapRow(final ResultSet rs, final int rowNum) throws SQLException {
					final Assunto assunto = new Assunto();
					assunto.setId(rs.getLong(1));
					assunto.setNome(rs.getString(2));
					return assunto;
				}
			});
		} catch (final EmptyResultDataAccessException ex) {
			return null;
		}

	}

}
