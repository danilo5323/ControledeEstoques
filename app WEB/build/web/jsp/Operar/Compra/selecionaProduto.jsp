<%-- 
    Document   : selecionaProduto
    Created on : 13/04/2016, 10:08:10
    Author     : danilo2
--%>


<%

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
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuPrincipal.jsp"> Cancelar </a></p>
                </div>
                <div class="col-sm-8 text-left"> 


                    <h1>Buscar produto</h1>


                    <form name="selecionaProduto" action="ConfigurarCompraProduto.jsp" method="POST">
                        <table>
                            <tr>
                                <td>Buscar produto pelo codigo de barras</td>
                                <td><input type="text" name="codBarras" value="" pattern="[0-9]+"></td>
                                <td>Quantidade</td>
                                <td><input type="text" name="quantidade" value="" pattern="[0-9]+"></td>


                            </tr>

                        </table>  


                        <br><br><br>

                        <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Adicionar"> <br>
                    </form>
                    <!--/div -->
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>