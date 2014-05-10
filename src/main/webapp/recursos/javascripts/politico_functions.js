/**
 * 
 */

function valida() {
	if ($('[name=nome]').val() == '') {
		alert("Preencha o nome do politico");
		$('[name=nome]').focus();
	} else if ($('[name=id_partido]').val() == '') {
		alert("Selecione um partido");
		$('[name=id_partido]').focus();
	} else {
		$('[name=frmPolitico]').attr(
				"action",
				'/portal/cadastro/cadastrarPolitico/'
						+ $('[name=id_partido]').val());
		$('[name=frmPolitico]').submit();
	}
}

$(function(){
	if($('[name=msg]').val() != ''){
		alert($('[name=msg]').val());
		$('[name=frmPolitico]').attr("action","../../cadastro/politico");
		$('[name=frmPolitico]').submit();
	}
});
