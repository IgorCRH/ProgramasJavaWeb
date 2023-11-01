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

@WebServlet("/CadastroServlet")
public class CadastroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("resource")
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

            // Verificar se o usu�rio j� existe no banco de dados
            String verificaUsuarioSql = "SELECT * FROM usuarios WHERE login = ?";
            stmt = conn.prepareStatement(verificaUsuarioSql);
            stmt.setString(1, usuario);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // Se o usu�rio j� existir, redirecione de volta para a p�gina de cadastro com uma mensagem de erro
                request.setAttribute("mensagemErro", "Usu�rio j� existe");
                request.getRequestDispatcher("cadastro.jsp").forward(request, response);
            } else {
                // Caso contr�rio, insira o novo usu�rio no banco de dados
                String insereUsuarioSql = "INSERT INTO usuarios (login, senha) VALUES (?, ?)";
                stmt = conn.prepareStatement(insereUsuarioSql);
                stmt.setString(1, usuario);
                stmt.setString(2, senha);
                stmt.executeUpdate();

                // Redirecione para a p�gina de login ap�s o cadastro bem-sucedido
                response.sendRedirect("login.jsp");
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