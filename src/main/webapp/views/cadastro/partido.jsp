<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<center>
	<b><c:out value="Cadastro de Partidos" /></b>
</center>

<div class="row">
	<div class="twelve columns" style="position: inherit; z-index: -1;">
		<sf:form name="frmPartido" enctype="multipart/form-data">
			<input type="hidden" name="acao" value="${acao}" />
			<input type="hidden" name="idPartido" value="${partido.id}" />
			<input type="hidden" name="msg" value="${mensagem}" />

			<sec:authorize access="hasAnyRole('ROLE_MEMBRO','ROLE_MODERADOR','ROLE_ADMIN')">
				<table class="tableBorder">
					<tr style="background: white">
						<td><b><c:out value="Nome: " /></b></td>
						<td><input name="nome" value="${partido.nome}" class="four" /></td>
					</tr>

					<tr style="background: white">
						<td><b><c:out value="Sigla: " /></b></td>
						<td><input name="sigla" value="${partido.sigla}" class="four" /></td>
					</tr>

					<tr style="background: white">
						<td><b><c:out value="Descricao: " /></b></td>
						<td><textarea name="descricao" rows="5" cols="100" class="three"><c:out value="${partido.descricao}" /></textarea></td>
					</tr>

					<tr style="background: white">
						<td><b><c:out value="Avatar: " /></b></td>
						<td><input type="file" name="avatar" /></td>
					</tr>

					<tr style="background: white" align="center">
						<td><a href="#" onclick="javascript:valida();" class="tiny button success"><c:out value="Cadastrar" /></a></td>
					</tr>
				</table>
			</sec:authorize>
			<hr size="2px">
			<c:if test="${!empty partidos}">
					<center>
						<table class="tableBorder">
							<tr>
								<td style="text-align:center" colspan="4"><b><c:out value="Partidos Cadastrados" /></b></td>
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
										<td><a href="partido/editar/${lista.id}"><c:out value="Editar" /></a></td>
										<td><a href="partido/excluir/${lista.id}"><c:out value="Excluir" /></a></td>
									</sec:authorize>
								</tr>
							</c:forEach>
						</table>
					</center>
			</c:if>
		</sf:form>
	</div>
</div>
<script type="text/javascript"
	src="<c:url value="/recursos/javascripts/partido_functions.js"/>">
	
</script>