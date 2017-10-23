package Lab3.Shapee;

public class Rectangle extends Shape {
    public Rectangle(int surface_){
        super("Rectangle",surface_);
    }
    public void draw(){
        System.out.println(name);
    };
}
