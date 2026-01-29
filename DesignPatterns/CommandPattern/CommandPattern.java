// Command Pattern converts a request into an object, allowing us to parameterize actions,
//  decouple sender and receiver, and easily support undo or logging.
// Command Pattern means wrapping an action in an object 
// so that the caller doesn’t need to know how the action is performed.


import java.util.*;
interface Command{
    void execute();
}

// Receiver( Actual Worker)
class TV{
    public void turnOn(){
        System.out.println("TV is ON");
    }

    public void turnOff(){
        System.out.println("TV is OFF");
    }

}
// Concrete Commands(Wraps the Receiver)
class TurnOnCommand implements Command{
    private TV tv;
    public TurnOnCommand(TV tv){
        this.tv=tv;
    }
    public void execute(){
        tv.turnOn();
    }
}

// Concrete Commands(Wraps the Receiver)
class TurnOffCommand implements Command{
        private TV tv;
        public TurnOffCommand(TV tv){
            this.tv=tv;
        }
        public void execute(){
           tv.turnOff();
        }
}

// Invoker
class Remote{
    private Command cm;
    public Remote(Command cm) {
        this.cm=cm;
    }
    public void executeCommand(){
        cm.execute();
    }
    

}

// Client 
public class CommandPattern{
    public static void main(String[] args) {
        
        System.out.println("Enter the command ON/OFF:");
        Scanner ip=new Scanner(System.in);

        String str=ip.next();
        TV tv=new TV(); 
        Remote rm;
        if(str.equals("ON")){
            TurnOnCommand on=new TurnOnCommand(tv); 
            rm=new Remote(on);
            rm.executeCommand();
        }
        else if(str.equals("OFF")){
            TurnOffCommand off=new TurnOffCommand(tv);
            rm=new Remote(off);
            rm.executeCommand();
        }
    }
}