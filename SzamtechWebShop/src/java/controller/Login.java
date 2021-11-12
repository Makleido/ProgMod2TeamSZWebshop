package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.*;

public class Login extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebShopService wbservice = new WebShopService();
        request.setCharacterEncoding("UTF-8");
        String lName = request.getParameter("name");
        String lPassword = request.getParameter("password");
        
        Integer Login = wbservice.LoginAcc(lName, lPassword);
        
        lPassword = wbservice.encrypt(lPassword);
        
        HttpSession session = request.getSession();
        
        
        if (Login == 1) {
            response.setContentType("text/html;charset=UTF-8");
            session.setAttribute("name", lName);
            session.setAttribute("password", lPassword);
            session.setAttribute("Type", Login);
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Welcome "+ lName +"</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } else if (Login == 2) {
            session.setAttribute("name", lName);
            session.setAttribute("password", lPassword);
            session.setAttribute("Type", Login);
            response.sendRedirect("adminSite");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Login</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>ERROR</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

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
