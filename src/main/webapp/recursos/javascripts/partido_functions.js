function valida() {
	if ($('[name=nome]').val() == '') {
		alert("Preencha o nome do partido");
		$('[name=nome]').focus();
	} else if ($('[name=sigla]').val() == '') {
		alert("Preencha a sigla do partido");
		$('[name=sigla]').focus();
	} else if ($('[name=nome]').val().length < 5
			|| $('[name=nome]').val().length > 50) {
		alert("O nome do partido deve conter de 5 a 50 caracteres");
		$('[name=nome]').focus();
	} else if ($('[name=sigla]').val().length < 2
			|| $('[name=sigla]').val().length > 6) {
		alert("A sigla do partido deve conter de 2 a 5 caracteres");
		$('[name=sigla]').focus();
	} else {
		$('[name=frmPartido]').attr(
				"action",
				'/portal/cadastro/cadastrarPartido/'
						+ $('[name=idPartido]').val());
		$('[name=frmPartido]').submit();
	}
}

$(function(){
	if($('[name=msg]').val() != ''){
		alert($('[name=msg]').val());	
	}
});

