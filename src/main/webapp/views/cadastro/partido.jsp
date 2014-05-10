<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div style="background: #48762A;">
	<div class="row">
		<div class="twelve columns">
			<c:choose>
				<c:when test="${empty acao}">
					<ul id="side-nav">
						<li><a href="<c:url value="/"/>"><c:out value="Home" /></a></li>
						<sec:authorize access="hasAnyRole('ROLE_MODERADOR,ROLE_ADMIN')">
							<li><a href="#">Gerenciamento</a>
								<ul>
									<li><a
										href="<c:url value="../publicacao/gerenciarPublicacoes"/>"><c:out
												value="Gerenciar Publicacoes" /></a></li>
									<li><a
										href="<c:url value="../politico/gerenciarPoliticos"/>"><c:out
												value="Gerenciar Politicos" /></a></li>
									<li><a
										href="<c:url value="../partido/gerenciarPartidos"/>"><c:out
												value="Gerenciar Partidos" /></a></li>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a href="<c:url value="../gerenciar/usuarios"/>"><c:out
													value="Gerenciar Usuarios" /></a></li>
									</sec:authorize>
								</ul></li>
						</sec:authorize>

						<sec:authorize
							access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
							<li><a href="#">Cadastros</a>
								<ul>
									<li><a href="<c:url value="../cadastro/partido"/>"><c:out
												value="Cadastro Partido" /></a></li>
									<li><a href="../cadastro/politico"><c:out
												value="Cadastro Politico" /></a></li>
								</ul></li>
						</sec:authorize>

						<li><a href="#">Consulta</a>
							<ul>
								<li><a href="../cadastro/politico"><c:out
											value="Consulta Politicos" /></a></li>
								<li><a href="<c:url value="../cadastro/partido"/>"><c:out
											value="Consulta Partidos" /></a></li>
								<li><a href="<c:url value="../publicacao"/>"><c:out
											value="Consulta de Publicacoes" /></a></li>
							</ul>
						<li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul id="side-nav">
						<li><a href="<c:url value="/"/>"><c:out value="Home" /></a></li>
						<sec:authorize access="hasAnyRole('ROLE_MODERADOR,ROLE_ADMIN')">
							<li><a href="#">Gerenciamento</a>
								<ul>
									<li><a
										href="<c:url value="../../../publicacao/gerenciarPublicacoes"/>"><c:out
												value="Gerenciar Publicacoes" /></a></li>
									<li><a
										href="<c:url value="../../../politico/gerenciarPoliticos"/>"><c:out
												value="Gerenciar Politicos" /></a></li>
									<li><a
										href="<c:url value="../../../partido/gerenciarPartidos"/>"><c:out
												value="Gerenciar Partidos" /></a></li>
									<sec:authorize access="hasRole('ROLE_ADMIN')">
										<li><a
											href="<c:url value="../../../gerenciar/usuarios"/>"><c:out
													value="Gerenciar Usuarios" /></a></li>
									</sec:authorize>
								</ul></li>
						</sec:authorize>

						<sec:authorize
							access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
							<li><a href="#">Cadastros</a>
								<ul>
									<li><a href="<c:url value="../../../cadastro/partido"/>"><c:out
												value="Cadastro Partido" /></a></li>
									<li><a href="../../../cadastro/politico"><c:out
												value="Cadastro Politico" /></a></li>
								</ul></li>
						</sec:authorize>

						<li><a href="#">Consulta</a>
							<ul>
								<li><a href="../../../cadastro/politico"><c:out
											value="Consulta Politicos" /></a></li>
								<li><a href="<c:url value="../../../cadastro/partido"/>"><c:out
											value="Consulta Partidos" /></a></li>
								<li><a href="<c:url value="../../../publicacao"/>"><c:out
											value="Consulta de Publicacoes" /></a></li>
							</ul>
						<li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<br />
<br />
<br />

<center>
	<b><c:out value="Cadastro de Partidos" /></b>
</center>

<br />
<br />
<br />

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPartido" enctype="multipart/form-data">
			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPartido" value="${partido.id}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<sec:authorize
				access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
				<center>
					<table class="tableBorder">
						<tr style="background: white">
							<td><c:out value="Nome: " /></td>
							<td><input name="nome" value="${partido.nome}" class="four" /></td>
						</tr>

						<tr style="background: white">
							<td><c:out value="Sigla: " /></td>
							<td><input name="sigla" value="${partido.sigla}"
								class="four" /></td>
						</tr>

						<tr style="background: white">
							<td><c:out value="Descricao: " /></td>
							<td><textarea name="descricao" rows="5" cols="100"
									class="three"><c:out value="${partido.descricao}" /></textarea></td>
						</tr>

						<tr style="background: white">
							<td><c:out value="Avatar: " /></td>
							<td><input type="file" name="avatar" /></td>
						</tr>

						<tr style="background: white">
							<td><a href="#" onclick="javascript:valida();"
								class="tiny button success"><c:out value="Cadastrar" /></a></td>
						</tr>
					</table>
				</center>
				<br />
			</sec:authorize>

			<c:if test="${!empty partidos}">
				<center>
					<table class="tableBorder">
						<tr>
							<td align="center" colspan="4"><b><c:out
										value="Partidos Cadastrados" /></b></td>
						</tr>
						<tr style="background: white">
							<td><b><c:out value="Nome" /></b></td>
							<td><b><c:out value="Sigla" /></b></td>
							<td><b><c:out value="Descricao" /></b></td>
						</tr>

						<c:forEach var="lista" items="${partidos}">
							<tr style="background: white">
								<td><c:out value="${lista.nome}" /></td>
								<td><c:out value="${lista.sigla}" /></td>
								<td><c:out value="${lista.descricao}" /></td>
								<sec:authorize
									access="hasAnyRole('ROLE_MODERADOR','ROLE_ADMIN')">
									<td><a href="/portal/cadastro/partido/editar/${lista.id}"><c:out
												value="Editar" /></a></td>
									<td><a href="/portal/cadastro/partido/excluir/${lista.id}"><c:out
												value="Excluir" /></a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</table>
				</center>
			</c:if>

			<br />
			<br />
		</sf:form>
		<script type="text/javascript"
			src="<c:url value="/recursos/javascripts/partido_functions.js"/>">
			
		</script>
	</div>
</div>