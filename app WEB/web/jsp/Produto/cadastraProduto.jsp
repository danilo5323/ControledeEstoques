<%-- 
    Document   : CadastraProduto
    Created on : 21/03/2016, 22:48:46
    Author     : Danilo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
<form name="cadastraProduto" action="validaCadastraProduto.jsp" method="POST">
    <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
    <div class="container-fluid text-center">    
        <div class="row content">
            <div class="col-sm-2 sidenav">
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/cadastraProduto.jsp">Cadastrar</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/excluirProduto.jsp">Excluir</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/alterarProduto.jsp">Alterar</a></p>
            </div>
            <div class="col-sm-8 text-left"> 

                <h1>Cadastrar produto</h1>

                <table>
                    <tr>
                        <td>Nome padrão</td>
                        <td><input type="text" name="nome" value="" pattern="[a-zA-z ]+"></td>
                    </tr>
                    <tr>
                        <td>Nome qualificador :</td>
                        <td><input type="text" name="nome_qualificador" value="" pattern="[a-zA-z ]+"></td>
                    </tr>
                    <tr>
                        <td>Unidade de medida</td>
                        <td><input type="text" name="unidade_medida" value="" pattern="[a-zA-z ]+" maxlenght = "5"></td>
                    </tr>
                    <tr>
                        <td>Codigo de barras </td>
                        <td><input type="text" name="cod_barras" value="" pattern="[0-9]+"></td>
                    </tr>
                    <tr>
                        <td>Marca </td>
                        <td><input type="text" name="marca" value="" pattern="[a-zA-z]+"></td>
                    </tr>


                </table>
                <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Enviar"> <br>
            </div>
        </div>
    </div>
    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
</form>
<!--/div -->


