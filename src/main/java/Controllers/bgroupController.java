package Controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name="bgroupservlet", displayName="bgroupservlet", urlPatterns = {"/secondpage"}, loadOnStartup=1)
public class bgroupController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("helloMessage", "Hello customer. What can I do for you? USERS B");
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }
}