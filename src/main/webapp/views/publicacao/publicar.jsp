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
								href="<c:url value="publicacao/gerenciarPublicacoes"/>"><c:out
										value="Gerenciar Publicacoes" /></a></li>
							<li><a href="<c:url value="gerenciar/usuarios"/>"><c:out
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

<div class="row">
	<div class="eight columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPublicar" action="cadastrar">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="nomePolitico"
				value="${publicacao.politico.nome}" />
			<input type="hidden" name="id_partido"
				value="${publicacao.politico.partido.id}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<br />
			<br />

			<center>
				<b><c:out value="Cadastrar Publicação" /></b>
			</center>

			<br />
			<br />

			<table class="tableBorder">
				<tr>
					<td><b><c:out value="Titulo: " /></b></td>
					<td><input name="titulo" value="${publicacao.titulo}" /></td>
				</tr>
				<tr>
					<td><b><c:out value="Texto: " /></b></td>
					<td><textarea name="texto" cols="100" rows="10">${publicacao.texto}</textarea></td>
				</tr>
				<tr>
					<td><b><c:out value="Politico: " /></b></td>
					<td><c:out value="${publicacao.politico.nome}" /></td>
				</tr>
				<tr>
					<td><b><c:out value="Partido: " /></b></td>
					<td><c:out value="${publicacao.politico.partido.nome}" /></td>
				</tr>

				<c:choose>
					<c:when test="${acao eq 'editar'}">
						<sec:authorize ifAnyGranted="'ROLE_MODERADOR','ROLE_ADMIN'">
							<tr>
								<td><input type="submit" value="Editar Publicacao"
									class="tiny button success" /></td>
							</tr>
						</sec:authorize>
					</c:when>
					<c:otherwise>
						<sec:authorize access="hasRole('ROLE_MEMBRO')">
							<tr>
								<td><input type="submit" value="Publicar"
									class="tiny button success" /></td>
							</tr>
						</sec:authorize>
					</c:otherwise>
				</c:choose>
			</table>

			<br />
			<br />
			<a href="/portal/publicacao">Voltar</a>
		</sf:form>
	</div>
</div>
