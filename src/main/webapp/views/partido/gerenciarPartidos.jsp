<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmGerenciarPartidos">
			<input type="hidden" name="msg" value="${mensagem}" />

			<center>
				<b><c:out value="Gerenciamento de Partidos" /></b><br/><br/>
			</center>

			<center>
				<table class="tableBorder">
					<c:choose>
						<c:when test="${fn:length(partidos) gt 0}">
							<tr>
								<td><b><c:out value="Nome" /></b></td>
								<td><b><c:out value="Sigla" /></b></td>
								<td><b><c:out value="Descricao" /></b></td>
								<td><b><c:out value="Status" /></b></td>
							</tr>
							<c:forEach var="lista" items="${partidos}">
								<tr>
									<td style="background: white"><c:out value="${lista.nome}" /></td>
									<td style="background: white"><c:out value="${lista.sigla}" /></td>
									<td style="background: white"><c:out value="${lista.descricao}" /></td>
									<td style="background: white"><c:out value="${lista.statusPartido.nome}" /></td>
									<td style="background: white"><a href="/portal/partido/gerenciarPartidos/aprovar/${lista.id}"><c:out value="Aprovar Partido" /></a></td>
									<td style="background: white"><a href="/portal/partido/gerenciarPartidos/reprovar/${lista.id}"><c:out value="Reprovar Partido" /></a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td><c:out value="Não existem partidos cadastrados ou para serem aprovados" /></td>
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
			$('[name=frmGerenciarPartidos]').attr("action",
					"../../../partido/gerenciarPartidos");
			$('[name=frmGerenciarPartidos]').submit();
		}
	});
</script>