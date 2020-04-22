import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShowProductServlet", urlPatterns = "/products/show")
public class ShowProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //Check if the is an id passed in the URL params.
        long productId = Long.parseLong(req.getParameter("id"));

        // Use the factory to get the dao object
        Products productsDao = DaoFactory.getProductsDao();

        //Get the product by its ID from ListProductsDao
        Product product = productsDao.findById(productId);

        // Pass the data to the jsp
        req.setAttribute("product", product);
        req.getRequestDispatcher("/products/product-show.jsp").forward(req, resp);
    }
}