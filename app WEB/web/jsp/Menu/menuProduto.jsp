<!DOCTYPE html>


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
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/cadastraProduto.jsp">Cadastrar</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/excluirProduto.jsp">Excluir</a></p>
      <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Produto/alterarProduto.jsp">Alterar</a></p>
    </div>
    <div class="col-sm-8 text-left"> 
<h1> Menu produto </h1>
    </div>
  </div>
</div>


    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
  </body>
</html>

