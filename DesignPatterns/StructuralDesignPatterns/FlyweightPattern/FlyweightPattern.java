// Flyweight pattern minimizes memory usage by sharing common object state among 
// multiple objects. It separates intrinsic state, which is shared, from extrinsic state, 
// which is supplied by the client. A common Java example is String Pool.
// Intrinsic state is the shared state that is stored in the flyweight object
// while extrinsic state is the unique state that is supplied by the client code. 
// The flyweight pattern is useful when you have a large number of objects that share common data, 
// as it can significantly reduce memory usage and improve performance.

import java.util.*;
interface Shape{
    void draw(int x,int y);
}

class Circle implements Shape{
    private String color;
    Circle(String color){
        this.color=color;
    }

    @Override
    public void draw(int x,int y) {
        System.out.println("Drawing Circle with color: " + color + " at (" + x + ", " + y + ")");
    }
} 

class FlyweightFactory{
    private static final Map<String,Shape> CircleMap=new HashMap<>();
    public static Shape getCircle(String color){
        if(!CircleMap.containsKey(color)){
            CircleMap.put(color,new Circle(color));
        }
        return CircleMap.get(color);
    }
}

class FlyweightPattern{
    public static void main(String[] args){
        Shape c1= FlyweightFactory.getCircle("Red");
        c1.draw(10, 20);
    }
}