<%-- 
    Document   : ConfirmaExcluirCliente
    Created on : 07/04/2016, 13:14:42
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.FornecedorDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Fornecedor"%>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page import="java.util.List"%>

<%
    //Cliente cliente = new ClienteDAO().consultaClientebyID(request.getParameter("JBCliente"));
    String strID = request.getParameter("JBFornecedor");
    Long id = Long.parseLong(strID);
    Fornecedor fornecedor = new FornecedorDAO().consultaFornecedorByID(strID);

    String TEL = fornecedor.getForn_tel() + "";

    String forn_nome_empresa = fornecedor.getForn_nome_empresa() + "",
            forn_nome_fantasia = fornecedor.getForn_nome_fantasia() + "",
            forn_email = fornecedor.getForn_email() + "";
    Long forn_cnpj = fornecedor.getForn_cnpj(),
            forn_ie = fornecedor.getForn_ie(),
            forn_tel = fornecedor.getForn_tel();

    new FornecedorDAO().excluirFornecedorByID(id);


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<form name="return" action="index.html" method="POST">
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>

    <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
    <div class="container-fluid text-center">    
        <div class="row content">
            <div class="col-sm-2 sidenav">
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/cadastraFornecedor.jsp">Cadastrar</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/excluirFornecedor.jsp">Excluir</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/alterarFornecedor.jsp">Alterar</a></p>
            </div>
            <div class="col-sm-8 text-left"> 

                <h3>Nome da empresa : <%=forn_nome_empresa%></h3> 
                <h3>Nome fantasia : <%=forn_nome_fantasia%></h3>
                <h3>E-mail : <%=forn_email%></h3> 
                <h3>Telefone : <%=forn_tel%></h3>
                <h3>cnpj : <%=forn_cnpj%></h3> 
                <h3>inscricao_estadual : <%=forn_ie%></h3> 

                <h3> Excluido com sucesso</h3>
                
            </div>
        </div>
    </div>

    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>

</form>
