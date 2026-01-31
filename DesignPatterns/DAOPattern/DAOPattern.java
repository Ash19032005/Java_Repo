// DAO Pattern (Data Access Object Pattern) in Java is a design pattern 
// used to separate the data access logic from business logic in an application.
// In simple words:DAO acts as a bridge between your Java application and the database.
// You divide your code into layers:
// 1. Model / Entity Layer – Java classes representing DB tables
// 2. DAO Layer – Handles database operations (CRUD)
// 3. Service / Business Layer – Contains business logic
// 4. Controller / UI Layer – Handles user requests


import java.sql.*;
import java.util.*;

// Model
class Student{
    private int id;
    private String name;
    private String email;
    Student(int id,String name,String email){
        this.id=id;
        this.name=name;
        this.email=email;
    }

    // getters and setters
    public int getId(){
        return id;
    }
    public void setId(int id){ this.id=id;}
    public String getName(){
        return name;
    }
    public void setName(String name){ this.name=name;}
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){ this.email=email;}

    @Override
    public String toString() {
    return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }

}


// DB Connection using singleton class
class ConnectDB{
    static Connection dbInstance;
    public static Connection getInstance(){
        if(dbInstance==null){
            try {
                Connection dbInstance=DriverManager.getConnection("jdbc:postgresql://localhost:8000/TEST","postgres","2005");
                return dbInstance;
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
        return dbInstance;
    }
}

// Data Access layer
interface StudentDAO{
    void addStudent(Student student);
    Student getStudentById(int id);
    List<Student> getStudents();
    void UpdateStudentById(Student student,int id);
    void deleteStudentByID(int id);
}

class StudentDAOImpl implements StudentDAO{
    @Override
    public void addStudent(Student student){
        String sql="INSERT INTO student values(?,?,?)";
        try{
                Connection con=ConnectDB.getInstance();
                PreparedStatement pstmt= con.prepareStatement(sql);
                pstmt.setInt(1,student.getId());
                pstmt.setString(2,student.getName());
                pstmt.setString(3,student.getEmail()); 
                pstmt.executeUpdate();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }


    @Override
    public Student getStudentById(int id) {
        String sql="SELECT * FROM STUDENT where id=?";
        Student std=null;
        try {
            Connection con=ConnectDB.getInstance();
            PreparedStatement pstmt= con.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                std=new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return std;
    }

    @Override
    public List<Student> getStudents() {
        String sql="SELECT * FROM STUDENT";
        List<Student> StudentList=new ArrayList<>();
        Student std=null;
        try {
            Connection con=ConnectDB.getInstance();
            PreparedStatement pstmt= con.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                std=new Student(rs.getInt("id"),rs.getString("name"),rs.getString("email"));
                StudentList.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return StudentList;
    }

    @Override
    public void UpdateStudentById(Student student, int id) {
        String sql="UPDATE student SET name=? , email=? WHERE id=?";
        try {
            Connection con=ConnectDB.getInstance();
            PreparedStatement pstmt= con.prepareStatement(sql);
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void deleteStudentByID(int id) {
       String sql="DELETE FROM student WHERE id=?";
       try {
            Connection con=ConnectDB.getInstance();
            PreparedStatement pstmt= con.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
       } catch (Exception e) {
            e.printStackTrace();
       }

    }
}


// Business logic layer
class StudentService{
    private StudentDAO stdDAO=new StudentDAOImpl();
    public void registerStudent(Student student){
        if (student == null ||
            student.getName() == null ||
            student.getEmail() == null ||
            !student.getEmail().contains("@")) {

                throw new IllegalArgumentException("Invalid student data");
        }
        stdDAO.addStudent(student);
    }   

    public void getStudentById(int id){
            Student list=stdDAO.getStudentById(id);
            System.out.println(list);
    }

    public void getStudents(){
        List<Student> StudentList=stdDAO.getStudents();
        for(Student i:StudentList){
            System.out.println(i);
        }
    }
    public void deleteStudentByID(int id){
        stdDAO.deleteStudentByID(id);
    }
    public void updateStudent(Student student,int id){
        stdDAO.UpdateStudentById(student, id);
    }
}

// UI layer
public class DAOPattern{
    public static void main(String[] args) {
        StudentService stdService=new StudentService();
        System.out.println("Enter the choice 1. Add Student 2. Update Student by ID 3. Delete Student by ID 4. Get Student by ID 5. Get Students ");
        Scanner ip=new Scanner(System.in);
        int choice=ip.nextInt();
        switch(choice){
            case 1: 
                stdService.registerStudent(new Student(1,"Ashwin","ashwin123@gmail.com")); 
                break;
            case 2:
                stdService.updateStudent(new Student(1,"Akash","akash123@gmail.com"), choice);
                break;
            case 3:
                stdService.deleteStudentByID(1);
                break;
            case 4:
                stdService.getStudentById(1);
                break; 
            case 5:
                stdService.getStudents();
        }
    }
}
