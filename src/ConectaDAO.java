import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDAO {
    public Connection connectDB() {
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/uc11_att2"; // Substituir pelo nome do seu banco
            String user = "root"; // Substituir pelo usuário do banco
            String password = "merxyx-tubvu2-nihNyr"; // Substituir pela senha do banco
            
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }
        return conn;
    }
}
