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
								href="<c:url value="../publicacao/gerenciarPublicacoes"/>"><c:out
										value="Gerenciar Publicacoes" /></a></li>
							<li><a
								href="<c:url value="../politico/gerenciarPoliticos"/>"><c:out
										value="Gerenciar Politicos" /></a></li>
							<li><a href="<c:url value="../partido/gerenciarPartidos"/>"><c:out
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
		</div>
	</div>
</div>

<br />
<br />
<br />

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmGerenciarPoliticos">
			<input type="hidden" name="msg" value="${mensagem}" />

			<center>
				<b><c:out value="Gerenciamento de Politicos" /></b> <br /> <br />
				<br />
			</center>

			<center>
				<table class="tableBorder">
					<c:choose>
						<c:when test="${fn:length(politicos) gt 0}">
							<tr>
								<td><b><c:out value="Nome" /></b></td>
								<td><b><c:out value="Partido" /></b></td>
								<td><b><c:out value="Status" /></b></td>
							</tr>
							<c:forEach var="lista" items="${politicos}">
								<tr>
									<td style="background: white"><c:out value="${lista.nome}" /></td>
									<td style="background: white"><c:out
											value="${lista.partido.nome}" /></td>
									<td style="background: white"><c:out
											value="${lista.statusPolitico.nome}" /></td>
									<td style="background: white"><a
										href="/portal/politico/gerenciarPoliticos/aprovar/${lista.id}"><c:out
												value="Aprovar Politico" /></a></td>
									<td style="background: white"><a
										href="/portal/politico/gerenciarPoliticos/reprovar/${lista.id}"><c:out
												value="Reprovar Politico" /></a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td><c:out value="Não existem politicos cadastrados" /></td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</center>
		</sf:form>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		if ($('[name=msg]').val() != '') {
			alert($('[name=msg]').val());
			$('[name=frmGerenciarPoliticos]').attr("action",
					"../../../politico/gerenciarPoliticos");
			$('[name=frmGerenciarPoliticos]').submit();
		}
	});
</script>

<br />
<br />
<br />
<br />
<br />
<br />
