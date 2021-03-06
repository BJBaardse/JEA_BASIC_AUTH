package Controllers;


import models.Userapp;
import rest.UserappDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet( name="loginServlet", displayName="loginServlet", urlPatterns = {"/login"}, loadOnStartup=1)
public class loginController extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long personid = Integer.parseInt(req.getParameter("username"));

        try {
            req.setAttribute("loggedinuser", "Kees");

            req.getRequestDispatcher("/WEB-INF/loggedin.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}