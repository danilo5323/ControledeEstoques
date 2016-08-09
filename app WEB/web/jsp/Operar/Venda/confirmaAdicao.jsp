<%-- 
    Document   : adicionarProdutos
    Created on : 13/04/2016, 07:32:13
    Author     : danilo2
--%>


<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="java.util.List"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<!-- aqui o produto é encontrado e exibida as informações do mesmo, após, se retorna a tela de Venda de produtos para adicionar-se
mais ou finalizar a venda-->
<%

    ProdutoDAO f = new ProdutoDAO();
    Produto produto = f.selecionaProduto(request.getParameter("codBarras"));
   //List<Produto> Produtos = f.consultaProdutoLista(request.getParameter("nome"));
    //List<Produto> Produtos = new ProdutoDAO().consultaProdutoTel(request.getParameter("tel"));

   //codigo para adicinar o produto na lista temporaria de aquisiçao
    String nome = produto.getNome(),
            nome_qualificador = produto.getNome_qualificador(),
            unidade_medida = produto.getUnidade_medida(),
            cod_barras = produto.getCod_barras();

    /// esta classe deve ficar na alteraçao da nota fiscal, pois a partir dela, poderei retornar 
    // os produtos, caso o cliente desista da compra
    //Boolean adicionado = ProdutoDAO.vendeProduto();
%>

<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>

        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/index.html">Sair</a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    <%if (produto != null) {%>
                    <form name="ConfirmAlteraProduto" action="VenderProduto.jsp" method="POST">
                        Produto Adicionado
                        <input type="text" name="tel" value="<%=nome%>">  <\input>                    
                        Excluir?        
                        <input type="submit"  class="btn btn-default" role="button" name="Ok" value="Sim"> 
                        <input type="submit"  class="btn btn-default" role="button" name="Nao" value="Não" > <br>
                        <% } else {%>

                        <% }%>
                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>
</html>

