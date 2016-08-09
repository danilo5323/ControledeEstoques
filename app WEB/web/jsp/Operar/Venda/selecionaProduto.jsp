<%-- 
    Document   : selecionaProduto
    Created on : 13/04/2016, 10:08:10
    Author     : danilo2
--%>


<%
    String itens = "";
    String total = "";
    String exibir  = "";
    try{
        
        String tmpStr = request.getParameter("itens");
        total = request.getParameter("total");
        if(total != null)
            exibir = tmpStr + "<br> Total: R$ " + total;
        
        if(tmpStr != null)
            itens += tmpStr;
            
    }
    catch(Exception e){
        
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuPrincipal.jsp"> Cancelar </a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    
                    <h1>Buscar produto</h1>
                    
                  
                    <form name="selecionaProduto" action="adicionarProdutos.jsp" method="POST">
                        <table>
                            <tr>
                                <td>Buscar produto pelo codigo de barras</td>
                                <td><input type="text" name="codBarras" value="" ></td>
                              
                                <td><input type="text" name="quantidade" value="1" hidden =" "hidden ></td>
                                <input type="text" name="itens" hidden = "hidden" value="<%=itens%>" ></td>
                                <input type="text" name="total" hidden="hidden" value="<%=total%> ">  
                            </tr>
                        
                        </table>  
                                
                          <%=exibir%>
                    
                          <br><br><br>
                        <a href ="VenderProdutos.jsp"
            type="submit" class="btn btn-default" role="button" name="Adicionar" value="Adicionar">Finalizar venda</a>
                           
                        <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Adicionar"> <br>
                    </form>
                    <!--/div -->
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>