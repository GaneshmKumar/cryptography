import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GaNeShKuMaRm
 */
@WebServlet(urlPatterns = {"/login"})
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try
        {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            MD5HASH md5 = new MD5HASH();
            DB db = new DB();
            String md5Password = md5.calculateMD5(password);
            String name = db.login(email, md5Password);
            if(name.equals("$$$$$"))
            {
                out.println("Invalid username or password");
            }
            else
            {
                out.println("Welcome " + name);
            }            
        }
        catch(Exception e)
        {
            out.println(e);
        }
        finally
        {
            out.close();
        }   
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
