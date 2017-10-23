package Lab3.Shapee;

import java.util.LinkedList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        LinkedList<Shape> shape= new LinkedList<Shape>();
        while(true){
            System.out.println("0 dodaj nowy ksztalt ");
            System.out.println("1 wyswietl ksztalty ");
            System.out.println("2 zakoncz ");
            int ster;
            Scanner odczyt=new Scanner(System.in);
            ster=odczyt.nextInt();

            if(ster==0){
                System.out.println("0 dodaj kwadrat ");
                System.out.println("1 dodaj trojkat ");
                System.out.println("2 dodaj prostokat ");
                System.out.println("3 dodaj kolo ");
                int kszt;
                Scanner ksztalt=new Scanner(System.in);
                kszt=ksztalt.nextInt();
                if(kszt==0)
                    shape.add(new Square(0));
                if(kszt==1)
                    shape.add(new Triangle(0));
                if(kszt==2)
                    shape.add(new Rectangle(0));
                if(kszt==3)
                    shape.add(new Circle(0));
            }
            if(ster==1){
                for(int i=0;i<shape.size();i++)
                    shape.get(i).draw();
            }
            if(ster==2)
                break;
        }
    }
}
