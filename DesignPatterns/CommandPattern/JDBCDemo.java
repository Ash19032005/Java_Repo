import java.sql.*;
public class JDBCDemo{
    public static void main(String[] args) {
        try {
            // Class.forName("org.postgresql.Driver");
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:8000/TEST","","");
            Statement stmt=con.createStatement();
            ResultSet res=stmt.executeQuery("select* from employee");

            while(res.hasNext()){
                res.next();
                System.out.println(res.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}