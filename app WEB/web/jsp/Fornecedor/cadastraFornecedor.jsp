<%-- 
    Document   : CadastraCliente
    Created on : 21/03/2016, 22:48:46
    Author     : Danilo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>

    <!--div class="container"/ -->
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

                    <form name="cadastraFornecedor" action="validaCadastraFornecedor.jsp" method="POST">


                        <h1>Cadastrar fornecedor</h1>




                        <table>
                            <tr>
                                <td>Nome da empresa:  </td>
                                <td><input type="text" name="forn_nome_empresa" value="" pattern="[a-zA-z]+"></td>
                            </tr>
                            <tr>
                                <td>Nome Fantasia:  </td>
                                <td><input type="text" name="forn_nome_fantasia" value="" pattern="[a-zA-z]+" ></td>
                            </tr>
                            <tr>
                                <td>Telefone:  </td>
                                <td><input type="text" name="forn_tel" value="" pattern="[0-9]+"></td>
                            </tr>
                            <tr>
                                <td>E-mail:  </td>
                                <td><input type="text" name="forn_email" value="" ></td>
                            </tr>
                            <tr>
                                <td>CNPJ:  </td>
                                <td><input type="text" name="forn_cnpj" value="" pattern="[0-9]+"></td>
                            </tr>
                            <tr>
                                <td>Inscrição Estadual:  </td>
                                <td><input type="text" name="forn_ie" value="" pattern="[0-9]+"></td>
                            </tr>
                        </table>  
                        <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Enviar"> <br>
                    </form>

                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
        <!--/div -->
    </body>

</html>
