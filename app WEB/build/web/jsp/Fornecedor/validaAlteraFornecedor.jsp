<%-- 
    Document   : AlteraCliente
    Created on : 02/04/2016, 15:11:31
    Author     : Danilo
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.projeto.jdbc.Beans.Fornecedor"%>
<%@page import="br.com.projeto.jdbc.DAO.FornecedorDAO"%>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    String ID = request.getParameter("JBFornecedor");
    FornecedorDAO f = new FornecedorDAO();
   Fornecedor fornecedor = f.consultaFornecedorByID(ID);
   //List<Fornecedor> fornecedores = f.consultaFornecedorLista(request.getParameter("tel"));
   //List<Cliente> clientes = new ClienteDAO().consultaClienteTel(request.getParameter("tel"));
    
    String TEL = request.getParameter("tel"); 
    String  forn_nome_empresa = fornecedor.getForn_nome_empresa() + "",
            forn_nome_fantasia = fornecedor.getForn_nome_fantasia() + "",
            forn_email = fornecedor.getForn_email() + "";
    Long    forn_cnpj = fornecedor.getForn_cnpj() ,
            forn_ie = fornecedor.getForn_ie(),
            forn_tel  = fornecedor.getForn_tel();
    
  
%>

<!DOCTYPE html>
<html>
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
<body>
<%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-2 sidenav">
        <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/cadastraFornecedor.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/excluirFornecedor.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/alterarFornecedor.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
      
            <form name="cadastraCliente" action="confirmaAtualizacaoFornecedor.jsp" method="POST">

                <h1> Alterar fornecedor</h1>
                <table>
                    <input type = "alt" name = "JBCliente" value = "<%=ID%>" hidden ="hidden" > 
                    <tr>
                        <td>Nome da empresa</td>
                        <td><input type="text" name="forn_nome_empresa" value="<%=forn_nome_empresa%>" pattern="[a-zA-z]+"></td>
                    </tr>
                    <tr>
                        <td>Nome fantasia</td>
                        <td><input type="text" name="forn_nome_fantasia" value="<%=forn_nome_fantasia %>" pattern="[a-zA-z]+" ></td>
                    </tr>
                    <tr>
                        <td>Telefone</td>
                        <td><input type="text" name="forn_tel" value="<%=forn_tel  %>" pattern="[0-9]+"></td>
                    </tr>
                    <tr>
                        <td>Email do fornecedor </td>
                        <td><input type="text" name="forn_email" value="<%=forn_email %> "></td>
                    </tr>
                    <tr>
                        <td>CNPJ</td>
                        <td><input type="text" name="forn_cnpj" value="<%=forn_cnpj %>" pattern="[0-9]+"></td>
                    </tr>
                    <tr>
                        <td>Inscrição Estadual</td>
                        <td><input type="text" name="forn_ie" value="<%=forn_ie %>" pattern="[0-9]+"></td>
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
