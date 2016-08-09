<%-- 
    Document   : ConfirmaExcluirProduto
    Created on : 07/04/2016, 13:14:42
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="java.util.List"%>

<%
    //Produto Produto = new ProdutoDAO().consultaProdutobyID(request.getParameter("JBProduto"));
    String strID = request.getParameter("JBProduto");
    Long id = Long.parseLong(strID);
    
    
    Produto produto = new ProdutoDAO().consultaProdutoByID(id);
    /***
    String nome = request.getParameter("nome"),
           nome_qualificador = request.getParameter("nome_qualificador"),
           unidade_medida = request.getParameter("unidade_medida"),
           cod_barras = request.getParameter("cod_barras");
    */
    String nome = produto.getNome(),
           nome_qualificador = produto.getNome_qualificador(),
           unidade_medida = produto.getUnidade_medida(),
           cod_barras = produto.getCod_barras();
    
    
    new ProdutoDAO().excluirProdutoByID(id);
    
    
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
<body>
 <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/cadastraProduto.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/excluirProduto.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/alterarProduto.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
      
	<form name="return" action="index.html" method="POST">
      
        <h3>Nome do produto: <%=nome%></h3> 
        <h3>Nome qualificador: <%=nome_qualificador%></h3> 
        <h3>Unidade de medida : <%=unidade_medida%></h3> 
        <h3>codigo de barras: <%=cod_barras%></h3> 
        
        <h1> Excluido com sucesso</h1>
        <a  class="btn btn-default" role="button" href ="/ControledeEstoques/index.html"> Retornar ao menu </a>
				
   
</form>
    </div>
  </div>
</div>

<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
 </body>
 </html>