import java.sql.*;

public class Employee {

    public static void main(String[] args) {

        Connection conn;
        PreparedStatement stmt;
        ResultSet rs;

        try {
            // Initialize the JDBC driver and get a database connection
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "username", "password");

            // Prepare the statement to retrieve employee data
            stmt = conn.prepareStatement("SELECT EmployeeID, EmployeeName, Designation FROM employee");

            // Execute the statement and get the result set
            rs = stmt.executeQuery();

            // Iterate through the result set and print the employee data
            while (rs.next()) {
                int employeeID = rs.getInt("EmployeeID");
                String employeeName = rs.getString("EmployeeName");
                String designation = rs.getString("Designation");

                System.out.println("Employee ID: " + employeeID);
                System.out.println("Employee Name: " + employeeName);
                System.out.println("Designation: " + designation);
            }

            // Close the result set, statement, and connection
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
