package br.com.itexto.springforum.entidades;

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
@Table(name = "publicacao")
public class Publicacao implements Serializable {

	private static final long serialVersionUID = -7606525246606715587L;

	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;

	@Column(name = "titulo", unique = true)
	private String titulo;
	
	@Column(name = "texto")
	private String texto;
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "politico_id")
	private Politico politico;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "statusPublicacao_id")
    private StatusPublicacao statusPublicacao;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

	public Publicacao() {}

	public Publicacao(String titulo, String texto) {
		this.titulo = titulo;
		this.texto = texto;
	}

	public Publicacao(String titulo, String texto, Politico politico,
			StatusPublicacao statusPublicacao, Usuario usuario) {
		this.titulo = titulo;
		this.texto = texto;
		this.politico = politico;
		this.statusPublicacao = statusPublicacao;
		this.usuario = usuario;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Politico getPolitico() {
		return politico;
	}

	public void setPolitico(Politico politico) {
		this.politico = politico;
	}

	public StatusPublicacao getStatusPublicacao() {
		return statusPublicacao;
	}

	public void setStatusPublicacao(StatusPublicacao statusPublicacao) {
		this.statusPublicacao = statusPublicacao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Publicacao other = (Publicacao) obj;
		if (id != other.id)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
    
}
