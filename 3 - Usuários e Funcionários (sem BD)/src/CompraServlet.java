import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/compra")
public class CompraServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("Usuario".equals(role)) {
            String itemComprado = request.getParameter("item");
            int quantidade = Integer.parseInt(request.getParameter("quantidade"));

            // Recupere a lista de itens comprados da sessão
            @SuppressWarnings("unchecked")
			List<String> itensComprados = (List<String>) session.getAttribute("itensComprados");

            if (itensComprados == null) {
                itensComprados = new ArrayList<>();
            }

            // Adicione o item comprado à lista
            for (int i = 0; i < quantidade; i++) {
                itensComprados.add(itemComprado);
            }

            // Atualize a lista na sessão
            session.setAttribute("itensComprados", itensComprados);

            RequestDispatcher dispatcher = request.getRequestDispatcher("listaitensusuario.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
