<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmGerenciarPublicacoes">
			<input type="hidden" name="msg" value="${mensagem}" />

			<center>
				<b><c:out value="Gerenciamento de Publicações" /></b><br/><br/>
			</center>

			<center>
				<table class="tableBorder">
					<c:choose>
						<c:when test="${fn:length(publicacoes) gt 0}">
							<tr>
								<td><b><c:out value="Titulo" /></b></td>
								<td><b><c:out value="Politico" /></b></td>
								<td><b><c:out value="Partido" /></b></td>
								<td><b><c:out value="Status" /></b></td>
							</tr>
							<c:forEach var="lista" items="${publicacoes}">
								<tr>
									<td style="background: white"><c:out value="${lista.titulo}" /></td>
									<td style="background: white"><c:out value="${lista.politico.nome}" /></td>
									<td style="background: white"><c:out value="${lista.politico.partido.sigla}" /></td>
									<td style="background: white"><c:out value="${lista.statusPublicacao.nome}" /></td>
									<td style="background: white"><a href="/portal/publicacao/visualizarPublicacao/${lista.id}"><c:out value="Visualizar Publicacao" /></a></td>
									<td style="background: white"><a href="/portal/publicacao/gerenciarPublicacoes/aprovar/${lista.id}"><c:out value="Aprovar Publicacao" /></a></td>
									<td style="background: white"><a href="/portal/publicacao/gerenciarPublicacoes/reprovar/${lista.id}"><c:out value="Reprovar Publicacao" /></a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td><c:out value="Não existem publicações cadastradas ou para serem aprovadas" /></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</center>
		</sf:form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		if ($('[name=msg]').val() != '') {
			alert($('[name=msg]').val());
			$('[name=frmGerenciarPublicacoes]').attr("action",
					"../../../publicacao/gerenciarPublicacoes");
			$('[name=frmGerenciarPublicacoes]').submit();
		}
	});
</script>
