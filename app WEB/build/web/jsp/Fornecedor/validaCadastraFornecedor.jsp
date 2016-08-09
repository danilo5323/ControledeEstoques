<%-- 
    Document   : ValidaInserirCliente
    Created on : 04/04/2016, 16:47:13
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.FornecedorDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Fornecedor"%>
<%@page import="com.sun.javafx.scene.layout.region.Margins.Converter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente" %>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO" %>

<!DOCTYPE html>

<%

    String forn_nome_empresa = request.getParameter("forn_nome_empresa"),
            forn_nome_fantasia = request.getParameter("forn_nome_fantasia"),
            forn_email = request.getParameter("forn_email");
    String str_Forn_ie = request.getParameter("forn_ie");
    Long forn_ie = Long.parseLong(str_Forn_ie);

    String str_Forn_CNPJ = request.getParameter("forn_cnpj");
    Long forn_cnpj = Long.parseLong(str_Forn_CNPJ);

    String str_forn_tel = request.getParameter("forn_tel");
    Long forn_tel = Long.parseLong(str_forn_tel);

    Fornecedor fornecedor = new Fornecedor(
            forn_nome_empresa,
            forn_nome_fantasia,
            forn_email,
            forn_cnpj,
            forn_ie,
            forn_tel);

    new FornecedorDAO().cadastraFornecedor(fornecedor);
%>

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


                    <h3>Nome Empresa : <%=forn_nome_empresa%></h3> 
                    <h3>Nome Fantasia : <%=forn_nome_fantasia%></h3>
                    <h3>Telefone : <%=forn_tel%></h3> 
                    <h3>E-mail  : <%=forn_email%></h3> 
                    <h3>cnpj : <%=forn_cnpj%></h3> 
                    <h3>inscricao estadual : <%=forn_ie%></h3> 

                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>


