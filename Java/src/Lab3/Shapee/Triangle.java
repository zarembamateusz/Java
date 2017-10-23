package Lab3.Shapee;

public class Triangle extends Shape {
    public Triangle(int surface_){
        super("Triangle",surface_);
    }
    public void draw(){
        System.out.println(name);
    };
}