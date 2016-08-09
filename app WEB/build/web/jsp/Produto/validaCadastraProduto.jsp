<%-- 
    Document   : ValidaInserirProduto
    Created on : 04/04/2016, 16:47:13
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="com.sun.javafx.scene.layout.region.Margins.Converter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>

<%
    String nome = request.getParameter("nome"),
            nome_qualificador = request.getParameter("nome_qualificador"),
            unidade_medida = request.getParameter("unidade_medida"),
            cod_barras = request.getParameter("cod_barras"),
            marca = request.getParameter("marca");

    Produto produto = new Produto(
            nome,
            nome_qualificador,
            unidade_medida,
            cod_barras, 
            marca);

    new ProdutoDAO().cadastraProduto(produto);
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


                    <h3>Nome do produto: <%=nome%></h3> 
                    <h3>Nome qualificador: <%=nome_qualificador%></h3> 
                    <h3>Unidade de medida : <%=unidade_medida%></h3> 
                    <h3>codigo de barras: <%=cod_barras%></h3> 
                    <h3>marca: <%=marca%></h3> 
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>

</html>


