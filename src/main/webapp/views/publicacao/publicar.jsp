<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPublicar">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<c:choose>
				<c:when test="${acao eq 'editar'}">
					<center>
						<input type="hidden" name="nomePolitico" value="${publicacoes[0].politico.nome}" /> 
						<input type="hidden" name="id_partido" value="${publicacoes[0].politico.partido.id}" />
					</center>
				</c:when>
				<c:otherwise>
					<center>
						<input type="hidden" name="nomePolitico" value="${publicacao.politico.nome}" /> 
						<input type="hidden" name="id_partido" value="${publicacao.politico.partido.id}" />
					</center>
				</c:otherwise>
			</c:choose>

			<br />
			

			<c:choose>
				<c:when test="${acao eq 'editar'}">
					
					<center><h3><c:out value="${publicacoes[0].politico.nome}" /> - <c:out value="${publicacoes[0].politico.partido.sigla}" /></h3></center>
					<c:forEach var="lista" items="${publicacoes}">
							<table class="tableBorder">
								<tr style="background: white">
									<td><b><c:out value="Titulo: " /></b></td>
									<td><c:out value="${lista.titulo}" /></td>
								</tr>
								<tr style="background: white">
									<td><b><c:out value="Texto: " /></b></td>
									<td><c:out value="${lista.texto}" /></td>
								</tr>
							</table>
						<br />
					</c:forEach>
				</c:when>
				<c:otherwise>
					<center>
						<table class="tableBorder">
							<tr style="background: white">
								<td><b><c:out value="Titulo: " /></b></td>
								<td><input name="titulo" value="${publicacao.titulo}" /></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Texto: " /></b></td>
								<td><textarea name="texto" cols="100" rows="10">${publicacao.texto}</textarea></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Politico: " /></b></td>
								<td><c:out value="${publicacao.politico.nome}" /></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Partido: " /></b></td>
								<td><c:out value="${publicacao.politico.partido.sigla}" /></td>
							</tr>
						</table>
					</center>
				</c:otherwise>
			</c:choose>

			<br />
			<br />

			<c:if test="${acao eq 'salvar'}">
				<sec:authorize
					access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
					<center>
						<input type="submit" value="Publicar"
							onclick="javascript:valida();" class="tiny button success" />
					</center>
				</sec:authorize>
			</c:if>
		</sf:form>
	</div>
</div>

<script type="text/javascript">
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
</script>