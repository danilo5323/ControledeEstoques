<%-- 
    Document   : finalizaVenda
    Created on : 13/04/2016, 08:02:18
    Author     : danilo2
--%>

<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%

    ProdutoDAO f = new ProdutoDAO();
//   Produto produto = f.selecionaProduto(request.getParameter("codBarras"));
    //List<Produto> Produtos = f.consultaProdutoLista(request.getParameter("nome"));
    //List<Produto> Produtos = new ProdutoDAO().consultaProdutoTel(request.getParameter("tel"));

    String nome = request.getParameter("nome"),
            nome_qualificador = request.getParameter("nome_qualificador"),
            unidade_medida = request.getParameter("unidade_medida"),
            cod_barras = request.getParameter("cod_barras");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- este faz o update  no banco e encerra retorna para a tela inicial -->
<!DOCTYPE html>
<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <body>
        <div class="col-sm-2 sidenav">
            <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/index.html">Sair</a></p>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">

                </div>
                <div class="col-sm-8 text-left"> 

                    <h1>Venda finalizada!</h1>
                    <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href="\index.html">  Retornar ao indice </a>
                    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
                </div>
            </div>
        </div>
    </body>
</html>
