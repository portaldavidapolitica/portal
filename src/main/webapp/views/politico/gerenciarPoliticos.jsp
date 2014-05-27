<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmGerenciarPoliticos">
			<input type="hidden" name="msg" value="${mensagem}" />

			<center>
				<b><c:out value="Gerenciamento de Politicos" /></b><br/><br/>
			</center>

			<center>
				<table class="tableBorder">
					<c:choose>
						<c:when test="${fn:length(politicos) gt 0}">
							<tr>
								<td><b><c:out value="Nome" /></b></td>
								<td><b><c:out value="Partido" /></b></td>
								<td><b><c:out value="Status" /></b></td>
							</tr>
							<c:forEach var="lista" items="${politicos}">
								<tr>
									<td style="background: white"><c:out value="${lista.nome}" /></td>
									<td style="background: white"><c:out value="${lista.partido.nome}" /></td>
									<td style="background: white"><c:out value="${lista.statusPolitico.nome}" /></td>
									<td style="background: white"><a href="/portal/politico/gerenciarPoliticos/aprovar/${lista.id}"><c:out value="Aprovar Politico" /></a></td>
									<td style="background: white"><a href="/portal/politico/gerenciarPoliticos/reprovar/${lista.id}"><c:out value="Reprovar Politico" /></a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td><c:out value="Não existem politicos cadastrados ou para serem aprovados" /></td>
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
			$('[name=frmGerenciarPoliticos]').attr("action",
					"../../../politico/gerenciarPoliticos");
			$('[name=frmGerenciarPoliticos]').submit();
		}
	});
</script>