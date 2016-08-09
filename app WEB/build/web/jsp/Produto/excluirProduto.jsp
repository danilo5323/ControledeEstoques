<%-- 
    Document   : ExcluirProduto
    Created on : 22/03/2016, 23:19:44
    Author     : Danilo
--%>

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
       <h1>Excluir produto</h1>
            <form name="deletaProduto" action="validaExcluirProduto.jsp" method="POST">
                <table>
                    <tr>
                        <td>Buscar Produto pelo nome principal: </td>
                        <td><input type="text" name="nome" value="" ></td>
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

