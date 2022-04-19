import java.sql.*;
import java.util.Enumeration;

public class DatabaseConnectivity {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/Payroll_Services?useSSL=false";
        String userName = "root";
        String password = "Root@123";
        Connection con;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the calsspath!",e);
        }
        listDrivers();
        try {
            System.out.println("Connecting to database: " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!!!!!" + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println("   "+driverClass.getClass().getName());
        }
    }
}
