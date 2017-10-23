package Lab3.Shapee;

public class Square extends Shape {
    public Square(int surface_){
        super("Square",surface_);
    }
    public void draw(){
        System.out.println(name);
    };
}