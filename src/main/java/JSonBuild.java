import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.Properties;

import static spark.Spark.halt;

/**
 * Created by I343516 on 05/09/2017.
 */
public class JSonBuild {
    public static JsonObject buildJsonEmployee(ResultSet rs, Properties prop) {
        try {
            if (rs.isBeforeFirst()) {
                JsonObject jobj = new JsonObject();
                while (rs.next()) {
                    jobj.addProperty(prop.getProperty("JSON_ID_FORMAT"), rs.getInt(prop.getProperty("COLUMN_ID_LABEL")));
                    jobj.addProperty(prop.getProperty("JSON_NAME_FORMAT"), rs.getString(prop.getProperty("COLUMN_NAME_LABEL")));
                    jobj.addProperty(prop.getProperty("JSON_DEPARTMENT_FORMAT"), rs.getString(prop.getProperty("COLUMN_DEP_LABEL")));
                }
                return jobj;
            } else {
                halt();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String buildStringDepartment(ResultSet rs, Properties prop) {
        try {
            StringBuilder toReturn = new StringBuilder("{ \"departments\":[");
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    toReturn.append(rs.getInt((prop.getProperty("COLUMN_ID_LABEL"))));
                    toReturn.append(",");
                }
                toReturn.delete(toReturn.length() - 1, toReturn.length());
                toReturn.append("]}");
                return toReturn.toString();
            } else {
                halt();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject buildJsonDepartments(ResultSet rs, Properties prop) {
        try {
            JsonObject jsonObject = new JsonObject();
            StringBuilder stringBuilder = new StringBuilder("");
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    stringBuilder.append(rs.getString(prop.getProperty("COLUMN_ID_LABEL")));
                    stringBuilder.append(",");
                }
            } else {
                halt();
                return null;
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            jsonObject.addProperty(prop.getProperty("DEPARTMENTS_LABEL"), stringBuilder.toString());
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject buildJsonDepartment(ResultSet rs, Properties prop) {
        try {
            final InputStream input = new FileInputStream("config.properties");
            prop.load(input);
            JsonObject jobj = new JsonObject();
            JsonArray jsonArray = new JsonArray();
            if (rs.isBeforeFirst()) {
                if (rs.next())
                {
                    jobj.addProperty(prop.getProperty("DEPARTMENT_LABEL"), rs.getString(prop.getProperty("COLUMN_DEP_LABEL")));
                    do {
                        JsonObject tempJobj = new JsonObject();
                        tempJobj.addProperty(prop.getProperty("JSON_ID_FORMAT"), rs.getInt(prop.getProperty("COLUMN_ID_LABEL")));
                        tempJobj.addProperty(prop.getProperty("JSON_NAME_FORMAT"), rs.getString(prop.getProperty("COLUMN_NAME_LABEL")));
                        jsonArray.add(tempJobj);
                    } while (rs.next());
                }
            } else {
                halt();
                return null;
            }
            jobj.add(prop.getProperty("EMPLOYEES_LABEL"), jsonArray);
            return jobj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
