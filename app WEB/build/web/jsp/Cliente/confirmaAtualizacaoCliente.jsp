<%-- 
    Document   : confirmaAtualizacaoCliente
    Created on : 10/04/2016, 12:00:25
    Author     : danilo2
--%>

<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page import="java.util.List"%>

<%
    String id = request.getParameter("JBCliente");
    Cliente cliente = new ClienteDAO().consultaClientebyID(id);
   
    
    new ClienteDAO().alteraClienteByID(cliente.getId_cliente(), cliente);
    
    
    String telefone = request.getParameter("telefone");
        String cpf = request.getParameter("cpf");
        String inscricao_estadual = request.getParameter("inscricao_estadual");
        String cnpj = request.getParameter("cnpj");
        String nomePrinc = request.getParameter("nomePrinc");
        String nomeSec = request.getParameter("nomeSec");
        String TEL = request.getParameter("TEL");

    Cliente clienteAtualizar = new Cliente( Long.parseLong(id), 
            Long.parseLong(telefone), 
            Long.parseLong(cpf), 
            Long.parseLong(inscricao_estadual), 
            Long.parseLong(cnpj), 
            nomePrinc, 
            nomeSec);    
    
    new ClienteDAO().alteraClienteByID(cliente.getId_cliente(),  clienteAtualizar );
%>


<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <h1>Nome : <%=nomePrinc%></h3> 
        <h3>Sobrenome : <%=nomeSec%></h3> 
        <h3>Telefone : <%=telefone%></h3> 
        <h3>CPF : <%=cpf%></h3> 
        <h3>cnpj : <%=cnpj%></h3> 
        <h3>inscricao_estadual : <%=inscricao_estadual%></h3> 
        
        <h1> Alterado com sucesso</h1>
        
        <a  class="btn btn-default" role="button"  class="btn btn-default" role="button" href = "/ControledeEstoques/jsp/Menu/menuPrincipal.jsp" >  retornar </a>
    </div>
  </div>
</div>

        
		
        
		<%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
