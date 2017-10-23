package Lab3.Shapee;

import java.util.LinkedList;

public class Comperator {
    public static void main(String[] args){
        LinkedList<Shape> list = new LinkedList<>();
        list.add(new Square(0));
        list.add(new Circle(3));
        list.add(new Rectangle(1));
        list.add(new Triangle(2));


        list.sort((o1, o2) -> {
            if (o1.surface > o2.surface) return 1;
            if (o1.surface < o2.surface) return -1;
            else return 0;
        });

        for(int i=0;i<list.size();i++)
            list.get(i).draw();

    }
}