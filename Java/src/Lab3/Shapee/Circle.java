package Lab3.Shapee;
import java.lang.String;

public class Circle extends Shape {
    public Circle(int surface_){
        super("Circle",surface_);
    }
    public void draw(){
        System.out.println(name);
    };
}
