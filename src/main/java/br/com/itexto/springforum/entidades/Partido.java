package br.com.itexto.springforum.entidades;

import java.io.Serializable;	
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "partido")
public class Partido implements Serializable {

	private static final long serialVersionUID = -5188859581075839527L;

	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;
	
	@Column(name = "nome", unique = true)
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "sigla", unique = true)
	private String sigla;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_criacao")
	private Date dataCriacao;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status_partido")
	private StatusPartido statusPartido;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acao_id")
//	private List<PartidoAcao> acao = new ArrayList<PartidoAcao>();
	
	public Partido() {}
	
	public Partido(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}

	public Partido(String nome, String descricao, String sigla, Date dataCriacao) {
		this.nome = nome;
		this.descricao = descricao;
		this.sigla = sigla;
		this.dataCriacao = dataCriacao;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public StatusPartido getStatusPartido() {
		return statusPartido;
	}

	public void setStatusPartido(StatusPartido statusPartido) {
		this.statusPartido = statusPartido;
	}
	
//	public List<PartidoAcao> getAcao() {
//		return acao;
//	}
//
//	public void setAcao(List<PartidoAcao> acao) {
//		this.acao = acao;
//	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCriacao == null) ? 0 : dataCriacao.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
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
		Partido other = (Partido) obj;
		if (dataCriacao == null) {
			if (other.dataCriacao != null)
				return false;
		} else if (!dataCriacao.equals(other.dataCriacao))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (sigla == null) {
			if (other.sigla != null)
				return false;
		} else if (!sigla.equals(other.sigla))
			return false;
		return true;
	}
	
}
