<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmGerenciamentoUsuarios"
			action="/portal/gerenciamento/gerenciarUsuarios/editarUsuario">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="msg" value="${mensagem}" />
			<input type="hidden" name="idUsuario" value="${usuario.id}" />

			<center>
				<b><c:out value="Gerenciamento de Usuarios" /></b>
			</center>

			<br />
			<br />

			<c:choose>
				<c:when test="${acao eq 'editar'}">
					<center>
						<table class="tableBorder">
							<tr style="background: white">
								<td><b><c:out value="Nome: " /></b></td>
								<td><c:out value="${usuario.nome}" /></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Login: " /></b></td>
								<td><c:out value="${usuario.login}" /></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Permissoes: " /></b></td>
								<td><select name="permissoes" multiple>
										<c:forEach var="roles" items="${roles}">
											<option value="${roles}"
												<c:forEach var = "permissao" items = "${usuario.permissoesUsuario}">
																<c:if test="${fn:contains(permissao.role,roles)}">selected</c:if> 
																</c:forEach>>${roles}</option>
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td style="background: white"><a href="#"
									onclick="valida();" class="tiny button success"><c:out
											value="Editar" /></a></td>
							</tr>
						</table>
					</center>
				</c:when>
				<c:otherwise>
					<center>
						<table class="tableBorder">
							<c:choose>
								<c:when test="${fn:length(usuarios) gt 0}">
									<tr>
										<td><b><c:out value="Nome " /></b></td>
										<td><b><c:out value="Login " /></b></td>
										<td><b><c:out value="Permissoes " /></b></td>
									</tr>
									<c:forEach var="lista" items="${usuarios}">
										<tr style="background: white">
											<td><c:out value="${lista.nome}" /></td>
											<td><c:out value="${lista.login}" /></td>
											<td><c:forEach var="permissao"
													items="${lista.permissoesUsuario}">
													<c:set var="role" value="${fn:split(permissao.role,'_')}" />
													<c:out value="${role[1]};" />
												</c:forEach></td>
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
					</center>
				</c:otherwise>
			</c:choose>
		</sf:form>

		<script type="text/javascript">
			function valida() {
				if ($('[name=permissoes]').val() == null) {
					alert("Selecione pelo menos uma permissao");
					$('[name=permissoes]').focus();
				} else {
					$('[name=frmGerenciamentoUsuarios]').submit();
				}
			}

			$(function() {
				if ($('[name=msg]').val() != '') {
					alert($('[name=msg]').val());
					$('[name=frmGerenciamentoUsuarios]').attr("action",
							"../../gerenciar/usuarios");
					$('[name=frmGerenciamentoUsuarios]').submit();
				}
			});
		</script>
	</div>
</div>

<br />
<br />
<br />
<br />
<br />
<br />
