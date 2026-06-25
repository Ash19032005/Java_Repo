// This example demonstrates the Adapter Design Pattern, which allows incompatible interfaces to work together.
// In this example, we have a target interface `PaymentProcessor`, an adaptee `PayPalPayment`, and an adapter `PayPalAdapter` that allows the client code to use the PayPal payment system through the PaymentProcessor interface.
// Adapter Pattern is a structural design pattern that allows two incompatible interfaces 
// to work together by introducing an adapter class that translates requests from one interface to another.
// target interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// adaptee
class PayPalPayment{
    public void makePayment(double amount){
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}
// adapter
class PayPalAdapter implements PaymentProcessor{
    private PayPalPayment paypalPayment;
    public PayPalAdapter(PayPalPayment paypalPayment){
        this.paypalPayment = paypalPayment;
    }
    @Override
    public void processPayment(double amount) {
        paypalPayment.makePayment(amount);
    }
}

// client code
public class AdapterPattern{
    public static void main(String[] args){
        PayPalPayment paypalPayment = new PayPalPayment(); 
        PaymentProcessor paymentProcessor = new PayPalAdapter(paypalPayment);
        paymentProcessor.processPayment(100.0);
    }
}