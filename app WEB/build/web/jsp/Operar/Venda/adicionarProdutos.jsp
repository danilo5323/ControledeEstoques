<%-- 
    Document   : adicionarProdutos
    Created on : 13/04/2016, 07:32:13
    Author     : danilo2
--%>


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
            cod_barras = "";
    ProdutoDAO f = null;
    Produto produto = null;
    String strQuantidade = "";
    Long quantidade = 0l;
    String retorno = "";

    String total = "0";
    String tmpStr = "";
    try {
        tmpStr = "";
        tmpStr = request.getParameter("itens");
        total = request.getParameter("total");
    } catch (Exception e) {
    }
    retorno = tmpStr;

    try {

        f = new ProdutoDAO();
        produto = f.selecionaProduto(request.getParameter("codBarras"));
        strQuantidade = (request.getParameter("quantidade"));
        quantidade = 0l;

        if (strQuantidade.equals("") || strQuantidade.equals(null)) {
            quantidade = 0l;
        } else {
            quantidade = Long.parseLong(strQuantidade);
        }
    //codigo para inserir a transação na tabela nf

        //gambiarra - farei inserção da data direto no estoque
        //ProdutoEstoque
        EstoqueDAO estoque = new EstoqueDAO();

        estoque.retiraProduto(produto, quantidade);
        //estoque.retiraProduto(produto);

        ProdutoEstoque prdEstoque = estoque.consultaPreco(produto);

        nome = produto.getNome() + " " + produto.getNome_qualificador();
        nome_qualificador = produto.getNome_qualificador();
        unidade_medida = produto.getUnidade_medida();
        cod_barras = produto.getCod_barras();

        Double precoVenda = prdEstoque.getPreco_venda();
        Double tmpTotal = 0.;
        try {
            tmpTotal = Double.parseDouble(total);
        } catch (Exception e) {
        }
        total = (precoVenda + tmpTotal) + "";

        retorno = tmpStr + "<br>item: " + nome + " -  preco: R$" + precoVenda;
    } catch (Exception e) {

    }
%>

<html>
    <%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
    <body>  
        <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>

        <div class="container-fluid text-center">    
            <div class="row content">
                <div class="col-sm-2 sidenav">

                    <p><a class ="btn btn-default btn-group-vertical btn-block" href="selecionaProduto.jsp"> Cancelar </a></p>
                </div>
                <div class="col-sm-8 text-left"> 

                    <form name="ConfirmAlteraProduto" action="selecionaProduto.jsp" method="POST">    

                        <h3>Nome do produto: <%=nome%></h3> 
                        <h3>Nome qualificador: <%=nome_qualificador%></h3>
                        <h3>Unidade de medida : <%=unidade_medida%></h3>
                        <h3>codigo de barras: <%=cod_barras%></h3> 


                        <input type="text" name="codBarras" hidden="hidden" value="<%=cod_barras%>">                    
                        <input type="text" name="itens" hidden="hidden" value="<%=retorno%> ">                    
                        <input type="text" name="total" hidden="hidden" value="<%=total%> ">  
                        <br>
                        <input type="submit"  class="btn btn-default" role="button" name="Sim" value="Prosseguir"> 

                    </form>
                </div>
            </div>
        </div>
        <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
    </body>  
</html>

