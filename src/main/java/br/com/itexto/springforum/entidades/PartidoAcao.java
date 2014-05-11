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
@Table(name = "partido_acao")
public class PartidoAcao implements Serializable {

	private static final long serialVersionUID = 5986131639049967692L;

	@Id
	@Generated(GenerationTime.INSERT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_partido_acao", unique = true)
	protected long idPartidoAcao;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_partido", nullable = false)
	private Partido partido;
	
	@ManyToOne
	@JoinColumn(name = "id_acao", nullable = false)
	private Acao acao;

	@ManyToOne
	@JoinColumn(name = "id_usuario_inclusao", nullable = false)
	private Usuario usuarioInclusao;

	@ManyToOne
	@JoinColumn(name = "id_usuario_alteracao", nullable = false)
	private Usuario usuarioAlteracao;

	@Column(name = "txt_valor_antigo")
	private String txtValorAntigo;

	@Column(name = "txt_Valor_novo")
	private String txtValorNovo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dat_acao")
	private Date data_acao;

	public PartidoAcao() {
	}

	public PartidoAcao(Acao acao, Usuario usuarioInclusao,
			Usuario usuarioAlteracao, String txtValorAntigo,
			String txtValorNovo, Date data_acao) {
		this.acao = acao;
		this.usuarioInclusao = usuarioInclusao;
		this.usuarioAlteracao = usuarioAlteracao;
		this.txtValorAntigo = txtValorAntigo;
		this.txtValorNovo = txtValorNovo;
		this.data_acao = data_acao;
	}

	public long getIdPartidoAcao() {
		return idPartidoAcao;
	}

	public void setIdPartidoAcao(long idPartidoAcao) {
		this.idPartidoAcao = idPartidoAcao;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Usuario getUsuarioInclusao() {
		return usuarioInclusao;
	}

	public void setUsuarioInclusao(Usuario usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	public Usuario getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	public String getTxtValorAntigo() {
		return txtValorAntigo;
	}

	public void setTxtValorAntigo(String txtValorAntigo) {
		this.txtValorAntigo = txtValorAntigo;
	}

	public String getTxtValorNovo() {
		return txtValorNovo;
	}

	public void setTxtValorNovo(String txtValorNovo) {
		this.txtValorNovo = txtValorNovo;
	}

	public Date getData_acao() {
		return data_acao;
	}

	public void setData_acao(Date data_acao) {
		this.data_acao = data_acao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((data_acao == null) ? 0 : data_acao.hashCode());
		result = prime * result
				+ (int) (idPartidoAcao ^ (idPartidoAcao >>> 32));
		result = prime * result
				+ ((txtValorAntigo == null) ? 0 : txtValorAntigo.hashCode());
		result = prime * result
				+ ((txtValorNovo == null) ? 0 : txtValorNovo.hashCode());
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
		PartidoAcao other = (PartidoAcao) obj;
		if (data_acao == null) {
			if (other.data_acao != null)
				return false;
		} else if (!data_acao.equals(other.data_acao))
			return false;
		if (idPartidoAcao != other.idPartidoAcao)
			return false;
		if (txtValorAntigo == null) {
			if (other.txtValorAntigo != null)
				return false;
		} else if (!txtValorAntigo.equals(other.txtValorAntigo))
			return false;
		if (txtValorNovo == null) {
			if (other.txtValorNovo != null)
				return false;
		} else if (!txtValorNovo.equals(other.txtValorNovo))
			return false;
		return true;
	}
}
