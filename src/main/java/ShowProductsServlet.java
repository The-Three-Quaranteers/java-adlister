//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//@WebServlet(name = "ShowProductsServlet", urlPatterns = "/products/showall")
//public class ShowProductsServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        //Check if the is an id passed in the URL params.
//        long productId = Long.parseLong(req.getParameter("id"));
//
//        // Use the factory to get the dao object
//        Products productsDao = DaoFactory.getProductsDao();
//
//        //Get the product by its ID from ListProductsDao
//        Product product = productsDao.all();
//
//        // Pass the data to the jsp
//        req.setAttribute("products", product);
//        req.getRequestDispatcher("/products/product-show.jsp").forward(req, resp);
//    }
//}