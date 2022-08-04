package util;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "helloServlet", value = "/")
public class HelloServlet extends HttpServlet {
    public void init() {
        System.out.println("Init!");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doGet!");
        response.setContentType("text/html");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost!");
        response.setContentType("text/html");
    }

    public void destroy() {
        System.out.println("Destroy!");
    }
}
