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
								href="<c:url value="../../publicacao/gerenciarPublicacoes"/>"><c:out
										value="Gerenciar Publicacoes" /></a></li>
							<li><a
								href="<c:url value="../../politico/gerenciarPoliticos"/>"><c:out
										value="Gerenciar Politicos" /></a></li>
							<li><a
								href="<c:url value="../../partido/gerenciarPartidos"/>"><c:out
										value="Gerenciar Partidos" /></a></li>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="<c:url value="../../gerenciar/usuarios"/>"><c:out
											value="Gerenciar Usuarios" /></a></li>
							</sec:authorize>
						</ul></li>
				</sec:authorize>

				<sec:authorize
					access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
					<li><a href="#">Cadastros</a>
						<ul>
							<li><a href="<c:url value="../../cadastro/partido"/>"><c:out
										value="Cadastro Partido" /></a></li>
							<li><a href="../../cadastro/politico"><c:out
										value="Cadastro Politico" /></a></li>
							<li><a href="<c:url value="../../publicacao"/>"><c:out
										value="Cadastro de Publicacoes" /></a></li>
						</ul></li>
				</sec:authorize>

				<li><a href="#">Consulta</a>
					<ul>
						<li><a href="../../cadastro/politico"><c:out
									value="Consulta Politicos" /></a></li>
						<li><a href="<c:url value="../../cadastro/partido"/>"><c:out
									value="Consulta Partidos" /></a></li>
						<li><a href="<c:url value="../../publicacao"/>"><c:out
									value="Consulta de Publicacoes" /></a></li>
					</ul>
				<li>
			</ul>
		</div>
	</div>
</div>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPublicar">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<br />
			<br />

			<c:choose>
				<c:when test="${acao eq 'editar'}">
					<center>
						<b><c:out value="Publicações" /></b> <input type="hidden"
							name="nomePolitico" value="${publicacoes[0].politico.nome}" /> <input
							type="hidden" name="id_partido"
							value="${publicacoes[0].politico.partido.id}" />
					</center>
				</c:when>
				<c:otherwise>
					<center>
						<b><c:out value="Publicação" /></b> <input type="hidden"
							name="nomePolitico" value="${publicacao.politico.nome}" /> <input
							type="hidden" name="id_partido"
							value="${publicacao.politico.partido.id}" />
					</center>
				</c:otherwise>
			</c:choose>

			<br />
			<br />

			<c:choose>
				<c:when test="${acao eq 'editar'}">
					<c:forEach var="lista" items="${publicacoes}">
						<center>
							<table class="tableBorder">
								<tr style="background: white">
									<td><b><c:out value="Titulo: " /></b></td>
									<td><c:out value="${lista.titulo}" /></td>
								</tr>
								<tr style="background: white">
									<td><b><c:out value="Texto: " /></b></td>
									<td><c:out value="${lista.texto}" /></td>
								</tr>
								<tr style="background: white">
									<td><b><c:out value="Politico: " /></b></td>
									<td><c:out value="${lista.politico.nome}" /></td>
								</tr>
								<tr style="background: white">
									<td><b><c:out value="Partido: " /></b></td>
									<td><c:out value="${lista.politico.partido.sigla}" /></td>
								</tr>
							</table>
						</center>
						<br />
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

<script type="text/javascript"
	src="<c:url value="/recursos/javascripts/publicar_functions.js"/>">
	
</script>


