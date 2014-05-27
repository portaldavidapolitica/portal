<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<center>
	<b><c:out value="Listagem de Publicacoes" /></b>
</center>

<br />

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmListaPublicacao">

			<input type="hidden" name="msg" value="${mensagem}" />
			<input type="hidden" name="polit" value="${politico}" />
			
			<center>
				<table class="tableBorder">
					<tr style="background: white">
						<td><b><c:out value="Nome do Politico" /></b></td>
						<td><b><c:out value="Partido" /></b></td>
					</tr>
					<tr style="background: white">
						<td><input name="nomePolitico" value="${nomePolitico}" style="width: 200px" /></td>
						<td><select name="id_partido" style="width: 85px">
								<option value="" selected><c:out value="Selecione" /></option>
								<c:forEach var="item" items="${partidos}">
									<option value="${item.id}"
										<c:if test="${item.id eq id_partido}">selected</c:if>>
										<c:out value="${item.sigla}" />
									</option>
								</c:forEach>
						</select></td>
						<td><a href="#" onclick="javascript:pesquisar();" ><c:out value="Pesquisar Politico" /></a></td>
					</tr>

					<c:if test="${!empty politico}">
						<tr style="background: white">
							<td><b><c:out value="Politico" /></b></td>
							<td><b><c:out value="Partido" /></b></td>
						</tr>
						<c:forEach var="listaPolitico" items="${politico}">
							<tr style="background: white">
								<td><c:out value="${listaPolitico.nome}" /></td>
								<td><c:out value="${listaPolitico.partido.sigla}" /></td>
								<td><a href="/portal/publicacao/visualizar/${listaPolitico.partido.id}"><c:out value="Visualizar Publicações" /></a></td>
								<sec:authorize ifAnyGranted="ROLE_MEMBRO,ROLE_ADMIN">
									<td><a href="/portal/publicacao/publicar/${listaPolitico.nome},${listaPolitico.partido.id}"><c:out value="Fazer Publicacao" /></a></td>
								</sec:authorize>
							</tr>
						</c:forEach>
					</c:if>
				</table>
			</center>
		 </sf:form>
	</div>
</div>

<script type="text/javascript" src="<c:url value="/recursos/javascripts/publicacao_functions.js"/>">
	
</script>
