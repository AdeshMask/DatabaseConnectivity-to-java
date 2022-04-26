import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseConnectivityTest {

    static Connection con = null;
    @Test
    public void connecingToDatabase() {
        con  = DatabaseConnectivity.connecingToDatabase();
        String expected = String.valueOf(DatabaseConnectivity.connecingToDatabase());
        Assert.assertEquals("com.mysql.cj.jdbc.ConnectionImpl@5d47c63f",expected);
    }

    @Test
    public void simpleCalculations() throws SQLException {
        con  = DatabaseConnectivity.connecingToDatabase();
        String expected = DatabaseConnectivity.simpleCalculations(String.valueOf(con));
        Assert.assertEquals("70000.0",expected);
    }

    @Test
    public void fromPerticularDate() throws SQLException {
        con  = DatabaseConnectivity.connecingToDatabase();
        String expected = DatabaseConnectivity.fromPerticularDate(String.valueOf(con));
        Assert.assertEquals("2021-09-15",expected);
    }

    @Test
    public void retriveBYName() throws SQLException {
        con  = DatabaseConnectivity.connecingToDatabase();
        String expected = DatabaseConnectivity.retriveBYName(String.valueOf(con));
        Assert.assertEquals("Adesh",expected);
    }

    @Test
    public void updateQuery() throws SQLException {
        con  = DatabaseConnectivity.connecingToDatabase();
        String expected = DatabaseConnectivity.updateQuery(String.valueOf(con));
        Assert.assertEquals("3000000.0",expected);
    }
}