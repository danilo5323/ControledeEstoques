<%-- 
    Document   : validaDeletaProduto
    Created on : 07/04/2016, 12:22:19
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="dao" class= "br.com.projeto.jdbc.DAO.ProdutoDAO"/>

<!DOCTYPE html>

<%
   
       ProdutoDAO f = new ProdutoDAO();
   //Produto Produto = f.consultaProduto(request.getParameter("tel"));
   List<Produto> Produtos = f.consultaProdutoLista(request.getParameter("nome"));
   //List<Produto> Produtos = new ProdutoDAO().consultaProdutoTel(request.getParameter("tel"));
    
    String nome = request.getParameter("nome"),
           nome_qualificador = request.getParameter("nome_qualificador"),
           unidade_medida = request.getParameter("unidade_medida"),
           cod_barras = request.getParameter("cod_barras");
%>

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
     
        <form name="ConfirmAlteraProduto" action="validaAlteraProduto.jsp" method="POST">
            
            <h1> Produtos encontrados </h1>
            <select name="JBProduto">
                <% for (Produto prd : Produtos) { %>
                    <option value = "<%=prd.getId_produto()%>" > <%=prd.getNome()%> <%=prd.getNome_qualificador()%>  </option>
                <% } %>
            </select>
                
           nome padrão: 
           <input type="text" name="tel" value="<%=nome%>">  
                    
           Excluir?        
           <input type="submit"  class="btn btn-default" role="button" name="Sim" value="Sim"> 
           <input type="submit"  class="btn btn-default" role="button" name="Nao" value="Não" > <br>
           
        </form>
    </div>
  </div>
</div>
	<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
