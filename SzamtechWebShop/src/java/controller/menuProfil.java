package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import service.*;

public class menuProfil extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        WebShopService wbservice = new WebShopService();
        String kepLink = "/SzamtechWebShop/menuMain";
        String basket = "/SzamtechWebShop/kosar";
        HttpSession session = request.getSession();
        String fname = session.getAttribute("name").toString();
        
        ArrayList<Vevo> vevok = wbservice.getVevok();
        Vevo v = new Vevo();
        for (Integer i = 0; i < vevok.size(); i++) {
            if (vevok.get(i).getVevoNev().equals(fname)){
                v = vevok.get(i);
                break;
            }
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(  "<!DOCTYPE html>\n" +
                        "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <title>WebShop</title>\n" +
                        "    <link rel=\"stylesheet\" href=\"RES/style.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <header>\n" +
                        "        <nav>\n" +
                        "            <a href=\""+kepLink+"\"class=\"logo\"><img src=\"RES/logo.png\" alt=\"logo helye\"></a>\n" +
                        "            <form method='post'>\n" +
                        "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuMain'\" class=\"fill\">Kezdőlap</button>\n" +
                        "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuTermekek'\" class=\"fill\">Termékek</button>\n" +
                        "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuTamogatoink'\" class=\"fill\">Támogatóink</button>\n" +
                        "                <button type=\"submit\" name = \"menup\" onclick=\"form.action='menuProfil'\" class=\"fill\">Profil</button>\n" +
                        "            </form>\n" +
                        "            <div class=\"szolgaltatasok\">\n" +
                        "                <a href=\""+basket+"\" class=\"funkciok\">\n" +
                        "                    <img src=\"RES/basket1.png\" alt=\"kosár kép\">\n" +
                        "                </a>\n" +
                        "            </div>\n" +
                        "        </nav>\n" +
                        "    </header>\n" +
                        "    <main class=\"mainProfil\">\n" +
                        "        <div class=\"felhasznalo\">\n" +
                        "            <div class=\"fhMainInfo\">\n" +
                        "                <h1 class=\"fName\">"+v.getVevoNev()+"</h1>\n" +
                        "                <span class=\"femail\">"+v.getVevoEmail()+"</span>\n" +
                        "            </div>\n" +
                        "            <div class=\"fhOtherInfo\">\n" +
                        "                <span>Számlázási cím: "+v.getVevoSzamCim()+"</span><br>\n" +
                        "                <span>Adószám: "+v.getVevoAdoszam()+"</span><br>\n" +
                        "            </div>\n" +
                        "            <div class=\"fhFunction\">\n" +
                        "                <form action=\"chPassw\" method=\"post\">\n" +
                        "                    <input type=\"password\" name=\"password\" /><br>\n" +
                        "                    <button type=\"submit\">Jelszó változtatás</button>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "            <div class=\"fhFunction\">\n" +
                        "                <form action=\"chUserSettings\" method=\"post\">\n" +
                        "          <h3>Számlázási cím</h3>          "
                                + "<input type=\"text\" name=\"szamCim\" /><br>\n" +
                        "          <h3>Adószám</h3>          "
                                + "<input type=\"text\" name=\"adSzam\" minlength=\"11\" maxlength=\"11\"/><br>\n" +
                        "                    <button type=\"submit\">Adatok változtatása</button>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "            <div class=\"fhFunction\">\n" +
                        "                <form action=\"listVasarlasok\">\n" +
                        "                    <button type=\"submit\">Vásárlások listázása</button>\n" +
                        "                </form>\n" +
                        "            </div>" +
                        "            <div class=\"fhFunction\">\n" +
                        "                <form action=\"logOut\" method=\"post\">\n" +
                        "                    <button type=\"submit\">Kijelentkezés</button>\n" +
                        "                </form>\n" +
                        "            </div>\n" +
                        "            <div class=\"fhFunction\">\n" +
                        "                <form action=\"delAcc\">\n" +
                        "                    <button type=\"submit\" style='background-color: RED; color: YELLOW'>Account törlése</button>\n" +
                        "                </form>\n" +
                        "            </div>" +
                        "        </div>\n" +
                        "    </main>\n" +
                        "    <footer>\n" +
                        "        <section class = \"bemutatkozas\">\n" +
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
                        "        </section>\n" +
                        "    </footer>\n" +
                        "</body>\n" +
                        "</html>");
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
