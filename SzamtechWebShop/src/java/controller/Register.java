package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import service.WebShopService;

public class Register extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebShopService wbservice = new WebShopService();
        request.setCharacterEncoding("UTF-8");
        String VevoName = request.getParameter("name");
        String VevoEmail = request.getParameter("email");
        String VevoPassw = request.getParameter("password");
        String question = request.getParameter("biztonsagiKerdes");
        String answer = request.getParameter("kerdesValasz");
        
        String kepLink = "/SzamtechWebShop/menuMain";
        
        Boolean saved = wbservice.RegAcc(VevoName, VevoEmail, VevoPassw, question, answer);
        
        if (saved) {
            HttpSession session = request.getSession();
            session.setAttribute("name", VevoName);
            session.setAttribute("email", VevoEmail);
            session.setAttribute("Type", 1);
            response.sendRedirect("menuProfil");
        } else {
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()){
                out.print(      "<!DOCTYPE html>\n" +
                                "<html lang='hu'>\n" +
                                "<head>\n" +
                                "    <meta charset=\"UTF-8\">\n" +
                                "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>\n" +
                                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>\n" +
                                "    <title>Webshop</title>\n" +
                                "    <link rel='stylesheet' href='RES/style.css'>\n" +
                                "</head>\n" +
                                "<body>\n" +
                                "    <header>\n" +
                                "        <nav>\n" +
                                "            <a href='"+kepLink+"' class='logo'><img src='RES/logo.png' alt='logo helye'></a>\n" +
                                "            <form method='post'>\n" +
                                "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuMain'\" class=\"fill\">Kezdőlap</button>\n" +
                                "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuTermekek'\" class=\"fill\">Termékek</button>\n" +
                                "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuTamogatoink'\" class=\"fill\">Támogatóink</button>\n" +
                                "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuLogin'\" class=\"fill\">Bejelentkezés</button>\n" +
                                "            </form>\n" +
                                "        </nav>\n"
                                + "<div class=\"header_atmenet\">\n" +
                                    "        </div>\n" +
                                "    </header>\n" +
                                "    <main class='logreg'>\n" +
                                "        <div class='regisztracio'>  \n" +
                                "            <h2>Regisztráció</h2><br>   \n" +
                                "            <h2>Hibás felhasználónevet, jelszót adott meg, vagy hiányos egy szükséges adat</h2>\n" +
                                "            <form action='reg' method='post'>    \n" +
                                "                <label><b>Felhasználónév</b><br><br></label>    \n" +
                                "                <input type='text' name='name' minlength=\"4\" id='registerName' required>    \n" +
                                "                <br><br>    \n" +
                                "                <label><b>Jelszó</b><br><br></label>    \n" +
                                "                <input type='password' name='password' minlength=\"4\" id='registerPasw' required>    \n" +
                                "                <br><br>\n" +
                                "                <label><b>Email</b><br><br></label>    \n" +
                                "                <input type='email' name='email' id='registerEmail' required>\n" +
                                "                <br><br>    \n"
                                        + "<label><b>Biztonsági kérdés</b><br><br></label>\n"
                                        + "<select name='biztonsagiKerdes' id='bkerdes' style='width: 80%; margin: 15px 0;'>\n"
                                        + "     <option value='KERDES1'>KERDES1</option>\n"
                                        + "     <option value='KERDES2'>KERDES2</option>\n"
                                        + "     <option value='KERDES3'>KERDES3</option>\n"
                                        + "     <option value='KERDES4'>KERDES4</option>\n"
                                        + "</select><br>\n"
                                        + "<label><b>Biztonsági kérdésre válasz</b><br><br></label>\n"
                                        + "<input type='text' name='kerdesValasz' style='margin-bottom: 15px' required/>\n" +
                                "                <input type='submit' id='log' value='Regisztráció' class='bekuld'>  \n" +
                                "            </form>     \n" +
                                "        </div>\n" +
                                "        <div class='bejelentkezes'>  \n" +
                                "            <h2>Bejelentkezés</h2><br>   \n" +
                                "            <form action='logAcc' method='post'>    \n" +
                                "                <label><b>Felhasználónév    \n" +
                                "                </b> \n" +
                                "                <br><br>      \n" +
                                "                </label>    \n" +
                                "                <input type='text' name='name' id='registerName'>    \n" +
                                "                <br><br>    \n" +
                                "                <label><b>Jelszó     \n" +
                                "                </b>    \n" +
                                "                <br><br>   \n" +
                                "                </label>    \n" +
                                "                <input type='password' name='password' id='registerPasw'>    \n" +
                                "                <br><br>    \n" +
                                "                <input type='submit' id='log' value='bejelentkezés' class='bekuld'>  \n" +
                                "            </form>     \n"
                                + "<form action='elfelejtettJelszo' method='post' style='margin-top: 25px;'><input type='submit' value='Elfelejtett jelszó' class='bekuld'></form>\n" +
                                "        </div>    \n" +
                                "    </main>\n" +
                                "    <footer>\n" +
                                    "    <section class = \"bemutatkozas\">\n"
                                + "<div class=\"footer_atmenet\">\n" +
"            </div>\n" +
                                    "            <div class=\"footer_info_box\">\n" +
                                    "                <h3>Elérhetőségek:</h3>\n" +
                                    "                <br>\n" +
                                    "                <div class=\"footer_elerhetoseg\">\n" +
                                    "                    <img src=\"RES/free-phone-icon-vector-27.jpg\" alt=\"\">\n" +
                                    "                    <p class=\"footer_elerhetoseg_szoveg\">+36 20 123 4567</p>\n" +
                                    "                </div>\n" +
                                    "                <div class=\"footer_elerhetoseg\">\n" +
                                    "                    <img src=\"RES/email-vector-icon-png-17.jpg\" alt=\"\">\n" +
                                    "                    <a href=\"mailto: eznemisletezik@gmail.com\">eznemisletezik@gmail.com</a>\n" +
                                    "                </div>\n" +
                                    "                <div class=\"footer_elerhetoseg\">\n" +
                                    "                    <img src=\"RES/gps-icon-vector-7.jpg\" alt=\"\">\n" +
                                    "                    <p class=\"footer_elerhetoseg_szoveg\">7620 Pécs PTE - TTK</p>\n" +
                                    "                </div>  \n" +
                                    "            </div>\n" +
                                    "            <div class=\"footer_info_box\">\n" +
                                    "                <h3>Információk rólunk: </h3>\n" +
                                    "                <br><br>\n" +
                                    "                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Doloribus, tempore!</p>\n" +
                                    "                <br>\n" +
                                    "                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Dolorem blanditiis ipsum, nulla dicta facere eius quos quae quasi nihil tenetur?</p>\n" +
                                    "                <br>\n" +
                                    "            </div>\n" +
                                    "            <div class=\"footer_info_box\">\n" +
                                    "                <h3>Támogatóint: </h3>\n" +
                                    "                <br><br>\n" +
                                    "                <div class = \"footer_tamogatok\">\n" +
                                    "                    <p>lelépünk a pénzel kft</p>\n" +
                                    "                    <br>\n" +
                                    "                </div>\n" +
                                    "            </div>\n" +
                                    "    </section>\n" +
                                    "</footer>" +
                                "</body>");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param RESponse servlet RESponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse RESponse)
            throws ServletException, IOException {
        processRequest(request, RESponse);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param RESponse servlet RESponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse RESponse)
            throws ServletException, IOException {
        processRequest(request, RESponse);
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
