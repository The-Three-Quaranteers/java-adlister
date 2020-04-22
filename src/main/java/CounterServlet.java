import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "CounterServlet", urlPatterns = "/count")
public class CounterServlet extends HttpServlet {
    private int counter = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        counter += 1;
        response.getWriter().println("<h1>The count is " + counter + ".</h1>");
    }

public static ArrayList<Integer> findFactors012(int num){
        ArrayList<Integer> factors = new ArrayList<>();

        for (int i = 1; i <= num; i++){
            if (num%i==0){
                factors.add(i);
            }
        }

        return factors;
}

    public static void main(String[] args) {
//        byte zero = 0;
//        int one = 1;
//        char letter = 'H';
//        long lon = 3110;
//        double dec = 2.0;
//        boolean val = true;
//
//        String output = "" + letter + lon + " w" + zero + "r" + one + "d " + dec + " " +val;
//        System.out.println(output);
        System.out.println(findFactors012(144));
    }

}
