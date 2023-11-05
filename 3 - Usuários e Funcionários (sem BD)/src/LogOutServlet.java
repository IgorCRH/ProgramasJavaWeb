import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtenha a sess�o existente, se houver
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalida a sess�o
            session.invalidate();
        }

        // Redirecione o usu�rio para a p�gina de login ou qualquer outra p�gina apropriada ap�s o log out
        response.sendRedirect("login.jsp");
    }
}
