<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:useBean id="usrLogado" class="br.com.itexto.springforum.controladores.UsuarioController" />

<div class="row">
	<div class="three columns">
		<h1>
			<a href="<c:url value="/"/>"><img src="<c:url value="/recursos/images/logotipo.png"/>" /></a>
		</h1>
	</div>
	<div class="five columns right">
		
		<sec:authorize access="isAnonymous()">
		
		<form style="margin-top: 1.0em;" action="<c:url value="/j_spring_security_check"/>" method="post">
			<div class="row">
				<input type="text" name="j_username" placeholder="Usu&aacute;rio" class="three columns right"/>
				<input type="password" name="j_password" placeholder="Senha" class="three columns right"/>
				<input type="submit" value="Entrar" class="tiny button success" class="three columns right"/>&nbsp;
				<a href="<c:url value='registro'/>" class="tiny button success">Registre-se</a>
			</div>
			
		</form>
		
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			Bem vindo(a) <c:out value="${usrLogado.nomeUsuarioLogado}" /> - <a href="<c:url value="/j_spring_security_logout"/>">Sair</a>
		</sec:authorize>
	</div>
</div>
<div style="background: #48762A;">
	<div class="row">
		<div class="twelve columns">
			<ul id="side-nav">
				<li><a href="/portal">Home</a></li>
				<sec:authorize access="hasAnyRole('ROLE_MODERADOR,ROLE_ADMIN')">
					<li><a href="#">Gerenciamento</a>
						<ul>
							<li><a href="/portal/publicacao/gerenciarPublicacoes">Publicacoes</a></li>
							<li><a href="/portal/politico/gerenciarPoliticos">Politicos</a></li>
							<li><a href="/portal/partido/gerenciarPartidos">Partidos</a></li>
							<sec:authorize access="hasRole('ROLE_ADMIN')">
								<li><a href="/portal/gerenciar/usuarios">Usuarios</a></li>
							</sec:authorize>
						</ul>
					</li>
				</sec:authorize>

				<sec:authorize access="hasRole('ROLE_MEMBRO')">
					<li><a href="#">Cadastros</a>
						<ul>
							<li><a href="/portal/cadastro/partido">Partido</a></li>
							<li><a href="/portal/cadastro/politico">Politico</a></li>
						</ul>
					</li>
				</sec:authorize>

				<li><a href="/portal/publicacao">Publicação</a>
				<li>
			</ul>
		</div>
	</div>
</div>
<br/><br/><br/>