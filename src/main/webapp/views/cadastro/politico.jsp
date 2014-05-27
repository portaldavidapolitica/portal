<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<center>
	<b><c:out value="Cadastro de Politicos" /></b>
</center>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPolitico" enctype="multipart/form-data">

			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPolitico" value="${idPolitico}" />
			<input type="hidden" name="msg" value="${mensagem}" />
			<c:choose>
				<c:when test="${fn:length(partidos) gt 0}">
					<table class="tableBorder">
						<sec:authorize access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
							<tr style="background: white">
								<td><b><c:out value="Nome: " /></b></td>
								<td><input name="nome" value="${nome}" style="width: 200px" /></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Partido: " /></b></td>
								<td><select name="id_partido" style="width: 85px">
										<option value="" selected><c:out value="Selecione" /></option>
										<c:forEach var="item" items="${partidos}">
											<option value="${item.id}"
												<c:if test="${item.id eq idPartido}">selected</c:if>>
												<c:out value="${item.sigla}" />
											</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Avatar: " /></b></td>
								<td><input type="file" name="avatar" /></td>
							</tr>
							<tr style="background: white">
								<td><a href="#" onclick="javascript:valida();" class="tiny button success"><c:out value="Cadastrar" /></a></td>
							</tr>
						</sec:authorize>
					</table>
					<hr size="2px">
					<c:if test="${!empty politicos}">
						<center>
						<table class="tableBorder">
							<tr>
								<td style="text-align:center" colspan="4"><b><c:out value="Politicos Cadastrados" /></b></td>
							</tr>
							<tr style="background: white">
								<td><b><c:out value="Nome" /></b></td>
								<td><b><c:out value="Partido" /></b></td>
							</tr>
							
							<c:forEach var="lista" items="${politicos}">
								<tr style="background: white">
									<td><c:out value="${lista.nome}" /></td>
									<td><c:out value="${lista.partido.sigla}" /></td>
									<sec:authorize access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
										<td><a href="/portal/cadastro/politico/editar/${lista.id}"><c:out value="Editar" /></a></td>
										<td><a href="/portal/cadastro/politico/excluir/${lista.id}"><c:out value="Excluir" /></a></td>
									</sec:authorize>
								</tr>
							</c:forEach>	
						</table>
						</center>
					</c:if>
				</c:when>
				<c:otherwise>
					<table>
						<tr>
							<td><c:out value="Não existem partidos cadastrados, para cadastrar um politico é preciso primeiro cadastrar um partido" /></td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>
			<br />
			<br />
		</sf:form>
	</div>
</div>

<script type="text/javascript"
	src="<c:url value="/recursos/javascripts/politico_functions.js"/>">
	
</script>
