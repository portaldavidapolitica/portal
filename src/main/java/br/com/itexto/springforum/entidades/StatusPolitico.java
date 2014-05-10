package br.com.itexto.springforum.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "status_politico")
public class StatusPolitico implements Serializable{

	private static final long serialVersionUID = 1448436572573994838L;

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_status_politico", unique = true)
	protected long idStatusPolitico;

	@Column(name = "nome", unique = true)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	public StatusPolitico() {
	}

	public StatusPolitico(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public long getIdStatusPolitico() {
		return idStatusPolitico;
	}

	public void setIdStatusPolitico(long idStatusPolitico) {
		this.idStatusPolitico = idStatusPolitico;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ (int) (idStatusPolitico ^ (idStatusPolitico >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StatusPolitico other = (StatusPolitico) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idStatusPolitico != other.idStatusPolitico)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
