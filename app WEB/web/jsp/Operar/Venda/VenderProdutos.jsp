<%-- 
    Document   : VenderProdutos
    Created on : 21/03/2016, 22:54:54
    Author     : Danilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <body><%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="selecionaProduto.jsp"> Vender </a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuPrincipal.jsp"> Cancelar </a></p>
                </div>
                <div class="col-sm-8 text-left"> 
                    <!--
                      <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href="selecionaProduto.jsp"> Vender produtos </a>
                      <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href="/ControledeEstoques/index.html"> finalizar venda </a>
                    -->
                </div>
            </div>

        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
