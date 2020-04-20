import java.awt.*;
import java.io.*;
import javax.print.attribute.standard.Media;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "HelloWorldServelet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if (name != null){
            try {
                out.println("<h1>Hello, "+ name +"!</h1>");
            } catch (Exception e) {
                response.resetBuffer();
                out.println("<h1>Hello, World!</h1>");
            }
        }else{
            out.println("<h1>Hello, World!</h1>");
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/banner");
        if (dispatcher != null)
            dispatcher.include(request, response);




    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() called");
    }

}
