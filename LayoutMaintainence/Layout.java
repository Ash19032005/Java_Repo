// Layout Maintenance Application
// ---------------------------------------
// Types -  Villa, Apartment, Independent House, Open Site
// Total Sites - 35 Sites
// First 10 sites are of 40x60 ft size
// Next 10 sites are of 30x50 ft size
// Last 15 sites are of 30x40 ft size
// Open sites are charged 6Rs/sqft
// Occupied sites are charged 9Rs./sqft

// Admin	- 
// 	Can add/edit/remove the owner details and site details
// 	Can collect the maintenance and update
// 	Can see the pending details of all sites or the specific site
// 	Can approve/reject the site owners update about their own sites
// Site Owner -
// 	Can only see/update the details of his/her own site (but should be approved by Admin)
// Use this template to connect with database
// java -cp "C:\Users\Wissen\Downloads\postgresql-42.7.5.jar;." TransactionDemo
import java.sql.*;
import java.util.*;

// DB singleton class
class ConnectDB {

    private static Connection dbInstance = null;

    private ConnectDB() {
        // private constructor to prevent object creation
    }

    public static Connection getInstance() {
        try {
            if (dbInstance == null || dbInstance.isClosed()) {
                dbInstance = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:8000/TEST",
                        "postgres",
                        "2005"
                );
                System.out.println("Database connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbInstance;
    }
}

class Operations {

    public void add() {
        Scanner ip = new Scanner(System.in);
        String sql = "INSERT INTO layout values(?,?,?,?,?)";
        try {
            Connection con = ConnectDB.getInstance();
            if (con == null) {
                System.out.println("Connection failed!");
                return;
            }
            PreparedStatement pstmt = con.prepareStatement(sql);
            int id = ip.nextInt();
            String role = ip.next();
            String siteType = ip.next();
            int siteSize = ip.nextInt();
            int cost = ip.nextInt();
            pstmt.setInt(1, id);
            pstmt.setString(2, role);
            pstmt.setString(3, siteType);
            pstmt.setInt(4, siteSize);
            pstmt.setInt(5, cost);
            pstmt.executeUpdate();
            con.close();
            System.out.println("Entry Added");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void edit() {
        Scanner ip = new Scanner(System.in);

        System.out.println("Enter the site id to update:");
        int id = ip.nextInt();

        System.out.println("Enter new site type (OPEN / OCCUPIED):");
        String siteType = ip.next();

        System.out.println("Enter new site size:");
        int siteSize = ip.nextInt();

        System.out.println("Enter new cost per sqft:");
        int cost = ip.nextInt();

        String sql = "UPDATE layout SET siteType=?, siteSize=?, cost=? WHERE id=?";

        try {
            Connection con = ConnectDB.getInstance();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, siteType);
            pstmt.setInt(2, siteSize);
            pstmt.setInt(3, cost);
            pstmt.setInt(4, id);

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("No record found with given id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(int houseNumber) {
        Scanner ip = new Scanner(System.in);
        String sql = "delete from layout where id=?";
        try {
            Connection con = ConnectDB.getInstance();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, houseNumber);
            pstmt.execute();
            System.out.println("Entry Deleted");
        } catch (Exception e) {
            System.out.println("Entry is not deleted");
        }

    }

    public int collect(int houseNumber) {
        String sql = "SELECT sitesize, cost FROM layout WHERE id = ?";

        try {
            Connection con = ConnectDB.getInstance();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, houseNumber);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("sitesize") * rs.getInt("cost");
            } else {
                System.out.println("Id not matched");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}

class Admin {

    Scanner ip;

    Admin(Scanner ip) {
        this.ip = ip;
        System.out.println("Enter the action to perform add/edit/remove/collect the maintainence:");
        String action = ip.next();
        Operations op = new Operations();
        switch (action) {
            case "add":
                op.add();
                break;
            case "edit":
                op.edit();
                break;
            case "remove":
                System.out.println("Enter the house number to remove from the layout:");
                int hNumber = ip.nextInt();
                op.remove(hNumber);
                break;
            case "collect":
                System.out.println("Enter the house number to collect the maintainence cost:");
                int houseNumber = ip.nextInt();
                System.out.println("Maintainence cost: " + op.collect(houseNumber));
                break;
            default:
                System.out.println("Method not matched");
        }
    }

    public static boolean getPermission(int id) {
        String sql = "select id from users";
        try {
            Connection con = ConnectDB.getInstance();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                int dbId = res.getInt("id");
                if (dbId == id) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Id not matched");
        }
        return false;
    }
}

class User {

    Scanner ip;

    User(Scanner ip) {
        this.ip = ip;
        System.out.println("Enter the house Number:");
        int houseNumber = ip.nextInt();
        System.out.println("Enter the action to perform update/collect the maintainence:");
        String action = ip.next();
        Operations op = new Operations();
        switch (action) {
            case "collect":
                System.out.println("Maintainence cost: " + op.collect(houseNumber));
                break;
            case "update":
                boolean res = Admin.getPermission(houseNumber);
                if (res) {
                    op.edit();
                } else {
                    System.out.println("Request to update is denied");
                }
            default:
                System.out.println("Method not matched");
        }
    }
}

public class Layout {

    static Scanner ip = new Scanner(System.in);
    static Connection dbInstance;
    public static void main(String[] args) {
        System.out.println("Enter the role user/admin:");
        String choice = ip.next();
        if (choice.equals("user")) {
            User user = new User(ip);
        } else if (choice.equals("admin")) {
            Admin ad = new Admin(ip);
        } else {
            System.out.println("Not authorized one");
        }

    }

}
