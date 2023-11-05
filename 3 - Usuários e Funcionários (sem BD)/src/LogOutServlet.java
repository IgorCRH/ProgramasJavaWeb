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
        // Obtenha a sessão existente, se houver
        HttpSession session = request.getSession(false);

        if (session != null) {
            // Invalida a sessão
            session.invalidate();
        }

        // Redirecione o usuário para a página de login ou qualquer outra página apropriada após o log out
        response.sendRedirect("login.jsp");
    }
}
