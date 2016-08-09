<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <!--div class="container"/ -->
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/CadastraCliente.jsp">Cadastrar</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/ExcluirCliente.jsp">Excluir</a></p>
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Cliente/AlterarCliente.jsp">Alterar</a></p>
                </div>
                <div class="col-sm-8 text-left"> 
                    <h1>Cadastrar cliente</h1>
                    <form name="cadastraCliente" action="validaCadastraCliente.jsp" >
                        <table>
                            <tr>
                                <td>Primeiro nome:  </td>
                                <td><input type="text" name="nomePrinc" value="" pattern="[a-zA-Z]+" ></td>
                            </tr>
                            <tr>
                                <td>Segundo nome:  </td>
                                <td><input type="text" name="nomeSec" value=""  pattern="[a-zA-Z]+"></td>
                            </tr>
                            <tr>
                                <td>Telefone:  </td>
                                <td><input type="text" name="telefone" value=""  pattern="[0-9]+"  maxlenght = "13" minlenght = "10"></td>
                            </tr>
                            <tr>
                                <td>CPF:  </td>
                                <td><input type="text" name="cpf" value=""  pattern="[0-9]+"></td>
                            </tr>
                            <tr>
                                <td>Inscrição Estadual:  </td>
                                <td><input type="text" name="inscricao_estadual" value=""  pattern="[0-9]+"  maxlenght = "12" minlenght = "12"></td>
                            </tr>
                            <tr>
                                <td>CNPJ:  </td>
                                <td><input type="text" name="cnpj" value=""  pattern="[0-9]+" maxlenght = "14" minlenght = "14"></td>
                            </tr>

                        </table>  
                        <input  class="btn btn-default" role="button" type="submit" name="Enviar" value="Enviar"> <br>
                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>
