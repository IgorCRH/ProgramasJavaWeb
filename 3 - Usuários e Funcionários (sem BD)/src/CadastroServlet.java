import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tarefa.Usuario;
import tarefa.Funcionario;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Verificar se a sess�o j� existe ou criar uma nova
        HttpSession session = request.getSession();

        // Verificar se as listas de usu�rios e funcion�rios j� existem na sess�o ou criar novas
        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
        if (usuarios == null) {
            usuarios = new ArrayList<>();
        }

        @SuppressWarnings("unchecked")
        List<Funcionario> funcionarios = (List<Funcionario>) session.getAttribute("funcionarios");
        if (funcionarios == null) {
            funcionarios = new ArrayList<>();
        }

        if ("Usuario".equals(role)) {
            // Criar um novo usu�rio
            Usuario novoUsuario = new Usuario(username, password, role);

            // Adicionar o novo usu�rio � lista de usu�rios
            usuarios.add(novoUsuario);
        } else if ("Funcionario".equals(role)) {
            // Criar um novo funcion�rio
            Funcionario novoFuncionario = new Funcionario(username, password, role);

            // Adicionar o novo funcion�rio � lista de funcion�rios
            funcionarios.add(novoFuncionario);
        }

        // Armazenar as listas de usu�rios e funcion�rios na sess�o
        session.setAttribute("usuarios", usuarios);
        session.setAttribute("funcionarios", funcionarios);

        // Redirecionar para a p�gina de sucesso ou qualquer outra p�gina apropriada
        response.sendRedirect("login.jsp");
    }
}
