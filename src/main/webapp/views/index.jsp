<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					</ul></li></sec:authorize>

                <sec:authorize access="hasRole('ROLE_MEMBRO')">
				<li><a href="#">Cadatros</a>
					<ul>
						<li><a href="<c:url value="cadastro/partido"/>"><c:out
									value="Cadastro Partido" /></a></li>
						<li><a href="cadastro/politico"><c:out
									value="Cadastro Politico" /></a></li>
					</ul></li></sec:authorize>

				<li><a href="#">Listagem</a>
					<ul>
						<li><a href="cadastro/politico"><c:out
									value="Listagem Politicos" /></a></li>
						<li><a href="<c:url value="cadastro/partido"/>"><c:out
									value="Listagem Partidos" /></a></li>
						<li><a href="<c:url value="publicacao"/>"><c:out
									value="Listagem de Publicacoes" /></a></li>
					</ul>
				<li>
			</ul>
		</div>
	</div>
</div>

<div class="row">
	<div class="eight columns" style="position: inherit; z-index: -1;">
		<h5>Portal da vida pol�tica</h5>
		<p>O portal da vida pol�tica � um espa�o onde os eleitores ter�o a
			oportunidade de encontrar um conte�do centralizado sobre determinado
			pol�co.</p>
	</div>
</div>
