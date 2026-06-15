// Prototype is a creational design pattern that creates new objects by cloning existing objects 
// instead of creating them from scratch. It is useful when object creation is expensive or when many 
// similar objects are required. The pattern typically provides a clone method that returns a copy of the existing object.
//  A common interview discussion point is the difference between shallow copy and deep copy.

interface Prototype {
    Prototype clone();
}

class ConcretePrototype implements Prototype{
    private String name;
    public ConcretePrototype(String name) {
        this.name = name;
    } 

    @Override
    public ConcretePrototype clone(){
        return new ConcretePrototype(this.name);
    }

    public String getName() {
        return name;
    }

}

// client
public class PrototypePattern{
    public static void main(String[] args){
        ConcretePrototype original=new ConcretePrototype("Original Object");
        ConcretePrototype clone=original.clone();
        System.out.println("Original: "+original.getName());
        System.out.println("Clone: "+clone.getName());
        System.out.println("Are they the same object? "+(original==clone));
    }
}


// Shallow copy: Here, the clone method in the Employee class creates a shallow copy of the Employee object.
// class Address {
//     String city;

//     Address(String city) {
//         this.city = city;
//     }
// }

// class Employee {

//     String name;
//     Address address;

//     Employee(String name, Address address) {
//         this.name = name;
//         this.address = address;
//     }

//     public Employee clone() {
//         return new Employee(
//             this.name,
//             this.address
//         );
//     }
// }
// clone.address=oridinal.address; // This means that both the original and cloned Employee objects share the same Address object.




// Deep Copy: In contrast, a deep copy creates a new instance of the Address object as well, ensuring that the cloned Employee has its own copy of the Address.
// class Employee {

//     String name;
//     Address address;

//     Employee(String name, Address address) {
//         this.name = name;
//         this.address = address;
//     }

//     public Employee clone() {
//         return new Employee(
//             this.name,
//             new Address(this.address.city)
//         );
//     }
// }
// clone.address!=original.address; // This means that the original and cloned Employee objects have separate Address objects, and changes to one will not affect the other.



 
