
$(function() {
		if ($('[name=msg]').val() != '') {
			alert($('[name=msg]').val());
		}
	});
	
	function pesquisar(){
		var idPartido = 0;
		
		if($('[name=id_partido]').val() != ''){
			idPartido = $('[name=id_partido]').val();
		}
		
		$('[name=frmListaPublicacao]').attr("action", "/portal/publicacao/pesquisar/"+idPartido);
		$('[name=frmListaPublicacao]').submit();
	}
	
	