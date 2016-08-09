<%-- 
    Document   : validaDeletaCliente
    Created on : 07/04/2016, 12:22:19
    Author     : Danilo
--%>

<%@page import="br.com.projeto.jdbc.DAO.FornecedorDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Fornecedor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente" %>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO" %>
<jsp:useBean id="dao" class= "br.com.projeto.jdbc.DAO.ClienteDAO"/>

<!DOCTYPE html>

<%

    FornecedorDAO f = new FornecedorDAO();
    //Fornecedor fornecedor = f.consultaFornecedor(request.getParameter("tel"));
    List<Fornecedor> fornecedores = f.consultaFornecedorLista(request.getParameter("tel"));
   //List<Cliente> clientes = new ClienteDAO().consultaClienteTel(request.getParameter("tel"));

    String TEL = request.getParameter("tel");

    String forn_nome_empresa = "",
            forn_nome_fantasia = "",
            forn_email = "";
    Long forn_cnpj = 0l,
            forn_ie = 0l,
            forn_tel = 0l;
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

                    <form name="ConfirmAlteraCliente" action="validaAlteraFornecedor.jsp" method="POST">
                        <h1> Fornecedores encontrados</h1>
                        <select name="JBFornecedor">
                            <% for (Fornecedor forn : fornecedores) {%>
                            <option value = "<%=forn.getId_fornecedor()%>" > <%=forn.getForn_nome_empresa()%>  </option>
                            <% }%>
                        </select>

                        TEL: 
                        <input type="text" name="tel" value="<%=TEL%>">  </input>                    

                        Alterar?        
                        <input type="submit"  class="btn btn-default" role="button" name="Sim" value="Sim"> 
                        <input type="submit"  class="btn btn-default" role="button" name="Nao" value="Não" > <br>

                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
