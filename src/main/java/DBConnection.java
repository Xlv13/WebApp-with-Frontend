import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by I343516 on 06/09/2017.
 */
public class DBConnection {

    public static Connection getConnectionToDB(String DB_URL, String USER, String PASSWORD) {
        try {
            return DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
