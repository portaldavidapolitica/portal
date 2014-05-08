<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<sf:form name="frmPolitico" enctype="multipart/form-data">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPolitico" value="${idPolitico}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<table>
				<c:choose>
					<c:when test="${fn:length(partidos) gt 0}">
						<sec:authorize access="hasRole('ROLE_MEMBRO')">
							<tr>
								<td><c:out value="Nome: " /></td>
								<td><input name="nome" value="${nome}" class="four" /></td>
							</tr>
							<tr>
								<td><c:out value="Partido: " /></td>
								<td><select name="id_partido">
										<option value="" selected><c:out
												value="Selecione um Elemento" /></option>
										<c:forEach var="item" items="${partidos}">
											<option value="${item.id}"
												<c:if test="${item.id eq idPartido}">selected</c:if>>
												<c:out value="${item.nome}" />
											</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td><c:out value="Avatar: " /></td>
								<td><input type="file" name="avatar" /></td>
							</tr>

							<tr>
								<td><a href="#" onclick="javascript:valida();"
									class="tiny button success"><c:out value="Cadastrar" /></a></td>
							</tr>

							<br />
							<br />
							<br />
						</sec:authorize>

						<c:if test="${!empty politicos}">
							<table>
								<tr>
									<td><b><c:out value="Politicos Cadastrados" /></b></td>
								</tr>

								<c:forEach var="lista" items="${politicos}">
									<tr>
										<td><c:out value="Nome: " /></td>
										<td><c:out value="${lista.nome}" /></td>
										<td><c:out value="Partido: " /></td>
										<td><c:out value="${lista.partido.nome}" /></td>
										<sec:authorize access="hasRole('ROLE_MEMBRO')">
											<td><a
												href="/portal/cadastro/politico/editar/${lista.id}"><c:out
														value="Editar" /></a></td>
											<td><a
												href="/portal/cadastro/politico/excluir/${lista.id}"><c:out
														value="Excluir" /></a></td>
										</sec:authorize>
									</tr>
								</c:forEach>
							</table>
						</c:if>
					</c:when>
					<c:otherwise>
						<tr>
							<td><c:out
									value="Não existem partidos cadastrados, para cadastrar um politico é preciso primeiro cadastrar um partido" /></td>
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

<script type="text/javascript"
	src="<c:url value="/recursos/javascripts/politico_functions.js"/>">
	
</script>
