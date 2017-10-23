package Lab3.Shapee;

public abstract class Shape{
    public Shape(String name_,int surface_){
        name=name_;
        surface=surface_;
    }
    public int surface;

    public String name;
    public abstract void draw();
}