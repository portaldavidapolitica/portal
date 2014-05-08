<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="eight columns">
		<sf:form name="frmPublicar" action="cadastrar">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="nomePolitico"
				value="${publicacao.politico.nome}" />
			<input type="hidden" name="id_partido"
				value="${publicacao.politico.partido.id}" />
			<input type="hidden" name="msg" value="${mensagem}" />	
			<table>
				<tr>
					<td><c:out value="Titulo" /></td>
					<td><input name="titulo" value="${publicacao.titulo}" /></td>
				</tr>
				<tr>
					<td><c:out value="Texto" /></td>
					<td><textarea name="texto" cols="100" rows="10">${publicacao.texto}</textarea></td>
				</tr>
				<tr>
					<td><c:out value="Politico" /></td>
					<td><c:out value="${publicacao.politico.nome}" /></td>
				</tr>
				<tr>
					<td><c:out value="Partido" /></td>
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
