package dam.m6.uf2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtletaDAO implements DAO<Atleta> {
    private final Connection conn;

    public AtletaDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(Atleta item) {
        String sql = "INSERT INTO deportistas (nombre, cod_deporte) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setInt(2, item.getSportId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error afegint l'atleta: " + e.getMessage());
        }
    }

    @Override
    public List<Atleta> getAll() {
        List<Atleta> result = new ArrayList<>();
        String sql = "SELECT cod, nombre, cod_deporte FROM deportistas ORDER BY cod";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(new Atleta(
                        rs.getInt("cod"),
                        rs.getString("nombre"),
                        rs.getInt("cod_deporte")));
            }
        } catch (SQLException e) {
            System.err.println("Error llistant els atletes: " + e.getMessage());
        }

        return result;
    }

    public List<Atleta> getBySport(int sportId) {
        List<Atleta> result = new ArrayList<>();
        String sql = "SELECT cod, nombre, cod_deporte FROM deportistas "
                + "WHERE cod_deporte = ? ORDER BY cod";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sportId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(new Atleta(
                            rs.getInt("cod"),
                            rs.getString("nombre"),
                            rs.getInt("cod_deporte")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error llistant els atletes: " + e.getMessage());
        }

        return result;
    }
}
