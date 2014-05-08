<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div class="row">
	
	<div class="eight columns">
		<sf:form name="frmGerenciamentoUsuarios"
			action="/portal/gerenciamento/gerenciarUsuarios/editarUsuario">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="msg" value="${mensagem}" />
			<input type="hidden" name="idUsuario" value="${usuario.id}" />
			<c:choose>
				<c:when test="${acao eq 'editar'}">
					<table>
						<tr>
							<td><c:out value="Nome: " /></td>
							<td><c:out value="${usuario.nome}" /></td>
						</tr>
						<tr>
							<td><c:out value="Login: " /></td>
							<td><c:out value="${usuario.login}" /></td>
						</tr>
						<tr>
							<td><select name="permissoes" multiple>
									<c:forEach var="permissao" items="${usuario.permissoesUsuario}">
										<option value="${permissao.id}"
											<c:forEach var = "roles" items = "${roles}">
																<c:if test="${roles eq permissao.role}">selected</c:if> 
																</c:forEach>>${permissao.role}</option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td><a href="#"
								onclick="javascript:document.frmGerenciamentoUsuarios.submit();"
								class="tiny button success"><c:out value="Editar" /></a></td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<table>
						<c:choose>
							<c:when test="${fn:length(usuarios) gt 0}">
								<c:forEach var="lista" items="${usuarios}">
									<tr>
										<td><c:out value="Nome: " /></td>
										<td><c:out value="${lista.nome}" /></td>
										<td><c:out value="Login: " /></td>
										<td><c:out value="${lista.login}" /></td>
										<td><c:out value="Permissoes: " /></td>
										<c:forEach var="permissao" items="${lista.permissoesUsuario}">
											<td><c:out value="${permissao.role}" /></td>
										</c:forEach>
										<td><a
											href="/portal/gerenciamento/gerenciarUsuarios/editar/${lista.id}"><c:out
													value="Editar" /></a></td>
										<td><a
											href="/portal/gerenciamento/gerenciarUsuarios/excluir/${lista.id}"><c:out
													value="Excluir" /></a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td><c:out value="Não existem usuarios cadastrados!" /></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</c:otherwise>
			</c:choose>

			<br />
			<br />
			<a href="/portal">Voltar</a>
		</sf:form>

		<script type="text/javascript">
			$(function() {
				if ($('[name=msg]').val() != '') {
					alert($('[name=msg]').val());
				}
			});
		</script>
	</div>
</div>
