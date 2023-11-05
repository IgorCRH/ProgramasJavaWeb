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

        // Obt�m a sess�o existente ou cria uma nova
        HttpSession session = request.getSession();

        // Obt�m ou inicializa a lista de itens cadastrados pelos funcion�rios na sess�o
        @SuppressWarnings("unchecked")
        List<String> itensFuncionarios = (List<String>) session.getAttribute("itensFuncionarios");
        if (itensFuncionarios == null) {
            itensFuncionarios = new ArrayList<>();
        }

        // Adiciona o novo item � lista de itens cadastrados pelos funcion�rios
        itensFuncionarios.add(item + " (Quantidade: " + quantidade + ")");

        // Atualiza a lista na sess�o
        session.setAttribute("itensFuncionarios", itensFuncionarios);

        // Redireciona de volta para a p�gina de tela do funcion�rio
        response.sendRedirect("telainicialfuncionario.jsp");
        
    }

}
