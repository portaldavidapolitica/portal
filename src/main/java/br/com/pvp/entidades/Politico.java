package br.com.pvp.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "politico")
public class Politico implements Serializable{

	private static final long serialVersionUID = 7624334650375427087L;

	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;	
	
	@Column(name = "nome", unique = true)
	private String nome;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_partido")
	private Partido partido;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status_politico")
    private StatusPolitico statusPolitico;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "acao_id")
//	private List<PoliticoAcao> acao = new ArrayList<PoliticoAcao>();
	
	public Politico() {}

	public Politico(String nome) {
		this.nome = nome;
	}

	public Politico(String nome, Partido partido) {
		this.nome = nome;
		this.partido = partido;
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

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}
	
	public StatusPolitico getStatusPolitico() {
		return statusPolitico;
	}

	public void setStatusPolitico(StatusPolitico statusPolitico) {
		this.statusPolitico = statusPolitico;
	}
	
//	public List<PoliticoAcao> getAcao() {
//		return acao;
//	}
//
//	public void setAcao(List<PoliticoAcao> acao) {
//		this.acao = acao;
//	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Politico other = (Politico) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
}
