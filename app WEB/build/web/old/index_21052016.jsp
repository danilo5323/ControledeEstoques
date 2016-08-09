<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script language="javascript" type="text/javascript">
    


  function validar() {
     //...

  }
</script>
<%=br.com.projeto.jdbc.Beans.GetPage.getHeader()%>

    <body>
	<%=br.com.projeto.jdbc.Beans.GetPage.getnavBar()%>
	<div class="container-fluid text-center">       
          <div class="row content">
            <div class="col-sm-2 sidenav">
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuCliente.jsp">Cliente</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuFornecedor.jsp">Fornecedor</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Operar/Compra/ComprarProdutos.jsp">Produto</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Menu/menuCliente.jsp">Comprar</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Operar/Venda/VenderProdutos.jsp">Vender</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioVendas.jsp">Relatorios</a></p>
            </div>
            <div class="col-sm-8 text-left"> 
            </div>
          </div>
        </div>
    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
</body>
</html>







