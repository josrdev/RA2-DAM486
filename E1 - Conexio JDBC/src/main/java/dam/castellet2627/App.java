
package dam.castellet2627;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        // Cadena de connexió JDBC
        String url = "jdbc:postgresql://localhost:5432/sports";
        String user = "admin@admin.com";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if (conn != null) {
                System.out.println("Connexió establerta amb èxit a PostgreSQL!");

                String sql = "SELECT d.COD, d.NOMBRE AS deportista " +
                        "FROM DEPORTISTAS d ";
                PreparedStatement pstmt = conn.prepareStatement(sql);

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    // bucle per recórrer els resultats de la consulta
                    String cod = rs.getString("COD");
                    String nombre = rs.getString("deportista");
                    System.out.println("COD: " + cod + ", Deportista: " + nombre);
                }

            }
        } catch (SQLException e) {
            System.err.println("Error de connexió: " + e.getMessage());
        }

    }
}
