package br.com.itexto.springforum.Enum;

public enum EnumPartidoAcao {

	CADASTRAR_PARTIDO(1), EDITAR_PARTIDO(2), EXCLUIR_PARTIDO(3);

	private int acao;

	private EnumPartidoAcao(int acao) {
		this.acao = acao;
	}

	public int getAcao() {
		return acao;
	}

}
