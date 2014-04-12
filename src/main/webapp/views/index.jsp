<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<div style="background: #48762A;">
		<div class="row">
			<div class="twelve columns">
				<img src="recursos/images/brasil.png" />
			</div>
		</div>
	</div>
	
	
	
	<div class="row">
		<div class="two columns">
			<h5>Assuntos</h5>
			<ul class="side-nav">
			<c:forEach items="${assuntos}" var="assunto">
				<li><a href="assunto/${assunto.id}">${assunto.nome}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div class="eight columns">
			<h5>Portal da vida política</h5>
			<p>O portal da vida política é um espaço onde os eleitores terão a oportunidade de encontrar um conteúdo centralizado sobre determinado políco.</p>
		</div>
		<div class="two columns">
			<h5>Último membros</h5>
			<ul class="side-nav">
			<c:forEach items="${usuarios}" var="usuario">
				<li><a href="usuario/show/${usuario.id}">${usuario.nome}</a></li>
			</c:forEach>
			</ul>
		</div>
		
	</div>
	
