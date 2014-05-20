package br.com.pvp.entidades;

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
@Table(name = "status_partido")
public class StatusPartido implements Serializable{

	private static final long serialVersionUID = -3312441169575035606L;

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_status_partido", unique = true)
	protected long idStatusPartido;

	@Column(name = "nome", unique = true)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	public StatusPartido() {
	}

	public StatusPartido(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public long getIdStatusPartido() {
		return idStatusPartido;
	}

	public void setIdStatusPartido(long idStatusPartido) {
		this.idStatusPartido = idStatusPartido;
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
				+ (int) (idStatusPartido ^ (idStatusPartido >>> 32));
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
		StatusPartido other = (StatusPartido) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idStatusPartido != other.idStatusPartido)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
