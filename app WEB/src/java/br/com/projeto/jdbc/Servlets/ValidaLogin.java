/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Servlets;

import br.com.projeto.jdbc.Beans.Administrador;
import br.com.projeto.jdbc.DAO.AdministradorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author danilo2
 */
public class ValidaLogin extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {

            if (request.getMethod() == "GET")
                doGet(request, res);
                
            else
            if (request.getMethod() == "DELETE")
                doGet(request, res);
            else
            if (request.getMethod() == "PUT")
                doGet(request, res);
            else
            if (request.getMethod() == "POST")
                doGet(request, res);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {


        PrintWriter out = res.getWriter();
        
        String email = request.getParameter("inputEmail");
        String senha = request.getParameter("inputSenha");

        
        //PrintWriter out = HttpServletResponse.getWriter();
        //String email = request.getParameter("email");
        //String senha = request.getParameter("senha");

        Administrador administrador = servletTeste.validaLogin(email, senha);

        String emailRetorno;
        String senhaRetorno;
        String usuarioRetorno;
        if (administrador != null) {

            emailRetorno = administrador.getEmail();
            senhaRetorno = administrador.getSenha();
            usuarioRetorno = administrador.getUsuario();
        } else {
            emailRetorno = senhaRetorno = usuarioRetorno = "";
        }

       /*
        // escreve o texto
        out.println("{\n"
                + " \"sgce\": [\n"
                + "            {\n"
                + "              \"usuario\" : \"" + usuarioRetorno + "\",\n"
                + "              \"email\"   : \"" + emailRetorno + "\",\n"
                + "              \"senha\"   : \"" + senhaRetorno + "\" \n"
                + "            }\n"
                + "         ]\n"
                + "    }\n");
*/
        
        // String variaveis2 = request.getParameter("parametro");
        // Float variaveis = Float.parseFloat(variaveis2);
        //Float calculo = 33f;
        //request.setAttribute(variaveis2, this);
        //request.setAttribute("distancia", calculo);
        //retorna os valores
        request.getRequestDispatcher("jsp/Menu/menuPrincipal.jsp").forward(request, res);

    }
    
    


    /*
     private byte[] AdminToJsonBytes(Administrador administrador) {

     try {
     JSONObject jsonAdmin = new JSONObject();
     jsonAdmin.put("id", administrador.getId());
     jsonAdmin.put("email", administrador.getEmail());
     jsonAdmin.put("senha", administrador.getSenha());
     jsonAdmin.put("usuario", administrador.getUsuario());
     jsonAdmin.put("status", administrador.getStatus());
     String json = jsonAdmin.toString();
     return json.getBytes();
     } catch (JSONException e) {
     }
     return null;
     }

     /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("inputEmail");
        String senha = request.getParameter("inputSenha");

        // String variaveis2 = request.getParameter("parametro");
        // Float variaveis = Float.parseFloat(variaveis2);
        //Float calculo = 33f;
        //request.setAttribute(variaveis2, this);
        //request.setAttribute("distancia", calculo);
        //retorna os valores
        request.getRequestDispatcher("jsp/Menu/menuPrincipal.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
