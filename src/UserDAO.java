import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private String url;
    private String user;
    private String password;
    
    public UserDAO(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }
    
    public void addUser(User user) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, this.user, password);
            String sql = "insert into user(name,email,password) values(?,?,md5(?))";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
