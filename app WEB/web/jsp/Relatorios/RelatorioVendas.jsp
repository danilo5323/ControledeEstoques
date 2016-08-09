<%@page import="com.sun.javafx.scene.control.skin.VirtualFlow.ArrayLinkedList"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.projeto.jdbc.Beans.ProdutoEstoque"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="br.com.projeto.jdbc.Beans.Produto"%>
<%@page import="br.com.projeto.jdbc.DAO.ProdutoDAO"%>
<%@page import="br.com.projeto.jdbc.DAO.EstoqueDAO"%>
<%-- 
    Document   : selecionaProduto
    Created on : 13/04/2016, 10:08:10
    Author     : danilo2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%

    ProdutoDAO f = new ProdutoDAO();
    Produto produto = f.selecionaProduto(request.getParameter("codBarras"));

    //codigo para inserir a transação na tabela nf
    //gambiarra - farei inserção da data direto no estoque
    //ProdutoEstoque
    EstoqueDAO estoque = new EstoqueDAO();

    List<String> listaEstoque = estoque.listarProdutosVendidos();

    List<String> listaEstoque2 = new ArrayList<String>();

 //   String[] a = {"ab","cd","ef","gh"};
    List<String> tmp = new ArrayList<String>();
    
    listaEstoque2.add("<thead> <tr>"
                + "<th> Produto  </th>  "
            + "<th> Quantidade  </th>  "
                + "<th> Valor de venda </th> "
                + "<th> Valor de compra  </th>  "
                + "<th> Lucro   </th> "
                + "<tr> </thead>");
    for (int i = 0; i < listaEstoque.size(); i++) {
        String[] a = listaEstoque.get(i).split(":-");

        listaEstoque2.add("<tr>"
                + "<td> " + a[0] + "  </td>  "
                + "<td> " + a[4] + "  </td>  "
                + "<td> R$" + a[1] + "   </td> "
                + "<td> R$" + a[2] + "   </td>  "
                + "<td> R$" + a[3] + "   </td> "
                + "<tr>");
    }
    //String tmpStr = a[0] + a[1] + a[2] + a[3];
    //estoque.retiraProduto(produto);

%>

<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%> 

<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>
<body>
    <%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
    <div class="container-fluid text-center">       
        <div class="row content">
            <div class="col-sm-2 sidenav">
                
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioEntradas.jsp">Estoque</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioVendas.jsp">Vendas</a></p>
                <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioPerdas.jsp">Perdas</a></p>
            </div>
            <div class="col-sm-8 text-left">
                <h1>Relatorio de vendas </h1>
                <div class="table-responsive">
                    
                    <table class="table table-striped">


                        <% for (String str : listaEstoque2) {%>
                        <%=str%>
                        <% }%>
                    </table>
                </div> 
            </div>
        </div>
    </div>
    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
</body>





