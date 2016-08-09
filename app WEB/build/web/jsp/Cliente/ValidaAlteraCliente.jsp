<%-- 
    Document   : AlteraCliente
    Created on : 02/04/2016, 15:11:31
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Cliente cliente = new ClienteDAO().consultaClientebyID(request.getParameter("JBCliente"));

    String TEL = (cliente.getTelefone()) + "";
    String nomePcint = cliente.getNomePrinc();
    
    //new ClienteDAO().excluirClienteByID(request.getParameter("JBCliente"));
    String id = cliente.getId_cliente()+ "";
    
    String telefone = cliente.getTelefone() + "";
        String cpf = cliente.getCpf() + "";
        String inscricao_estadual = cliente.getInscricao_estadual() + "";
        String cnpj = cliente.getCnpj() + "";
        String nomePrinc = cliente.getNomePrinc() + "";
        String nomeSec = cliente.getNomeSec() + "";
%>

<!DOCTYPE html>
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
      
        <!--div class="container"/ -->
            <h1>Novos dados do cliente</h1>
            <form name="cadastraCliente" action="confirmaAtualizacaoCliente.jsp" method="POST">
                <table>
                    <input type = "alt" name = "JBCliente"  hidden="hidden" value = "<%=id%>" > 
                    <tr>
                        <td>Primeiro nome:</td>
                        <td><input type="text" name="nomePrinc" value="<%=nomePrinc%>"  pattern="[a-zA-Z]+"></td>
                    </tr>
                    <tr>
                        <td>Segundo nome:</td>
                        <td><input type="text" name="nomeSec" value="<%=nomeSec%>"  pattern="[a-zA-Z]+"></td>
                    </tr>
                    <tr>
                        <td>Telefone</td>
                        <td><input type="text" name="telefone" value="<%=telefone%>"  pattern="[0-9]+"> </td>
                    </tr>
                    <tr>
                        <td>CPF</td>
                        <td><input type="text" name="cpf" value="<%=cpf%>"  pattern="[0-9]+"></td>
                    </tr>
                    <tr>
                        <td>Inscrição Estadual</td>
                        <td><input type="text" name="inscricao_estadual"  pattern="[0-9]+" value="<%=inscricao_estadual%>"></td>
                    </tr>
                    <tr>
                        <td>CNPJ</td>
                        <td><input type="text" name="cnpj" value="<%=cnpj%>"  pattern="[0-9]+"></td>
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
