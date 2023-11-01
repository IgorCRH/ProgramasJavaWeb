import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import conexao.ConexaoBancoDados;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("login");
        String senha = request.getParameter("senha");

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obter uma conex�o com o banco de dados usando a classe de utilit�rio
            conn = ConexaoBancoDados.obterConexao();

            // Consultar o banco de dados para verificar as credenciais do usu�rio
            String sql = "SELECT * FROM usuarios WHERE login = ? AND senha = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Se as credenciais forem v�lidas, redirecione para a p�gina principal
                response.sendRedirect("paginaprincipal.jsp");
            } else {
                // Caso contr�rio, redirecione de volta para a p�gina de login com uma mensagem de erro
                request.setAttribute("mensagemErro", "Usu�rio ou senha inv�lidos");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            // Lidar com erros de conex�o com o banco de dados
            e.printStackTrace();
            // Redirecionar para uma p�gina de erro apropriada
            response.sendRedirect("erro.jsp");
        } finally {
            // Fechar recursos
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                // Feche a conex�o usando o m�todo de fechamento da classe de utilit�rio
                ConexaoBancoDados.fecharConexao(conn);
            }
        }
    }
}