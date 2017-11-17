package KOlokwium;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        String plik="";
        File file1 = new File("C:\\Users\\Mateusz\\Desktop\\test.txt");
        try{
            Scanner in= new Scanner(file1);
            while (in.hasNextLine()) {
                plik = in.nextLine();
                WeWy.zapiszWynik(WeWy.obliczk_koniec(WeWy.pobierzDzialanie(plik)));
            }
            }catch (NumberFormatException A){}
        catch (IOException b){}
    }
}
