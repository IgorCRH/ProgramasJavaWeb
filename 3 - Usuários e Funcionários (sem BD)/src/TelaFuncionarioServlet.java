import java.io.IOException;
import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/telafuncionario")
public class TelaFuncionarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String item = request.getParameter("item");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));

        // Obtém a sessão existente ou cria uma nova
        HttpSession session = request.getSession();

        // Obtém ou inicializa a lista de itens cadastrados pelos funcionários na sessão
        @SuppressWarnings("unchecked")
        List<String> itensFuncionarios = (List<String>) session.getAttribute("itensFuncionarios");
        if (itensFuncionarios == null) {
            itensFuncionarios = new ArrayList<>();
        }

        // Adiciona o novo item à lista de itens cadastrados pelos funcionários
        itensFuncionarios.add(item + " (Quantidade: " + quantidade + ")");

        // Atualiza a lista na sessão
        session.setAttribute("itensFuncionarios", itensFuncionarios);

        // Redireciona de volta para a página de tela do funcionário
        response.sendRedirect("telainicialfuncionario.jsp");
        
    }

}
