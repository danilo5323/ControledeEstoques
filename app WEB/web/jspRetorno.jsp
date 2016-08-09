<%@page import="br.com.projeto.jdbc.Servlets.servletTeste"%>
<%@page import="br.com.projeto.jdbc.Beans.Administrador"%>
<%@page import="br.com.projeto.jdbc.DAO.ClienteDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Cliente"%>
<%@page  pageEncoding="UTF-8"%>


<%
    
    String email = request.getParameter("email");
    String senha = request.getParameter("senha");
    
    Administrador administrador = servletTeste.validaLogin(email, senha);
    
    String emailRetorno;
    String senhaRetorno;
    String usuarioRetorno;
    if(administrador != null){
    
 emailRetorno = administrador.getEmail();
 senhaRetorno = administrador.getSenha();
 usuarioRetorno = administrador.getUsuario();
    }
    else {
    emailRetorno =  senhaRetorno =  usuarioRetorno = "";
            }
    
    /*Cliente cliente = new ClienteDAO().consultaClientebyID();

    String TEL = (cliente.getTelefone()) + "";
    String nomePcint = cliente.getNomePrinc();

    new ClienteDAO().excluirClienteByID(request.getParameter("JBCliente"));

    String telefone = cliente.getTelefone() + "";
    String cpf = cliente.getCpf() + "";
    String inscricao_estadual = cliente.getInscricao_estadual() + "";
    String cnpj = cliente.getCnpj() + "";
    String nomePrinc = cliente.getNomePrinc() + "";
    String nomeSec = cliente.getNomeSec() + "";
            */
%>






    {
 "sgce": [
            {
              "usuario" : "<%=usuarioRetorno%>",
              "email"   : "<%=emailRetorno%>",
              "senha"   : "<%=senhaRetorno%>" 
            }
         ]
    }
