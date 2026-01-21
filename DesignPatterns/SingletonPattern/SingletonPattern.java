// Database connection

// “This is a Singleton Pattern.
// The constructor is private to prevent object creation.
// A static instance variable stores the single object.
// The static getInstance() method ensures only one object is created and reused.”

class Database{
    private static Database instance;
    private Database(){
        System.out.println("Database connected");
    }

    // lazy initialization
    public static synchronized Database getInstance(){
        if(instance==null){
            instance=new Database();
        }
        return instance;
    }
}

public class SingletonPattern{
    public static void main(String args[]){
        Database db1=Database.getInstance();
        Database db2=Database.getInstance();
        System.out.println(db1==db2);

    }
}