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

<br />
<br />
<br />

<div class="row">
	<div class="eight columns" style="position: inherit; z-index: -1;">
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
					<table class="tableBorder">
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
					<table class="tableBorder">
						<c:choose>
							<c:when test="${fn:length(usuarios) gt 0}">
								<tr>
									<td><b><c:out value="Nome " /></b></td>
									<td><b><c:out value="Login " /></b></td>
									<td><b><c:out value="Permissoes " /></b></td>
								</tr>
								<c:forEach var="lista" items="${usuarios}">
									<tr>
										<td><c:out value="${lista.nome}" /></td>
										<td><c:out value="${lista.login}" /></td>
										<td><c:forEach var="permissao"
												items="${lista.permissoesUsuario}">
												<c:out value="${permissao.role}" />
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
				</c:otherwise>
			</c:choose>
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

<br />
<br />
<br />
<br />
<br />
<br />
