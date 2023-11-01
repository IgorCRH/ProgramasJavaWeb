import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para adicionar um produto
    public boolean adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (nome, quantidade, valor_unitario) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, produto.getNome());
            statement.setInt(2, produto.getQuantidade());
            statement.setBigDecimal(3, produto.getValorUnitario());

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para buscar um produto por ID
    public Produto buscarProdutoPorId(int id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Produto(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getInt("quantidade"),
                    resultSet.getBigDecimal("valor_unitario")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Método para atualizar a quantidade de um produto
    public boolean atualizarQuantidadeProduto(int id, int novaQuantidade) {
        String sql = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, novaQuantidade);
            statement.setInt(2, id);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para remover um produto por ID
    public boolean removerProduto(int id) {
        String sql = "DELETE FROM produtos WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            int linhasAfetadas = statement.executeUpdate();
            return linhasAfetadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                produtos.add(new Produto(
                    resultSet.getInt("id"),
                    resultSet.getString("nome"),
                    resultSet.getInt("quantidade"),
                    resultSet.getBigDecimal("valor_unitario")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }
}