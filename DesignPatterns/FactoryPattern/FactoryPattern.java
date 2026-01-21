// The Factory Pattern is a creational design pattern that provides a method 
// to create objects without exposing the object creation logic, and lets the subclass 
// or factory decide which class to instantiate based on input or conditions.

import java.util.*;
interface Pizza{
    abstract void bake();
}

class VegPizza implements Pizza{
    public void bake(){
        System.out.println("VegPizza is ready");
    }
} 

class ChickenPizza implements Pizza{
    public void bake(){
        System.out.println("ChickenPizza is ready");
        
    }
}

class Factory{
    public static Pizza getPizza(String str){
        if(str.equals("Veg")){
            return new VegPizza();
        }
        else if(str.equals("Chicken")){
            return new ChickenPizza();
        
        }
        else{
            return null;
        }
    }
}

public class FactoryPattern{
    public static void main(String args[]){
        Scanner ip=new Scanner(System.in);
        System.out.println("Enter the pizza you want:");
        String pizzaName=ip.next();
        Pizza pizza=Factory.getPizza(pizzaName);
        pizza.bake();
    }
}