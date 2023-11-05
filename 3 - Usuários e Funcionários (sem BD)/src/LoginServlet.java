import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tarefa.Usuario;
import tarefa.Funcionario;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Recupere a lista de usuários e funcionários cadastrados da sessão
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
        @SuppressWarnings("unchecked")
        List<Funcionario> funcionarios = (List<Funcionario>) session.getAttribute("funcionarios");

        // Verificar se a lista de usuários existe e não está vazia
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                    // As credenciais correspondem a um usuário cadastrado
                    session.setAttribute("role", usuario.getRole());

                    if ("Usuario".equals(usuario.getRole())) {
                        // Redirecionar para a página de compra se for um usuário
                        response.sendRedirect("compra.jsp");
                        return; // Sai do método após o redirecionamento
                    }
                }
            }
        }

        // Verificar se a lista de funcionários existe e não está vazia
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getUsername().equals(username) && funcionario.getPassword().equals(password)) {
                    // As credenciais correspondem a um funcionário cadastrado
                    session.setAttribute("role", funcionario.getRole());

                    if ("Funcionario".equals(funcionario.getRole())) {
                        // Redirecionar para a página do funcionário se for um funcionário
                        response.sendRedirect("telainicialfuncionario.jsp");
                        return; // Sai do método após o redirecionamento
                    }
                }
            }
        }

        // Redirecionar de volta para a página de login em caso de falha na autenticação
        response.sendRedirect("login.jsp");
    }
 
}
