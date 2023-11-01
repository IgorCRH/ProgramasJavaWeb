import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    // Configurações do banco de dados
    private static final String URL = "jdbc:mysql://localhost:8080/estoquesistema"; // URL de conexão
    private static final String USUARIO = "lar"; // Nome de usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados

    // Método para obter uma conexão com o banco de dados
    public static Connection obterConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC
            return DriverManager.getConnection(URL, USUARIO, SENHA); // Estabelece a conexão
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null; // Tratamento de exceções, você pode personalizar isso de acordo com suas necessidades
        }
    }

    // Método para fechar uma conexão com o banco de dados
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}