<%-- 
    Document   : adicionarProdutos
    Created on : 13/04/2016, 07:32:13
    Author     : danilo2
--%>


<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="br.com.projeto.jdbc.Beans.ProdutoEstoque"%>
<%@page import="br.com.projeto.jdbc.DAO.EstoqueDAO"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="java.util.List"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<!-- aqui busca o produto e envia a confirçaõa da existencia do produto-->
<%
    String nome = "",
            nome_qualificador = "",
            unidade_medida = "",
            cod_barras = "",
            data_compra = request.getParameter("data_compra"),
            preco_compra = request.getParameter("preco_compra"),
            preco_venda = request.getParameter("preco_venda"),
            data_validade = request.getParameter("data_validade"),
            medida = request.getParameter("medida");
    medida = "0";
    String data_compra_tmp = data_compra.replace("/", "");
    String data_validade_tmp = data_validade.replace("/", "");

    Long quantidade = Long.parseLong(request.getParameter("quantidade"));

    ProdutoDAO f = new ProdutoDAO();
    cod_barras = request.getParameter("codBarras");
    Produto produto = f.selecionaProduto(cod_barras);

    Float precovenda, precocompra;
    precovenda = Float.parseFloat(preco_venda);
    precocompra = Float.parseFloat(preco_compra);
    //codigo para inserir a transação na tabela nf
    Boolean cadastrar = false;

    if (produto != null) {
        //gambiarra - farei inserção da data direto no estoque
        //ProdutoEstoque
        EstoqueDAO estoque = new EstoqueDAO();

        ProdutoEstoque prdEstoque = new ProdutoEstoque();

        prdEstoque.setProduto(produto);

        Long dtCompra = Long.parseLong(data_compra_tmp);
        prdEstoque.setData_compra(new Date(dtCompra));

        prdEstoque.setPreco_compra(Double.parseDouble(preco_compra));

        prdEstoque.setPreco_venda(Double.parseDouble(preco_venda));

        Long dtValidade = Long.parseLong(data_validade_tmp);
        prdEstoque.setData_validade(new Date(dtValidade));

        prdEstoque.setMedida(Double.parseDouble(medida));

        estoque.insereProdutos(prdEstoque, quantidade);

        nome = produto.getNome();
        nome_qualificador = produto.getNome_qualificador();
        unidade_medida = produto.getUnidade_medida();
        cod_barras = produto.getCod_barras();

        cadastrar = true;
    } else {
        ///codigo para cadastrar o novo tipo de produto
        ///talvez abrir a tela para cadastrar novo produto

        cadastrar = false;
    }
%>

<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 

    <body> 
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">
                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuPrincipal.jsp"> Cancelar</a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    <% if (cadastrar) {%>
                    <form name="ConfirmAlteraProduto" action="ComprarProdutos.jsp" method="POST">    

                        <h3>Nome do produto: <%=nome%></h3> 
                        <h3>Nome qualificador: <%=nome_qualificador%></h3> 
                        <h3>Unidade de medida : <%=unidade_medida%></h3> 
                        <h3>codigo de barras: <%=cod_barras%></h3> 
                        <h3> data de compra <%=data_compra%> </h3>
                        <h3> preco de compra: R$ <%=precocompra%>  </h3> 
                        <h3> preco de venda: R$ <%=precovenda%>   </h3> 
                        <h3> data de valdiade <%=data_validade%> </h3> 
                        <h3> medida <%=medida%>        </h3> 

                        <input type="text" name="codBarras" hidden="hidden" value="<%=cod_barras%>">  


                        <input type="submit"  class="btn btn-default" role="button" name="Sim" value="Prosseguir"> 

                    </form>

                    <% } else { %>

                    <p><a  class="btn btn-default" role="button"  role="button"   href="/ControledeEstoques/jsp/Menu/menuProduto.jsp">Cadastrar novo produto</a></p>

                    <% }%>
                </div>
            </div>
        </div>

        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>


