package Lab5.matrix;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args){
        LinkedList<LinkedList<String>> macierz = new LinkedList<LinkedList<String>>();
        File file = new File("C:\\Users\\Mateusz\\Desktop\\test.txt");
        try(Scanner in_ = new Scanner(file);) {
            int h=0;
            String tekst= "";
            while (in_.hasNextLine()){
                macierz.add(new LinkedList<String>());
                tekst=in_.nextLine();
                int j=0;
                while (j<tekst.length()){
                    if(!",".equals(""+tekst.charAt(j)))
                        macierz.get(h).add("" + tekst.charAt(j));
                    j++;
                }
                h++;
            }

        }catch (IOException a){

        }

        Matrix jeden = new Matrix(macierz);
        Matrix dwa=new Matrix(macierz);

        //Tutaj sprawdzamy wyrzucanie wyjatku
        //Matrix dwa=new Matrix(new int[4][4]);

        try {
            Matrix out_ = jeden.Add(dwa);

            out_.Print();
        }
        catch (BadSize a){

        }
    }
}
