
// The Observer Pattern is a behavioral design pattern in which one object (subject) 
// maintains a list of dependent objects (observers) and automatically notifies them 
// whenever its state changes, without tightly coupling the subject to the observers.

import java.util.ArrayList;
interface Subscribers{
   void update();
}

class User implements Subscribers{
    String userName;
    User(String name){
        this.userName=name;
    }
    public void update(){
            System.out.println(userName +" got notification");
        }
}


class Youtube{
    ArrayList<User> subscriberArray=new ArrayList<>();
    public void subscribe(User s){
            subscriberArray.add(s);
    }

    void uploadVideo(){
    for(User user:subscriberArray){
        user.update();
    }
    }
}

public class ObserverPattern{
    public static void main(String[] args) {
        Youtube yt=new Youtube();
        yt.subscribe(new User("Ashwin"));
        yt.subscribe(new User("Magesh"));
        yt.uploadVideo();
    }
}