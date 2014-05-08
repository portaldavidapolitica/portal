<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="four columns">
		<h4>Bem vindo(a) ao Portal da vida política</h4>
		<p>Esperamos que você encontre informações úteis e que também
			compartilhe informações com os outros usuários.</p>
	</div>
	<div class="eight columns">

		<sf:form name="frmPartido" enctype="multipart/form-data">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPartido" value="${partido.id}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<sec:authorize access="hasRole('ROLE_MEMBRO')">
				<table>
					<tr>
						<td><c:out value="Nome: " /></td>
						<td><input name="nome" value="${partido.nome}" class="four" /></td>
					</tr>

					<tr>
						<td><c:out value="Sigla: " /></td>
						<td><input name="sigla" value="${partido.sigla}" class="four" /></td>
					</tr>

					<tr>
						<td><c:out value="Descricao: " /></td>
						<td><textarea name="descricao" rows="5" cols="100"
								class="three"><c:out value="${partido.descricao}" /></textarea></td>
					</tr>

					<tr>
						<td><c:out value="Avatar: " /></td>
						<td><input type="file" name="avatar" /></td>
					</tr>

					<tr>
						<td><a href="#" onclick="javascript:valida();"
							class="tiny button success"><c:out value="Cadastrar" /></a></td>
					</tr>
				</table>

				<br />
			</sec:authorize>

			<c:if test="${!empty partidos}">
				<table>
					<tr>
						<td><b><c:out value="Partidos Cadastrados" /></b></td>
					</tr>

					<c:forEach var="lista" items="${partidos}">
						<tr>
							<td><c:out value="Nome:" /></td>
							<td><c:out value="${lista.nome}" /></td>
							<td><c:out value="Sigla:" /></td>
							<td><c:out value="${lista.sigla}" /></td>
							<td><c:out value="Descricao:" /></td>
							<td><c:out value="${lista.descricao}" /></td>
							<sec:authorize access="hasRole('ROLE_MEMBRO')">
								<td><a href="/portal/cadastro/partido/editar/${lista.id}"><c:out
											value="Editar" /></a></td>
								<td><a href="/portal/cadastro/partido/excluir/${lista.id}"><c:out
											value="Excluir" /></a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:if>

			<br />
			<br />
			<a href="/portal"><c:out value="Voltar" /></a>
		</sf:form>
		<script type="text/javascript"
			src="<c:url value="/recursos/javascripts/partido_functions.js"/>">
			
		</script>
	</div>
</div>