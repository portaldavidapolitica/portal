package br.com.pvp.enums;

public enum EnumStatusPublicacao {

	APROVADO(1), REPROVADO(2), AGUARDANDO_APROVACAO(3);

	private int acao;

	private EnumStatusPublicacao(int acao) {
		this.acao = acao;
	}

	public int getAcao() {
		return acao;
	}

}
