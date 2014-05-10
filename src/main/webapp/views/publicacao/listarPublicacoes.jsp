<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div style="background: #48762A;">
	<div class="row">
		<div class="twelve columns">
			<ul id="side-nav">

				<li><a href="<c:url value="/"/>"><c:out value="Home" /></a></li>
				<sec:authorize access="hasAnyRole('ROLE_MODERADOR,ROLE_ADMIN')">
					<li><a href="#">Gerenciamento</a>
						<ul>
							<li><a
								href="<c:url value="/portal/publicacao/gerenciarPublicacoes"/>"><c:out
										value="Gerenciar Publicacoes" /></a></li>
							<li><a href="<c:url value="/portal/gerenciar/usuarios"/>"><c:out
										value="Gerenciar Usuarios" /></a></li>
						</ul></li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_MEMBRO')">
					<li><a href="#">Cadastros</a>
						<ul>
							<li><a href="<c:url value="cadastro/partido"/>"><c:out
										value="Cadastro Partido" /></a></li>
							<li><a href="cadastro/politico"><c:out
										value="Cadastro Politico" /></a></li>
						</ul></li>
				</sec:authorize>

				<li><a href="#">Consulta</a>
					<ul>
						<li><a href="cadastro/politico"><c:out
									value="Consulta Politicos" /></a></li>
						<li><a href="<c:url value="cadastro/partido"/>"><c:out
									value="Consulta Partidos" /></a></li>
						<li><a href="<c:url value="publicacao"/>"><c:out
									value="Consulta de Publicacoes" /></a></li>
					</ul>
				<li>
			</ul>
		</div>
	</div>
</div>

<br />
<br />
<br />

<center>
	<b><c:out value="Listagem de Publicacoes" /></b>
</center>

<br />

<div class="row">
	<div class="eight columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmListaPublicacao">

			<input type="hidden" name="msg" value="${mensagem}" />
			<sec:authorize ifAnyGranted="ROLE_MEMBRO,ROLE_ADMIN">
				<table class="tableBorder">
					<tr>
						<td><b><c:out value="Nome do Politico" /></b></td>
						<td><b><c:out value="Partido" /></b></td>
					</tr>
					<tr>
						<td><input name="nomePolitico" value="${nomePolitico}" /></td>
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
					<tr>
						<td><b><c:out value="Politico" /></b></td>
						<td><b><c:out value="Partido" /></b></td>
					</tr>
					<c:forEach var="listaPolitico" items="${politico}">
						<tr>
							<td><c:out value="${listaPolitico.nome}" /></td>
							<td><c:out value="${listaPolitico.partido.nome}" /></td>
							<td><a
								href="/portal/publicacao/publicar/${listaPolitico.nome},${listaPolitico.partido.id}"><c:out
										value="Fazer Publicacao" /></a></td>
						</tr>
					</c:forEach>
				</table>
			</sec:authorize>

			<table class="tableBorder">
				<c:choose>
					<c:when test="${!empty publicacoes}">
						<table class="tableBorder">
							<tr>
								<td><b><c:out value="Publicacoes Cadastradas" /></b></td>
							</tr>
							<tr>
								<td><b><c:out value="Titulo" /></b></td>
								<td><b><c:out value="Politico" /></b></td>
								<td><b><c:out value="Partido" /></b></td>
							</tr>
							<c:forEach var="lista" items="${publicacoes}">
								<tr>
									<td><c:out value="${lista.titulo}" /></td>
									<td><c:out value="${lista.politico.nome}" /></td>
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
