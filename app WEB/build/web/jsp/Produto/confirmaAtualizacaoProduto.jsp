<%-- 
    Document   : confirmaAtualizacaoProduto
    Created on : 10/04/2016, 12:00:25
    Author     : danilo2
--%>

<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="java.util.List"%>

<%
    String nome = request.getParameter("nome"),
           nome_qualificador = request.getParameter("nome_qualificador"),
           unidade_medida = request.getParameter("unidade_medida"),
           cod_barras = request.getParameter("cod_barras");
    
    String ID = request.getParameter("JBProduto");
    Long id = Long.parseInt(ID);
   
    Produto ProdutoAtualizar = new Produto( 
            nome,
            nome_qualificador,
            unidade_medida,
            cod_barras);
    
    
    //new ProdutoDAO().alteraProdutoByID(Produto.getId_Produto(),  ProdutoAtualizar );
    
    new ProdutoDAO().alteraProdutoByID((id),  ProdutoAtualizar );
    
    
   
    
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form name="return" action="index.html" method="POST">
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <body>
	<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/cadastraProduto.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/excluirProduto.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/alterarProduto.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
      
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <h1>Nome do produto: <%=nome%></h1> <br>
        <h1>Nome qualificador: <%=nome_qualificador%></h1> <br>
        <h1>Unidade de medida : <%=unidade_medida%></h1> <br>
        <h1>codigo de barras: <%=cod_barras%></h1> <br>
        
        
        <h1> Atualizad com sucesso</h1>
        <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href = "/ControledeEstoques/index.html" >  retornar </a>
    </div>
  </div>
</div>
		
				<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</form>
