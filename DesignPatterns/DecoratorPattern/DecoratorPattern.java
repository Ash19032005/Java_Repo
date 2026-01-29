// The Decorator Pattern is a structural design pattern that allows you to dynamically 
// add new responsibilities or behaviors to an object at runtime by wrapping it with
//  decorator objects, without modifying the original class.


// Component interface
interface Coffee{
    String getDescription();
    int getCost();
}

// Concrete component
class SimpleCoffee implements Coffee{
    @Override
    public String getDescription(){
            return "Just a Simple Coffee";
    }

    @Override
    public int getCost(){
        return 50;
    }

}


// Decorator
abstract class CoffeeDecorator implements Coffee{
    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee=coffee;
    }
    
}

// Decorator Component
class SugarDecorator extends CoffeeDecorator{
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription(){
            return "Coffee added with extra sugar";
    }

    public int getCost(){
        return coffee.getCost()+50;    
    }
}

class MilkDecorator extends CoffeeDecorator{
    public MilkDecorator(Coffee coffee){
        super(coffee);
    }
    public String getDescription(){
            return "Coffee added with extra milk";
    }

    public int getCost(){
        return coffee.getCost()+20;    
    }
}
public class DecoratorPattern{
    public static void main(String[] args) {
        SimpleCoffee cf=new SimpleCoffee();
        System.out.println("Simple coffee");
        System.out.println(cf.getDescription());
        System.out.println(cf.getCost());

        MilkDecorator md=new MilkDecorator(cf);
        SugarDecorator sd=new SugarDecorator(cf);
        System.out.println("Milk decorator");
        System.out.println(md.getDescription());
        System.out.println(md.getCost());


    }
}
