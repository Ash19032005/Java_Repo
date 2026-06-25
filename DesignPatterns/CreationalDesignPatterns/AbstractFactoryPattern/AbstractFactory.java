// "A Factory Pattern creates one type of product, while an 
// Abstract Factory creates a family of related products. 
// In practice, Abstract Factory usually has multiple product interfaces 
// such as Button, Checkbox, and TextBox, whereas Factory typically deals with a single product interface."

import java.util.Scanner;
interface Pizza{
     void bake();
}
interface Drink{
     void pour();
}
interface Fry{
     void fry();
}


interface MealFactory{
    Pizza getPizza();
    Drink getDrink();
    Fry getFry();
} 

class VegPizza implements Pizza{
    @Override
    public void bake(){
        System.out.println("Veg Pizza is Ready");
    }
}
class ChickenPizza implements Pizza{
     @Override
    public void bake(){
        System.out.println("Chicken Pizza is Ready");
    }
}
class Coke implements Drink{
    @Override
    public void pour(){
        System.out.println("Coke is poured in the glass");
    }
}
class Pepsi implements Drink{
    @Override
    public void pour(){
        System.out.println("Pepsi is poured in the glass");
    }
}
class ChickenFry implements Fry{
    @Override
    public void fry(){
        System.out.println("Chicken Fry is ready");
    }
}

class PaneerFry implements Fry{
    @Override
    public void fry(){
        System.out.println("Paneer Fry is ready");
    }
}

class VegFactory implements MealFactory{
    public Pizza getPizza(){
        return new VegPizza();
    }
    public Drink getDrink(){
        return new Coke();
    }
    public Fry getFry(){
        return new PaneerFry();
    }
}

class ChickenFactory implements MealFactory{
     public Pizza getPizza(){
        return new ChickenPizza();
    }
    public Drink getDrink(){
        return new Pepsi();
    }
    public Fry getFry(){
        return new ChickenFry();
    }
}

class MealStore{
    private Pizza pizza;
    private Drink drink;
    private Fry fry;
    public MealStore(MealFactory factory){
        pizza=factory.getPizza();
        drink=factory.getDrink();
        fry=factory.getFry();
    }
    public void serve(){
        pizza.bake();
        drink.pour();
        fry.fry();
    }
    
}

class AbstractFactory{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter meal type (Veg/Chicken):");

        String choice = sc.next();

        MealFactory factory;

        if(choice.equalsIgnoreCase("Veg")) {
            factory = new VegFactory();
        }
        else {
            factory = new ChickenFactory();
        }
        MealStore store=new MealStore(factory);
        store.serve();
        
    }
}