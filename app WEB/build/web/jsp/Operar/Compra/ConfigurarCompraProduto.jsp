<%-- 
    Document   : selecionaProduto
    Created on : 13/04/2016, 10:08:10
    Author     : danilo2
--%>

<%@page import="java.sql.Date"%>
<%@page import="br.com.projeto.jdbc.Beans.ProdutoEstoque"%>
<%@page import="br.com.projeto.jdbc.DAO.EstoqueDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    String nome = "",
            nome_qualificador = "",
            unidade_medida = "",
            cod_barras = "";

    Long quantidade = Long.parseLong(request.getParameter("quantidade"));
    cod_barras = request.getParameter("codBarras");
    String listaProduto = "";
    ProdutoDAO f = new ProdutoDAO();
    String codBarras = request.getParameter("codBarras");
    Produto produto = f.selecionaProduto(codBarras);
    //codigo para inserir a transação na tabela nf
    Boolean cadastrar = false;

    if (produto != null) {
   //gambiarra - farei inserção da data direto no estoque
        //ProdutoEstoque
        /*
         EstoqueDAO estoque = new EstoqueDAO();

        
         ProdutoEstoque prdEstoque = new ProdutoEstoque();

        
         prdEstoque.setProduto(produto);

        

         estoque.insereProdutos(prdEstoque, quantidade);

         nome = produto.getNome();
         nome_qualificador = produto.getNome_qualificador();
         unidade_medida = produto.getUnidade_medida();
         cod_barras = produto.getCod_barras();
         listaProduto = "<br>" + nome
         + "<br>" + nome_qualificador
         + "<br>" + unidade_medida
         + "<br>" + cod_barras;
         cadastrar = true;
         */
    } else {
       ///codigo para cadastrar o novo tipo de produto
        ///talvez abrir a tela para cadastrar novo produto

        cadastrar = false;
    }
%>

<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 
    <script>
        function formatdata(var input) {
            var barra1 = 2;
            var barra2 = 4;
            var resultado = input.substr(0, barra1) + "/" + input.substr(barra1, barra2) + "/" + input.substr(barra2);
            return resultado;
        }

        function mascaraData(campoData) {
            var data = campoData.value;
            if (data.length == 2) {
                data = data + '/';
                document.forms[0].data.value = data;
                return true;
            }
            if (data.length == 5) {
                data = data + '/';
                document.forms[0].data.value = data;
                return true;
            }
        }
    </script>
    <body>
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <!--div class="container"/ -->
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuPrincipal.jsp"> Cancelar</a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    <h1>Comprar produtos</h1>
                    <form name="selecionaProduto" action="adicionarProdutos.jsp" method="POST">
                        <table>

                            <tr>
                                <td>
                                    <%=listaProduto%>
                                </td>
                            </tr>
                            <tr>
                            <input type="text" name="codBarras" value="<%=cod_barras%>" hidden="hidden">
                            <td>data de compra</td> <td> <input type="text" name="data_compra"  OnKeyUp="mascaraData(this);" value="" pattern="[0-9]8"></td>
                            </tr>
                            <tr>
                                <td>preco pago</td> <td> <input type="text" name="preco_compra" value="" pattern="[0-9]+"></td>
                            </tr>
                            <tr>
                                <td>preco de venda </td> <td> <input type="text" name="preco_venda" value="" pattern="[0-9]+"></td>
                            </tr>
                            <tr>
                                <td>data de validade</td> <td> <input type="text" name="data_validade" OnKeyUp="mascaraData(this);" value="" pattern="[0-9]8"></td>
                            </tr>
                            <tr>
                                <td>quantidade</td> <td>  <input type="text" name="quantidade" value="<%=quantidade%>" pattern="[0-9]+"></td>
                            </tr>
                        </table>  
                        <input type="submit"  class="btn btn-default" role="button" name="Enviar" value="Comprar"> <br>
                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
        <!--/div -->
    </body>
</html>