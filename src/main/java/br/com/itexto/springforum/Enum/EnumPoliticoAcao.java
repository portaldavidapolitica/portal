package br.com.itexto.springforum.Enum;

public enum EnumPoliticoAcao {

	CADASTRAR_POLITICO(4), EDITAR_POLITICO(5), EXCLUIR_POLITICO(6);
	
	private int acao;
	
	private EnumPoliticoAcao(int acao) {
		this.acao = acao;
	}

	public int getAcao() {
		return acao;
	}
	
}
