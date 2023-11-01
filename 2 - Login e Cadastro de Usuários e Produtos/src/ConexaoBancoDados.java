import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBancoDados {
    // Configura��es do banco de dados
    private static final String URL = "jdbc:mysql://localhost:8080/estoquesistema"; // URL de conex�o
    private static final String USUARIO = "lar"; // Nome de usu�rio do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados

    // M�todo para obter uma conex�o com o banco de dados
    public static Connection obterConexao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver JDBC
            return DriverManager.getConnection(URL, USUARIO, SENHA); // Estabelece a conex�o
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null; // Tratamento de exce��es, voc� pode personalizar isso de acordo com suas necessidades
        }
    }

    // M�todo para fechar uma conex�o com o banco de dados
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