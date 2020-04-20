import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

@WebServlet(name = "CountServlet", urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    private static int sharedCounter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String counter = request.getParameter("counter");

        if (counter != null) {
            try {
                sharedCounter = Integer.parseInt(counter);
                System.out.println(sharedCounter);
                response.sendRedirect("/count");
            } catch (Exception e){
                response.sendRedirect("/count");
            }
        } else {
            sharedCounter++;
            response.setContentType("text/html");
            response.getWriter().write("<h1>Incrementing the count to " + sharedCounter + "</h1>");  // accessing a local variable
        }
    }

    @Override
    public void destroy() {
        getServletContext().log("destroy() called");
    }
}
