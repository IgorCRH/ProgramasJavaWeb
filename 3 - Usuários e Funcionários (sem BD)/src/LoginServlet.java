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

        // Recupere a lista de usu�rios e funcion�rios cadastrados da sess�o
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
        List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuarios");
        @SuppressWarnings("unchecked")
        List<Funcionario> funcionarios = (List<Funcionario>) session.getAttribute("funcionarios");

        // Verificar se a lista de usu�rios existe e n�o est� vazia
        if (usuarios != null && !usuarios.isEmpty()) {
            for (Usuario usuario : usuarios) {
                if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
                    // As credenciais correspondem a um usu�rio cadastrado
                    session.setAttribute("role", usuario.getRole());

                    if ("Usuario".equals(usuario.getRole())) {
                        // Redirecionar para a p�gina de compra se for um usu�rio
                        response.sendRedirect("compra.jsp");
                        return; // Sai do m�todo ap�s o redirecionamento
                    }
                }
            }
        }

        // Verificar se a lista de funcion�rios existe e n�o est� vazia
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getUsername().equals(username) && funcionario.getPassword().equals(password)) {
                    // As credenciais correspondem a um funcion�rio cadastrado
                    session.setAttribute("role", funcionario.getRole());

                    if ("Funcionario".equals(funcionario.getRole())) {
                        // Redirecionar para a p�gina do funcion�rio se for um funcion�rio
                        response.sendRedirect("telainicialfuncionario.jsp");
                        return; // Sai do m�todo ap�s o redirecionamento
                    }
                }
            }
        }

        // Redirecionar de volta para a p�gina de login em caso de falha na autentica��o
        response.sendRedirect("login.jsp");
    }
 
}
