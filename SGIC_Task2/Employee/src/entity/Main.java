package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Parse XML and filter employees
        List<Employee> filteredEmployees = XMLParser.parseAndFilterEmployees("C:\\Users\\rajes\\eclipse-workspace\\Employee\\src\\entity/employees.xml");
        

        // Connect to MySQL database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee", "root", "1234")) {
            // Insert filtered employees into database
            for (Employee employee : filteredEmployees) {
                String sql = "INSERT INTO Employee (id, name, position, department) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, employee.getId());
                    statement.setString(2, employee.getName());
                    statement.setString(3, employee.getPosition());
                    statement.setString(4, employee.getDepartment());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
