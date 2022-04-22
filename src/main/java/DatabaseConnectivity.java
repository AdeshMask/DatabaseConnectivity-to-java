import java.sql.*;
import java.util.Enumeration;

public class DatabaseConnectivity {
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/Payroll_Services?useSSL=false";
        String userName = "root";
        String password = "Root@123";
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the calsspath!",e);
        }

        try {
            System.out.println("Connecting to database: " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful!!!!!" + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        listDrivers();
        System.out.println("");
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from employee_payroll");
        System.out.println("Before Update==>>");
        showRecord(resultSet);
        System.out.println("After Update");
        String updatequery = "update employee_payroll set Basic_Pay = ? where Employee_Name = ?";
        PreparedStatement preparedStatement = con.prepareStatement(updatequery);
        preparedStatement.setInt(1,3000000);
        preparedStatement.setString(2,"Teresa");
        preparedStatement.executeUpdate();
        String updatedquery = "select * from employee_payroll where Employee_Name = ?";
        PreparedStatement preparedStatement1 = con.prepareStatement(updatedquery);
        ResultSet resultSet1 = preparedStatement1.executeQuery("select * from employee_payroll");
        showRecord(resultSet1);
    }
    static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println("   "+ driverClass.getClass().getName());
        }
    }
    private static void  showRecord(ResultSet resultSet) throws SQLException {
        while (resultSet.next()){
            System.out.print(resultSet.getInt("EmployeeID")+" ");
            System.out.print(resultSet.getString("Employee_Name")+" ");
            System.out.print(resultSet.getString("Department")+" ");
            System.out.print(resultSet.getLong("Phone_number")+" ");
            System.out.print(resultSet.getString("Address")+" ");
            System.out.print(resultSet.getString("Department")+" ");
            System.out.print(resultSet.getString("Gender")+" ");
            System.out.print(resultSet.getFloat("Basic_Pay")+" ");
            System.out.print(resultSet.getFloat("Deduction")+" ");
            System.out.print(resultSet.getFloat("Taxable_Pay")+" ");
            System.out.print(resultSet.getFloat("Tax")+" ");
            System.out.print(resultSet.getFloat("NetPay")+" ");
            System.out.print(resultSet.getDate("Start_Date")+" ");
            System.out.print(resultSet.getString("city")+" ");
            System.out.print(resultSet.getString("country")+" ");
            System.out.println("");
        }
    }
}
