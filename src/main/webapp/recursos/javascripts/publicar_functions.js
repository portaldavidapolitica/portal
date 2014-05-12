

function valida(){
	if ($('[name=titulo]').val() == '') {
		alert("Preencha o titulo da Publicacao");
		$('[name=titulo]').focus();
	} else if ($('[name=texto]').val() == '') {
		alert("Preencha o texto da Publicacao");
		$('[name=texto]').focus();
	} else {
		$('[name=frmPublicar]').attr(
				"action",
				'/portal/publicacao/cadastrar');
		$('[name=frmPublicar]').submit();
	}
}



















