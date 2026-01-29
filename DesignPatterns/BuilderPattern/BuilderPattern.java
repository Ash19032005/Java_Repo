// The Builder Pattern is a creational design pattern that separates
// the construction of a complex object from its representation, 
// allowing an object to be built step by step and enabling the creation of 
// different configurations without using multiple constructors.
// “Builder Pattern is used to construct complex objects step by step.
// It avoids constructor overloading and improves code readability.”

class User{
    private String name;
    private int age;
    private String email;
    private String address;
    private User(Builder builder){
        this.name=builder.name;
        this.age=builder.age;
        this.email=builder.email;
        this.address=builder.address;
        System.out.println("User created: "+this.age+this.name+this.email);
    }


    // This class need to be static
    static class Builder{
        private String name;
        private int age;
        private String email;
        private String address;

         Builder(String name,int age){
            this.name=name;
            this.age=age;
        }

        Builder SetEmail(String email){
            this.email=email;
            return this;
        }

        Builder setAddress(String address){
            this.address=address;
            return this;
        }

        User build(){
            return new User(this);
        } 
    }
}

public class BuilderPattern{
    public static void main(String[] args) {
        User user=new User.Builder("Ashwin",20)
                        .SetEmail("ashwinsk1905@gmail.com")
                        .build();
       
            
    }
}