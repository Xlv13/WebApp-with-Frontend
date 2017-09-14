import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import static spark.Spark.get;

/**
 * Created by I343516 on 04/09/2017.
 */
public class Client {

    private static Connection conn;
    private static final Properties prop = new Properties();
    public static void main(String[] args) {
        try {
            InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            conn = DBConnection.getConnectionToDB(prop.getProperty("DATABASE_URL"), prop.getProperty("DB_USER"), prop.getProperty("DB_PASSWORD"));

            get("/employee/:id", (req, res) -> WebApp.getEmployee(prop.getProperty("EXTERNAL_DB_QUERY"), conn,prop,req.params(":id")));
            get("/departments", (req, res) -> WebApp.getDepartmentsIDs(prop.getProperty("GET_ALL_DEPS_QUERY") + "", conn,prop));
            get("/department/:id", (req, res) -> WebApp.getDepartment(prop.getProperty("GET_DEP_QUERY") , conn,prop,req.params(":id")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}