// The Strategy Pattern is a behavioral design pattern that defines a family of algorithms, 
// encapsulates each algorithm in a separate class, and makes them interchangeable at runtime, 
// allowing the behavior of an object to change without modifying its code.

import java.util.*;
interface Payment{
    void payment(int amount);
}

class CreditPayment implements Payment{
    public void payment(int amt){
        System.out.println("Credit Payment done:"+ amt);
    }
}

class UpiPayment implements Payment{
    public void payment(int amt){
        System.out.println("Upi Payment done: "+ amt);
    }
}

class ShoppingCart{
    private Payment paymentStrategy;
    int amt;
    public ShoppingCart(int amt,Payment paymentStrategy) {
        this.amt=amt;
        this.paymentStrategy=paymentStrategy;
    }
    public void MakePayment(){
        paymentStrategy.payment(amt);
    }
}

public class StrategyPattern{
    public static void main(String[] args) {
        System.out.println("Enter the mode of payment:");
        Scanner ip=new Scanner(System.in);
        String str=ip.next();
        System.out.println("Enter the amount of money:");
        int amt=ip.nextInt();
        System.out.println(str);
        if(str.equals("creditcard")){
            ShoppingCart sc1=new ShoppingCart(amt,new CreditPayment());
            sc1.MakePayment();
        }
        else if(str.equals("UpiPayment")){
            ShoppingCart sc2=new ShoppingCart(amt,new CreditPayment());
            sc2.MakePayment();
        }
    }
}