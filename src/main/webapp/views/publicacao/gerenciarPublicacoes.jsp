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
		<sf:form name="frmGerenciarPublicacoes">
			<input type="hidden" name="msg" value="${mensagem}" />

			<center>
				<b><c:out value="Gerenciamento de Publicações" /></b> <br /> <br />
				<br />
			</center>

			<table class="tableBorder">
				<c:choose>
					<c:when test="${fn:length(publicacoes) gt 0}">
						<tr>
							<td><b><c:out value="Titulo" /></b></td>
							<td><b><c:out value="Texto" /></b></td>
							<td><b><c:out value="Politico" /></b></td>
							<td><b><c:out value="Partido" /></b></td>
							<td><b><c:out value="Status" /></b></td>
						</tr>
						<c:forEach var="lista" items="${publicacoes}">
							<tr>
								<td><c:out value="${lista.titulo}" /></td>
								<td><c:out value="${lista.texto}" /></td>
								<td><c:out value="${lista.politico.nome}" /></td>
								<td><c:out value="${lista.politico.partido.nome}" /></td>
								<td><c:out value="${lista.statusPublicacao.nome}" /></td>
								<td><a
									href="/portal/publicacao/gerenciarPublicacoes/aprovar/${lista.id}"><c:out
											value="Aprovar Publicacao" /></a></td>
								<td><a
									href="/portal/publicacao/gerenciarPublicacoes/reprovar/${lista.id}"><c:out
											value="Reprovar Publicacao" /></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td><c:out value="Não existem publicações cadastradas" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</sf:form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		if ($('[name=msg]').val() != '') {
			alert($('[name=msg]').val());
		}
	});
</script>

<br />
<br />
<br />
<br />
<br />
<br />	