<%-- 
    Document   : ConfirmaExcluirCliente
    Created on : 07/04/2016, 13:14:42
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page import="java.util.List"%>

<%
    Cliente cliente = new ClienteDAO().consultaClientebyID(request.getParameter("JBCliente"));

    String TEL = (cliente.getTelefone()) + "";
    String nomePcint = cliente.getNomePrinc();

    new ClienteDAO().excluirClienteByID(request.getParameter("JBCliente"));

    String telefone = cliente.getTelefone() + "";
    String cpf = cliente.getCpf() + "";
    String inscricao_estadual = cliente.getInscricao_estadual() + "";
    String cnpj = cliente.getCnpj() + "";
    String nomePrinc = cliente.getNomePrinc() + "";
    String nomeSec = cliente.getNomeSec() + "";
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
<form name="return" action="index.html" method="POST">

    <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>

    <div class="container-fluid text-center">    
        <div class="row content">
            <div class="col-sm-2 sidenav">
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/CadastraCliente.jsp">Cadastrar</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/ExcluirCliente.jsp">Excluir</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/AlterarCliente.jsp">Alterar</a></p>
            </div>
            <div class="col-sm-8 text-left"> 
                <h1>Nome : <%=nomePrinc%></h3>
                    <h3>Sobrenome : <%=nomeSec%></h3> 
                    <h3>Telefone : <%=telefone%></h3> 
                    <h3>CPF : <%=cpf%></h3> 
                    <h3>cnpj : <%=cnpj%></h3> 
                    <h3>inscricao_estadual : <%=inscricao_estadual%></h3>

                    <h3> Excluido com sucesso</h3>
            </div>
        </div>
    </div>


    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>

</form>
