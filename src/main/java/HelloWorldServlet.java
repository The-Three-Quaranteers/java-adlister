import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(name = "HelloWorldServelet", urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        if (name != null) {
            try {
                out.println("<h1>Hello, " + name + "!</h1>");
            } catch (Exception e) {
                response.resetBuffer();
            }
        } else {
            out.println("<h1>Hello, World!</h1>");
        }
    }
}
