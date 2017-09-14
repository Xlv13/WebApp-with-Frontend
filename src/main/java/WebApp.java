import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Created by I343516 on 04/09/2017.
 */
public class WebApp {

    public static JsonObject getEmployee(String QUERY, Connection conn, Properties prop,String parameter) {
        final ResultSet rs = executeQueryOnDB(conn, QUERY,parameter);
        return JSonBuild.buildJsonEmployee(rs,prop);
    }

    public static String getDepartmentsIDs(String QUERY, Connection  conn, Properties prop) {
        final ResultSet rs = executeQueryOnDB(conn, QUERY,null);
        return JSonBuild.buildStringDepartment(rs,prop);
    }

    private static ResultSet executeQueryOnDB(Connection conn, String QUERY,String parameter) {
        try {
            final PreparedStatement stmt = conn.prepareStatement(QUERY);
            if(parameter!=null) {
                stmt.setString(1, parameter);
            }
            return stmt.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject getDepartment(String QUERY, Connection conn, Properties prop,String parameter) {
        final ResultSet rs = executeQueryOnDB(conn, QUERY,parameter);
        return JSonBuild.buildJsonDepartment(rs,prop);
    }
}
