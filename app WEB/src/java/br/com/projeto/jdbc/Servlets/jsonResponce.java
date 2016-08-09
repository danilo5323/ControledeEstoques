/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc.Servlets;

import br.com.projeto.jdbc.Beans.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 *
 * @author Danilo
 */
public class jsonResponce extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //response.setContentType("text/html;charset=UTF-8");
            //modificar para Json
        String email = request.getParameter("inputEmail");
        String senha = request.getParameter("inputSenha");
 
       //retorna os valores
       request.getRequestDispatcher("retorno.json").forward(request, response);
      
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet jsonResponce</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet jsonResponce at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
     
    }
    
        
     /*
    public static List<Administrador> lerJsonLivros(JSONObject json) throws JSONException{
        List<Administrador> listaDeAddministradores= new ArrayList<Administrador>();
        String categoriaAtual;
        
        JSONArray jsonNovatec = json.getJSONArray("novatec");
        for(int i = 0; i < jsonNovatec.size(); i++){

            JSONObject jsonCategoria = jsonNovatec.getJSONObject(i);
            categoriaAtual = jsonCategoria.getString("categoria");

            JSONArray jsonLivros = jsonCategoria.getJSONArray("livros");
            for(int j= 0; j < jsonLivros.size(); j++){

                JSONObject jsonLivro = jsonLivros.getJSONObject(j);

                Administrador adm;
                adm = 
                        new Administrador (0l, 
                                jsonLivro.getString("usuario"),
                                jsonLivro.getString("email"), 
                                jsonLivro.getString("senha"));
                listaDeAddministradores.add(adm);
            }
        }
        return listaDeAddministradores;
    }

*/
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
