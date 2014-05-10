package br.com.itexto.springforum.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.GrantedAuthority;

@Entity @Table(name="permissao_usuario")
public class PermissaoUsuario  implements GrantedAuthority, java.io.Serializable {
	
	private static final long serialVersionUID = 1812550269891887757L;
	@Id @Generated(GenerationTime.INSERT) 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", unique=true) 
	protected long id;
	
	public long getId() {return id;}
	public void setId(long valor) {this.id = valor;}
	
	@Column(name="role", nullable=false, length=64)
	private String role;
	@ManyToOne @JoinColumn(name="id_usuario", nullable=false)
	private Usuario usuario;
	
	public String getRole() {return role;}
	public void setRole(String valor) {role = valor;}
	
	public Usuario getUsuario() {return usuario;}
	public void setUsuario(Usuario usr) {usuario = usr;}
	
	public String getAuthority() {
		return role;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		PermissaoUsuario other = (PermissaoUsuario) obj;
		if (id != other.id)
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}
	
}
