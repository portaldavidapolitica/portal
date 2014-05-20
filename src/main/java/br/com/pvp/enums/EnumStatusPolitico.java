package br.com.pvp.enums;

public enum EnumStatusPolitico {

	APROVADO(1), REPROVADO(2), AGUARDANDO_APROVACAO(3);

	private int acao;

	private EnumStatusPolitico(int acao) {
		this.acao = acao;
	}

	public int getAcao() {
		return acao;
	}

}
