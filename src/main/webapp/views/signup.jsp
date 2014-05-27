<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div style="background: #48762A;">
	<div class="row">
		<div class="twelve columns">

			<ul id="side-nav">

				<!--<li><a href="<c:url value="/"/>"><c:out value="Home" /></a></li>-->
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

<br /> <br />

<div class="row">
	<div class="four columns" style="position: inherit; z-index: -1;">
		<h4>Bem vindo(a) ao Portal da vida política</h4>
		<p>Esperamos que você encontre informações úteis e que também
			compartilhe informações com os outros usuários.</p>
	</div>
	<div class="eight columns">
		<sf:form modelAttribute="usuario" action="executarRegistro"
			enctype="multipart/form-data">
			<input type="hidden" name="msg" value="${mensagem}" />
			<h1>${mensagem}</h1>
			<label for="nome">Nome:<sf:errors path="nome" cssClass="erro" /></label>
			<sf:input path="nome" class="four" />

			<label for="email">Email:<sf:errors path="email"
					cssClass="erro" /></label>
			<sf:input path="email" class="four" />


			<label for="login">Nome do usuário (login):<sf:errors
					path="login" cssClass="erro" /></label>
			<sf:input path="login" class="three" />
			<label for="senha">Senha:</label>
			<sf:password path="senha" class="three" />
			<label for="avatar">Avatar:</label>
			<input type="file" name="avatar" />


			<input type="submit" value="Faça parte!"
				class="tiny button success" />

			<br />
			<br />

			<a href="../portal">Voltar</a>
		</sf:form>
	</div>
</div>