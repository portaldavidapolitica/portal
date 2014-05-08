<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="eight columns">
		<sf:form name="frmGerenciarPublicacoes">
			<input type="hidden" name="msg" value="${mensagem}" />
			<table>
				<c:choose>
					<c:when test="${fn:length(publicacoes) gt 0}">
						<c:forEach var="lista" items="${publicacoes}">
							<tr>
								<td><c:out value="Titulo" /></td>
								<td><c:out value="${lista.titulo}" /></td>
								<td><c:out value="Texto" /></td>
								<td><c:out value="${lista.texto}" /></td>
								<td><c:out value="Politico" /></td>
								<td><c:out value="${lista.politico.nome}" /></td>
								<td><c:out value="Partido" /></td>
								<td><c:out value="${lista.politico.partido.nome}" /></td>
								<td><c:out value="Status" /></td>
								<td><c:out value="${lista.statusPublicacao.nome}" /></td>
								<td><a
									href="/portal/publicacao/gerenciarPublicacoes/aprovar/${lista.id}"><c:out
											value="Aprovar Publicacao" /></a></td>
								<td><a
									href="/portal/publicacao/gerenciarPublicacoes/reprovar/${lista.id}"><c:out
											value="Reprovar Publicacao" /></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td><c:out value="Não existem publicações cadastradas" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>

			<br />
			<br />
			<a href="/portal">Voltar</a>
		</sf:form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		if ($('[name=msg]').val() != '') {
			alert($('[name=msg]').val());
		}
	});
</script>
