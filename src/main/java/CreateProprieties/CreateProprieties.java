package CreateProprieties;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class CreateProprieties {
    public static void main(String[] args) {

        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.properties");

            // set the properties value
            prop.setProperty("FILE_NAME","config.properties");
            prop.setProperty("DATABASE_URL", "jdbc:mysql://localhost/emp?useSSL=true");
            prop.setProperty("DB_USER", "root");
            prop.setProperty("DB_PASSWORD", "parolaDB13");
            prop.setProperty("EXTERNAL_DB_QUERY", "SELECT a.id, a.name AS empName, a.dep_id, b.name AS departmentName FROM employee a LEFT JOIN department b ON (a.dep_id = b.id) WHERE a.id=?");
            prop.setProperty("INTERNAL_DB_QUERY", "SELECT * from employees WHERE id=?");
            prop.setProperty("JSON_ID_FORMAT", "employeeId");
            prop.setProperty("JSON_NAME_FORMAT", "employeeName");
            prop.setProperty("JSON_DEPARTMENT_FORMAT", "employeeDepartment");
            prop.setProperty("COLUMN_ID_LABEL", "id");
            prop.setProperty("COLUMN_NAME_LABEL", "empName");
            prop.setProperty("COLUMN_DEP_LABEL", "departmentName");
            prop.setProperty("GET_ALL_DEPS_QUERY", "Select id from department");
            prop.setProperty("GET_ALL_DEPS_IDS_QUERY", "Select id from department");
            prop.setProperty("GET_DEP_QUERY", "Select a.name AS departmentName, b.id, b.name AS empName FROM department a LEFT JOIN employee b ON (a.id=b.dep_id) WHERE a.id=?");
            prop.setProperty("DEPARTMENTS_LABEL", "departments");
            prop.setProperty("DEPARTMENT_LABEL", "departmentName");
            prop.setProperty("EMPLOYEES_LABEL", "employees");
            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}