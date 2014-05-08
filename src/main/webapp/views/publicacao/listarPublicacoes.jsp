<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="eight columns">
		<sf:form name="frmListaPublicacao">

			<input type="hidden" name="msg" value="${mensagem}" />
			<sec:authorize ifAnyGranted="ROLE_MEMBRO,ROLE_ADMIN">
				<table>
					<tr>
						<td><c:out value="Nome do Politico: " /></td>
						<td><input name="nomePolitico" value="${nomePolitico}" /></td>
						<td><c:out value="Partido: " /></td>
						<td width="300px"><select name="id_partido">
								<option value="" selected><c:out
										value="Selecione um Elemento" /></option>
								<c:forEach var="item" items="${partidos}">
									<option value="${item.id}"
										<c:if test="${item.id eq id_partido}">selected</c:if>>
										<c:out value="${item.nome}" />
									</option>
								</c:forEach>
						</select></td>
						<td><a href="/portal/publicacao/pesquisar"><c:out
									value="Pesquisar Politico" /></a></td>
						<td><a href="/portal/publicacao/procurar/publicacoes"><c:out
									value="Procurar Publicacoes" /></a></td>
					</tr>

					<c:if test="${!empty politico}"></c:if>
					<c:forEach var="listaPolitico" items="${politico}">
						<tr>
							<td><c:out value="Politico" /></td>
							<td><c:out value="${listaPolitico.nome}" /></td>
							<td><c:out value="Partido" /></td>
							<td><c:out value="${listaPolitico.partido.nome}" /></td>
							<td><a
								href="/portal/publicacao/publicar/${listaPolitico.nome},${listaPolitico.partido.id}"><c:out
										value="Fazer Publicacao" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</sec:authorize>

			<table>
				<c:choose>
					<c:when test="${!empty publicacoes}">
						<table>
							<tr>
								<td><b><c:out value="Publicacoes" /></b></td>
							</tr>
							<c:forEach var="lista" items="${publicacoes}">
								<tr>
									<td><c:out value="Titulo: " /></td>
									<td><c:out value="${lista.titulo}" /></td>
									<td><c:out value="Texto: " /></td>
									<td><c:out value="${lista.texto}" /></td>
									<td><c:out value="Politico: " /></td>
									<td><c:out value="${lista.politico.nome}" /></td>
									<td><c:out value="Partido: " /></td>
									<td><c:out value="${lista.politico.partido.nome}" /></td>
									<td><a href="/portal/publicacao/visualizar/${lista.id}"><c:out
												value="Visualizar Publicacao" /></a></td>
								</tr>
							</c:forEach>
						</table>
					</c:when>
					<c:otherwise>
						<tr>
							<td><c:out value="Não existem publicacoes cadastradas" /></td>
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
