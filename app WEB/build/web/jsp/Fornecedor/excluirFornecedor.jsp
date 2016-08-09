<%-- 
    Document   : ExcluirCliente
    Created on : 22/03/2016, 23:19:44
    Author     : Danilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
    <body>
        <!--div class="container"/ -->
    <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>   
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/cadastraFornecedor.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/excluirFornecedor.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/alterarFornecedor.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
      <form name="deletaCliente" action="validaExcluirFornecedor.jsp" method="POST">
    
                <h1>Excluir fornecedor</h1>
                <table>
                    <tr>
                        <td>Buscar fornecedor pelo telefone cadastrado</td>
                        <td><input type="text" name="tel" value="" ></td>
                    </tr>
                </table>  
                <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Enviar"> <br>
            </form>
            

           
    </div>
  </div>
</div>	
            
<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>            
        <!--/div -->
    </body>
</html>

