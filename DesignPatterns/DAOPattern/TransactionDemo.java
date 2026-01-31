// Set Autocommiting 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

public class TransactionDemo{
    public static void main(String[] args) {
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:8000/TEST","postgres","2005");
            con.setAutoCommit(false);
            // id,designation, name,age
            PreparedStatement pstmt=con.prepareStatement("insert into Emp values(?,?,?,?)");
            for (int i = 1; i <= 5; i++) {

                System.out.println("Enter the id:");
                int id = Integer.parseInt(br.readLine());

                System.out.println("Enter the designation:");
                String designation = br.readLine();

                System.out.println("Enter the name:");
                String name = br.readLine();

                System.out.println("Enter the age:");
                int age = Integer.parseInt(br.readLine());

                pstmt.setInt(1, id);
                pstmt.setString(2, designation);
                pstmt.setString(3, name);
                pstmt.setInt(4, age);

                pstmt.executeUpdate();

                if(i==3){
                    con.rollback();
                } 
            }
            con.commit();
            pstmt.close();
                con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}