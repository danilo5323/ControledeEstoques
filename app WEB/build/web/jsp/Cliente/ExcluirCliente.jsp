<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <!--div class="container"/ -->
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/CadastraCliente.jsp">Cadastrar</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/ExcluirCliente.jsp">Excluir</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/AlterarCliente.jsp">Alterar</a></p>
                </div>
                <div class="col-sm-8 text-left"> 
                    <h1>Excluir cliente</h1>
                    <form name="deletaCliente" action="validaExcluirCliente.jsp" method="POST">
                        <table>
                            <tr>
                                <td>Buscar cliente pelo telefone cadastrado    </td>
                                <td><input type="text" name="tel" value="" ></td>
                            </tr>
                        </table>  
                        <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Enviar"> <br>
                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>

