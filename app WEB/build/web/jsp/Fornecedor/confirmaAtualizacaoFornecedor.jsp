<%-- 
    Document   : confirmaAtualizacaoCliente
    Created on : 10/04/2016, 12:00:25
    Author     : danilo2
--%>

<%@page import="br.com.projeto.jdbc.DAO.FornecedorDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Fornecedor"%>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page import="java.util.List"%>

<%
    String id = request.getParameter("JBCliente");
    Fornecedor fornecedor = new FornecedorDAO().consultaFornecedorByID(id);

    String forn_nome_empresa = request.getParameter("forn_nome_empresa"),
            forn_nome_fantasia = request.getParameter("forn_nome_fantasia"),
            forn_email = request.getParameter("forn_email");
    String str_Forn_ie = request.getParameter("forn_ie");
    Long forn_ie = Long.parseLong(str_Forn_ie);

    String str_Forn_CNPJ = request.getParameter("forn_cnpj");
    Long forn_cnpj = Long.parseLong(str_Forn_CNPJ);

    String str_forn_tel = request.getParameter("forn_tel");
    Long forn_tel = Long.parseLong(str_forn_tel);

    Fornecedor fornecedorAtualizar = new Fornecedor(
            forn_nome_empresa,
            forn_nome_fantasia,
            forn_email,
            forn_cnpj,
            forn_ie,
            forn_tel);

    //new ClienteDAO().alteraClienteByID(cliente.getId_cliente(),  clienteAtualizar );
    new FornecedorDAO().alteraFornecedorByID(Long.parseLong(id), fornecedorAtualizar);


%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
<form name="return" action="index.html" method="POST">
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/cadastraFornecedor.jsp">Cadastrar</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/excluirFornecedor.jsp">Excluir</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Fornecedor/alterarFornecedor.jsp">Alterar</a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    
                    <h3>nome da empresa: <%=forn_nome_empresa%></h3> 
                    <h3>nome fantasia: <%=forn_nome_fantasia%></h3> 
                    <h3>email : <%=forn_email%></h3> 
                    <h3>Telefone : <%=forn_tel%></h3> 
                    <h3>CNPJ : <%=forn_cnpj%></h3> 
                    <h3>Inscrição estadual : <%=forn_ie%></h3> 


                    <h1> Atualizado com sucesso</h1>
                    <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href = "/ControledeEstoques/jsp/Menu/menuPrincipal.jsp" >  retornar </a>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
</form>
