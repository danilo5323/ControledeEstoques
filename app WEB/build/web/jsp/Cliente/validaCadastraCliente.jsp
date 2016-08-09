<%-- 
    Document   : ValidaInserirCliente
    Created on : 04/04/2016, 16:47:13
    Author     : Danilo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente" %>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO" %>

<!DOCTYPE html>

<%

    String telefone = request.getParameter("telefone");
    String cpf = request.getParameter("cpf");
    String inscricao_estadual = request.getParameter("inscricao_estadual");
    String cnpj = request.getParameter("cnpj");
    String nomePrinc = request.getParameter("nomePrinc");
    String nomeSec = request.getParameter("nomeSec");

    Cliente cliente = new Cliente();
    cliente.setCnpj(Long.parseLong(cnpj));
    cliente.setCpf(Long.parseLong(cpf));
    cliente.setInscricao_estadual(Long.parseLong(inscricao_estadual));
    cliente.setNomePrinc(nomePrinc);
    cliente.setNomeSec(nomeSec);
    cliente.setTelefone(Long.parseLong(telefone));
    new ClienteDAO().cadastrarCliente(cliente);
%>

<html>
    
<head>
 
  <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
</head>
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
                    <h3>Nome : <%=nomePrinc%></h3> 
                    <h3>Sobrenome : <%=nomeSec%></h3> 
                    <h3>Telefone : <%=telefone%></h3> 
                    <h3>CPF : <%=cpf%></h3>
                    <h3>cnpj : <%=cnpj%></h3> 
                    <h3>inscricao_estadual : <%=inscricao_estadual%></h3>

                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>

