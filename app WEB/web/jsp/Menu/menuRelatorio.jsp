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
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioEntradas.jsp">Entrada</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioSaidas.jsp">Saida</a></p>
              <p><a class ="btn btn-default btn-group-vertical btn-block" href="/ControledeEstoques/jsp/Relatorios/RelatorioPerdas.jsp">Perda</a></p>
            </div>
            <div class="col-sm-8 text-left"> 
            </div>
          </div>
        </div>
    <%=br.com.projeto.jdbc.Beans.GetPage.getFooter()%>
</body>
