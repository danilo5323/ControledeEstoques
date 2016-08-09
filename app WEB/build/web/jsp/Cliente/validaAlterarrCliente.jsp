<%-- 
    Document   : validaDeletaCliente
    Created on : 07/04/2016, 12:22:19
    Author     : Danilo
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente" %>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO" %>
<jsp:useBean id="dao" class= "br.com.projeto.jdbc.DAO.ClienteDAO"/>

<!DOCTYPE html>

<%
    
   
   List<Cliente> clientes = new ClienteDAO().consultaClienteTel(request.getParameter("tel"));
    
    String TEL = request.getParameter("tel"); 
    String telefone = "";
        String cpf = "";
        String inscricao_estadual = "";
        String cnpj = "";
        String nomePrinc = "";
        String nomeSec = "";
%>

<html>
  <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
		<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/CadastraCliente.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/ExcluirCliente.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/AlterarCliente.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
      
        <form name="ConfirmExcluirCliente" action="ValidaAlteraCliente.jsp" method="POST">
            <tbody>
            <select name="JBCliente">
                <% for (Cliente cli : clientes) { %>
                    <option value = <%=cli.getId_cliente()%> > <%=cli.getNomePrinc()%> <%=cli.getNomeSec()%>  </option>
                <% } %>
            </select>
                
           TEL: 
           <input type="text" name="tel" value="<%=TEL%>">  </input>                    
                    
           Excluir?        
           <input type="submit"  class="btn btn-default" role="button" name="Sim" value="Sim"> 
           <input type="submit"  class="btn btn-default" role="button" name="Nao" value="Não" > <br>
           </tbody>
        </form>
    </div>
  </div>
</div>
		<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
