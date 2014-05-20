package br.com.pvp.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity @Table(name="usuario")
public class Usuario implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6897414269815424982L;
	
	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;
	
	public long getId() {return id;}
	public void setId(long valor) {this.id = valor;}
	
	@NotNull @NotEmpty
	@Column(name="nome", nullable=false, length=128)
	private String nome;
	
	@Email(message="Isto não é um e-mail válido") @NotNull @NotEmpty
	@Column(name="email", nullable=false, length=128, unique=true)
	private String email;
	
	@NotNull
	@Column(name="data_cadastro", nullable=false) @Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro = new Date();
	
	@NotNull @NotEmpty
	@Size(min=8, max=32, message="Login muito curto ou muito longo") 
	@Column(name="login", nullable=false, unique=true, length=64)
	private String login;
	
	@Column(name="twitter", nullable=true, length=64, unique=true)
	private String twitter;
	
	private transient String senha;
	@Column(name="ultimo_login", nullable=true) @Temporal(TemporalType.TIMESTAMP)
	private Date ultimoLogin;
	
	@Column(name="hash_senha", nullable=false, length=128)
	private String hashSenha;
	
	public String getHashSenha() {return hashSenha;}
	public void setHashSenha(String valor) {hashSenha = valor;}

	public Date getUltimoLogin() {return ultimoLogin;}
	public void setUltimoLogin(Date data) {ultimoLogin = data;}
	
	public String getTwitter() {return twitter;}
	public void setTwitter(String valor) {twitter = valor;}

	private transient List<PermissaoUsuario> permissoesUsuario = new ArrayList<PermissaoUsuario>();
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		setHashSenha(org.apache.commons.codec.digest.DigestUtils.sha256Hex(senha));
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	public List<PermissaoUsuario> getPermissoesUsuario() {
		return permissoesUsuario;
	}
	public void setPermissoesUsuario(List<PermissaoUsuario> permissoesUsuario) {
		this.permissoesUsuario = permissoesUsuario;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((hashSenha == null) ? 0 : hashSenha.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((twitter == null) ? 0 : twitter.hashCode());
		result = prime * result
				+ ((ultimoLogin == null) ? 0 : ultimoLogin.hashCode());
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
		Usuario other = (Usuario) obj;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (hashSenha == null) {
			if (other.hashSenha != null)
				return false;
		} else if (!hashSenha.equals(other.hashSenha))
			return false;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (twitter == null) {
			if (other.twitter != null)
				return false;
		} else if (!twitter.equals(other.twitter))
			return false;
		if (ultimoLogin == null) {
			if (other.ultimoLogin != null)
				return false;
		} else if (!ultimoLogin.equals(other.ultimoLogin))
			return false;
		return true;
	}
}
