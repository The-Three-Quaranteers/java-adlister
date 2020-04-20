import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CountServlet", urlPatterns = "/count")
public class CountServlet extends HttpServlet {
    private static int sharedCounter;



    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        getServletContext().log("init() called");
        sharedCounter = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String counter = request.getParameter("counter");


        if (counter != null) {
            if (counter.equalsIgnoreCase("0")){
                int val = Integer.parseInt(counter);
                sharedCounter = val;
                response.sendRedirect("/count");
            }
        }


        sharedCounter++;
        response.setContentType("text/html");
        response.getWriter().write("Incrementing the count to " + sharedCounter);  // accessing a local variable
    }
}