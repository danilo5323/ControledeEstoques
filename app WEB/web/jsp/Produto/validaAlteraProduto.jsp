<%-- 
    Document   : AlteraProduto
    Created on : 02/04/2016, 15:11:31
    Author     : Danilo
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    String ID = request.getParameter("JBProduto");
    ProdutoDAO f = new ProdutoDAO();
   
   Long id = Long.parseInt(ID);
   Produto produto = f.consultaProdutoByID(id);
   //List<Produto> Produtoes = f.consultaProdutoLista(request.getParameter("tel"));
   //List<Produto> Produtos = new ProdutoDAO().consultaProdutoTel(request.getParameter("tel"));
    
    String nome = produto.getNome(),
           nome_qualificador = produto.getNome_qualificador(),
           unidade_medida = produto.getUnidade_medida(),
           cod_barras = produto.getCod_barras();
    
  
%>

<!DOCTYPE html>
<html>
    <!--%@include file="../../inc/materalizeWeb.inc" %-->
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
     
            <h1>Cadastrar Produto</h1>
            <form name="cadastraProduto" action="confirmaAtualizacaoProduto.jsp" method="POST">
                <table>
                    <input type = "alt" name = "JBProduto" value = <%=ID%> > 
                    <tr>
                        <td>Nome padção</td>
                        <td><input type="text" name="nome" value="<%=nome%>" pattern="[a-zA-z]+"></td>
                    </tr>
                    <tr>
                        <td>Nome modificador</td>
                        <td><input type="text" name="nome_qualificador" value="<%=nome_qualificador%>" pattern="[a-zA-z]+"></td>
                    </tr>
                    <tr>
                        <td>unidade de medida</td>
                        <td><input type="text" name="unidade_medida" value="<%=unidade_medida%>" pattern="[a-zA-z]5"></td>
                    </tr>
                    <tr>
                        <td>codigo de barras</td>
                        <td><input type="text" name="cod_barras" value="<%=cod_barras%>" pattern="[0-9]+"></td>
                    </tr>
                   

                </table>  
                <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Atualizar"> <br>
            </form>
    </div>
  </div>
</div>
			<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
        <!--/div -->
    </body>
</html>
