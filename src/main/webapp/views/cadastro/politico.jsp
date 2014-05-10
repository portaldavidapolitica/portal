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


<center>
	<b><c:out value="Cadastro de Politicos" /></b>
</center>

<div class="row">
	<div class="eight columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPolitico" enctype="multipart/form-data">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPolitico" value="${idPolitico}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<table class="tableBorder">
				<c:choose>
					<c:when test="${fn:length(partidos) gt 0}">
						<sec:authorize access="hasRole('ROLE_MEMBRO')">
							<tr>
								<td><b><c:out value="Nome: " /></b></td>
								<td><input name="nome" value="${nome}" class="four" /></td>
							</tr>
							<tr>
								<td><b><c:out value="Partido: " /></b></td>
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
								<td><b><c:out value="Avatar: " /></b></td>
								<td><input type="file" name="avatar" /></td>
							</tr>

							<tr>
								<td><a href="#" onclick="javascript:valida();"
									class="tiny button success"><c:out value="Cadastrar" /></a></td>
							</tr>
						</sec:authorize>

						<br />
						<br />
						<br />


						<c:if test="${!empty politicos}">
							<table class="tableBorder">
								<tr>
									<td><b><c:out value="Politicos Cadastrados" /></b></td>
								</tr>

								<tr>
									<td><b><c:out value="Nome" /></b></td>
									<td><b><c:out value="Partido" /></b></td>
								</tr>
								<c:forEach var="lista" items="${politicos}">
									<tr>
										<td><c:out value="${lista.nome}" /></td>
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
									value="N�o existem partidos cadastrados, para cadastrar um politico � preciso primeiro cadastrar um partido" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
			<br />
			<br />
		</sf:form>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/recursos/javascripts/politico_functions.js"/>">
	
</script>
