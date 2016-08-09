/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetosgce2.com.br.sgce2.Models;

/**
 *
 * @author danilo2
 */
public class GetPage {
    private static String header = 
"<head>\n" +
"  <title>Project SGCE</title>\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"  <link rel=\"stylesheet\" href=\"/ControledeEstoques/dist/css/bootstrap.min.css\">\n" +
"  <script src=\"/ControledeEstoques/dist/js/bootstrap.min.js\"></script>\n" +
"  <style>\n" +
"    /* Remove the navbar's default margin-bottom and rounded borders */ \n" +
"    .navbar {\n" +
"      margin-bottom: 0;\n" +
"      border-radius: 0;\n" +
"    }\n" +
"    \n" +
"    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */\n" +
"    .row.content {height: 450px}\n" +
"    \n" +
"    /* Set gray background color and 100% height */\n" +
"    .sidenav {\n" +
"      padding-top: 20px;\n" +
"      background-color: #f1f1f1;\n" +
"      height: 100%;\n" +
"    }\n" +
"    \n" +
"    /* Set black background color, white text and some padding */\n" +
"    footer {\n" +
"      background-color: #555;\n" +
"      color: white;\n" +
"      padding: 15px;\n" +
"    }\n" +
"    \n" +
"    /* On small screens, set height to 'auto' for sidenav and grid */\n" +
"    @media screen and (max-width: 767px) {\n" +
"      .sidenav {\n" +
"        height: auto;\n" +
"        padding: 15px;\n" +
"      }\n" +
"      .row.content {height:auto;} \n" +
"    }\n" +
"  </style>\n" +
"</head>\n" ;
    
    public static String head2 = "<head>\n" +
"  <title>Bootstrap Example</title>\n" +
"  <meta charset=\"utf-8\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
"  <link rel=\"stylesheet\" href=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\">\n" +
"  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js\"></script>\n" +
"  <script src=\"http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js\"></script>\n" +
"  <style>\n" +
"    /* Remove the navbar's default margin-bottom and rounded borders */ \n" +
"    .navbar {\n" +
"      margin-bottom: 0;\n" +
"      border-radius: 0;\n" +
"    }\n" +
"    \n" +
"    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */\n" +
"    .row.content {height: 450px}\n" +
"    \n" +
"    /* Set gray background color and 100% height */\n" +
"    .sidenav {\n" +
"      padding-top: 20px;\n" +
"      background-color: #f1f1f1;\n" +
"      height: 100%;\n" +
"    }\n" +
"    \n" +
"    /* Set black background color, white text and some padding */\n" +
"    footer {\n" +
"      background-color: #555;\n" +
"      color: white;\n" +
"      padding: 15px;\n" +
"    }\n" +
"    \n" +
"    /* On small screens, set height to 'auto' for sidenav and grid */\n" +
"    @media screen and (max-width: 767px) {\n" +
"      .sidenav {\n" +
"        height: auto;\n" +
"        padding: 15px;\n" +
"      }\n" +
"      .row.content {height:auto;} \n" +
"    }\n" +
"  </style>\n" +
"</head>";
    public static String getnavBar(){
        return navBar;
    }
    
    String n = 
"            <div class=\"col-sm-8 text-left\"> \n" +
"            </div>";
    public static String navBar = "\n" +
"<nav class=\"navbar navbar-inverse\">\n" +
"  <div class=\"container-fluid\">\n" +
"    <div class=\"navbar-header\">\n" +
"      <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#myNavbar\">\n" +
"        <span class=\"icon-bar\"></span>\n" +
"        <span class=\"icon-bar\"></span>\n" +
"        <span class=\"icon-bar\"></span>                        \n" +
"      </button>\n" +
"      <a class=\"navbar-brand\" href=\"/ControledeEstoques/index.jsp\">SGCE</a>\n" +
"    </div>\n" +
"    <div class=\"collapse navbar-collapse\" id=\"myNavbar\">\n" +
"      <ul class=\"nav navbar-nav\">\n" +
"        <li class=\"active\"><a href=\"/ControledeEstoques/jsp/Menu/menuCliente.jsp\">Cliente</a></li>\n" +
"        <li class=\"active\"><a href=\"/ControledeEstoques/jsp/Menu/menuFornecedor.jsp\">Fornecedor</a></li>\n" +
"        <li class=\"active\"><a href=\"/ControledeEstoques/jsp/Menu/menuProduto.jsp\">Produto</a></li>\n" +
"        <li class=\"active\"><a href=\"/ControledeEstoques/jsp/Operar/Compra/ComprarProdutos.jsp\">Comprar</a></li>\n" +
"		<li class=\"active\"><a href=\"/ControledeEstoques/jsp/Operar/Venda/VenderProdutos.jsp\">Vender</a></li>\n" +
"		<li class=\"active\"><a href=\"/ControledeEstoques/jsp/Relatorios/RelatorioVendas.jsp\">Relatorios</a></li>\n" +
"      </ul>\n" +
"      <ul class=\"nav navbar-nav navbar-right\">\n" +
"        <li><a href=\"#\"><span class=\"glyphicon glyphicon-log-in\"></span> Login</a></li>\n" +
"      </ul>\n" +
"    </div>\n" +
"  </div>\n" +
"</nav>";
    
    private static String footer = "<footer class=\"container-fluid text-center\">\n" +
"  <p>SGCE</p>\n" +
"</footer>\n" +
"\n" +
" ";
    private String buttons;

    public static String getHeader() {
        return head2;
    }

    public static void setHeader(String header) {
        GetPage.header = header;
    }

    public static String getFooter() {
        return footer;
    }

    public static void setFooter(String footer) {
        GetPage.footer = footer;
    }

    public String getButtons() {
        return buttons;
    }

    public void setButtons(String buttons) {
        this.buttons = buttons;
    }
    
    
}
