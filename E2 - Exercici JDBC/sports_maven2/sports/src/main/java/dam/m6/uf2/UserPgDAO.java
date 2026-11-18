package dam.m6.uf2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class UserPgDAO implements DAO<SportsMan> {
    private Connection conn;

    public UserPgDAO(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void add(SportsMan item) {
        if (conn == null) {
            return;
        }

        String sql = "INSERT INTO deportistas (nombre, sport) VALUES (?, ?)";
        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, item.name);
            ps.setString(2, item.sport); // Assuming 'sport' is used as password for demonstration
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en inserir l'esportista: " + e.getMessage());
        }
    }

    @Override
    public List<SportsMan> getAll() {
        List<SportsMan> sportsMans = new ArrayList<>();

        String sql = "SELECT * FROM deportistas";
        try (java.sql.Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                String sport = rs.getString("sport"); // Assuming 'sport' is used as password for demonstration
                sportsMans.add(new SportsMan(name, sport));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar esportistas: " + e.getMessage());
        }

        return sportsMans;
    }

    public String callFunction(String p1, String p2) {
        String call = "{ ? = call get_user_name(?, ?) }";
        try (CallableStatement cs = conn.prepareCall(call)) {
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(2, p1);
            cs.setString(3, p2);
            cs.execute();
            return cs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean userExists(String name) {
        String sql = "SELECT 1 FROM deportistas WHERE nombre = ?";
        try (java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error comprovant esportista: " + e.getMessage());
            return false;
        }
    }
}
