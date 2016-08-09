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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author danilo2
 */
@WebServlet(name = "servletTeste", urlPatterns = {"/servletTeste"})
public class servletTeste extends HttpServlet {

    public static Administrador validaLogin(String email, String senha){
    
        Administrador admReturn = null;
        AdministradorDAO daoAdm;
        try{
            daoAdm = new AdministradorDAO();
            admReturn = daoAdm.ConsultaAdministrador(email, senha);
        }
        catch(Exception e){
            return null;
        }
        
        
        
        return admReturn;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String variaveis2 = request.getParameter("parametro");
        Float variaveis = Float.parseFloat(variaveis2);
        
       Float calculo = 33f;
       
       request.setAttribute(variaveis2, this);
       request.setAttribute("distancia", calculo);
        
       //retorna os valores
       request.getRequestDispatcher("computador.jsp").forward(request, response);
    }
}
