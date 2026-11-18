package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EsportDAO implements DAO<Esport> {
    private final Connection conn;

    public EsportDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Esport item) {
        String sql = "INSERT INTO deportes (nombre) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error afegint l'esport: " + e.getMessage());
        }
    }

    @Override
    public List<Esport> getAll() {
        List<Esport> result = new ArrayList<>();
        String sql = "SELECT cod, nombre FROM deportes ORDER BY cod";

        try (Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                result.add(new Esport(rs.getInt("cod"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.err.println("Error llistant els esports: " + e.getMessage());
        }

        return result;
    }
}
