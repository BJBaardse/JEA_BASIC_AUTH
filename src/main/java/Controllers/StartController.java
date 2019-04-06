package Controllers;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name="ListServlet", displayName="ListServlet", urlPatterns = {"/firstpage"}, loadOnStartup=1)
public class StartController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Got a requestt");
        req.setAttribute("helloMessage", "Hello customer. What can I do for you?");
        req.getRequestDispatcher("/WEB-INF/hello.jsp").forward(req, resp);
    }
}