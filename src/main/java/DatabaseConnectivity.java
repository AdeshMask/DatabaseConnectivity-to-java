import java.sql.*;
import java.util.Enumeration;

public class DatabaseConnectivity {
    static Connection con = null;
    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/Employee_Payroll?useSSL=false";
        String userName = "root";
        String password = "Root@123";
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
        System.out.println("");
        System.out.println("/////////////////////////---After Update---///////////////////////////////");
        String updatequery = "update employee_payroll set Basic_Pay = ? where Employee_Name = ?";
        updateQuery(updatequery);
        System.out.println("Done \n");
        String search = "select * from employee_payroll where Employee_Name =?";
        retriveBYName(search);
        String fromDate = "SELECT * FROM employee_payroll WHERE Start_Date > ? ";
        fromPerticularDate(fromDate);
        System.out.print("\nMale salary sum:");
        String maleSum = "SELECT SUM(Salary) FROM employee_payroll WHERE gender = 'M' GROUP BY gender ";
        simpleCalculations(maleSum);
        System.out.print("\nFemale Salary sum:");
        String feMaleSum = "SELECT SUM(Salary) FROM employee_payroll WHERE gender = 'M' GROUP BY gender ";
        simpleCalculations(feMaleSum);
        System.out.print("\nAverage of male salary:");
        String maleAvg = "select avg(Salary) from employee_payroll where Gender = 'M' group by Gender;";
        simpleCalculations(maleAvg);
        System.out.print("\nAverage of Female salary");
        String femaleAvg = "select avg(Salary) from employee_payroll where Gender = 'M' group by Gender;";
        simpleCalculations(femaleAvg);
        System.out.print("\nMin of male salary:");
        String maleMin = "select min(Salary) from employee_payroll where Gender = 'M' group by Gender;";
        simpleCalculations(maleMin);
        System.out.print("\nMax of Female salary");
        String femaleMax = "select max(Salary) from employee_payroll where Gender = 'M' group by Gender;";
        simpleCalculations(femaleMax);
        System.out.print("\nCount of male salary: ");
        String maleCount = "select count(*) from employee_payroll where Gender = 'M'";
        simpleCalculations(maleCount);
        System.out.print("\ncount of Female salary: ");
        String femaleCount = "select count(*) from employee_payroll where Gender = 'F';";
        simpleCalculations(femaleCount);
    }

    private static void simpleCalculations(String sum) throws SQLException {
        float value = 0.0f;
        PreparedStatement preparedStatement = con.prepareStatement(sum);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String result = resultSet.getString(1);
        System.out.print(result);
    }

    private static void fromPerticularDate(String fromDate) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(fromDate);
        System.out.println("Searching records from particular date:");
        preparedStatement.setString(1, "2021-09-15");
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
        {
            do{
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+
                        resultSet.getString(3)+" "+resultSet.getString(4)+" "+
                        resultSet.getString(5)+" "+resultSet.getString(6)+" "+
                        resultSet.getString(7)+" "+resultSet.getString(8)+" "+
                        resultSet.getString(9)+" "+resultSet.getString(10)+" "+
                        resultSet.getString(11)+" "+resultSet.getString(12)+" "+
                        resultSet.getString(13)+" "+resultSet.getString(14));
            }while(resultSet.next());
        }
        else
        {
            System.out.println("Record Not Found...");
        }
    }

    private static void retriveBYName(String search) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(search);
        preparedStatement.setString(1, "Adesh");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            System.out.println("Record Found :==>>");
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+
                    resultSet.getString(3)+" "+resultSet.getString(4)+" "+
                    resultSet.getString(5)+" "+resultSet.getString(6)+" "+
                    resultSet.getString(7)+" "+resultSet.getString(8)+" "+
                    resultSet.getString(9)+" "+resultSet.getString(10)+" "+
                    resultSet.getString(11)+" "+resultSet.getString(12)+" "+
                    resultSet.getString(13)+" "+resultSet.getString(14)+"\n");
        }
        else System.out.println("Record not Found:");
    }

    public static String updateQuery(String updatequery) throws SQLException {
        System.out.println("\nUpdate Query");
        PreparedStatement preparedStatement = con.prepareStatement(updatequery);
        preparedStatement.setInt(1, (int) 3000000.0);
        preparedStatement.setString(2,"Teresa");
        preparedStatement.executeUpdate();
        String updatedquery = "select * from employee_payroll where Employee_Name = ?";
        PreparedStatement preparedStatement1 = con.prepareStatement(updatedquery);
        ResultSet resultSet1 = preparedStatement1.executeQuery("select * from employee_payroll");
        showRecord(resultSet1);
        return updatedquery;
    }

    static void listDrivers(){
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()){
            Driver driverClass = driverList.nextElement();
            System.out.println("   "+ driverClass.getClass().getName());
        }
    }
    static void  showRecord(ResultSet resultSet) throws SQLException {
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
