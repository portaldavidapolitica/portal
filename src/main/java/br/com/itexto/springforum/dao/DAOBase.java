package br.com.itexto.springforum.dao;

import java.util.List;

public interface DAOBase<T> {

	public List<T> list(int offset, int max);

	public void persistir(T objeto) throws Exception;

	public void excluir(T objeto) throws Exception;

	public T get(Long id);

}
