import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ads")
public class ListAdsServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //use the factory to get the dao object
        Ads adsDao = DaoFactory.getAdsDao();
        //method on the Dao to get all the ads
        List<Ad> ads = adsDao.all();
        //pass the data to the jsp
        request.setAttribute("ads",ads);
        request.getRequestDispatcher("ads/index.jsp").forward(request, response);

    }
}
