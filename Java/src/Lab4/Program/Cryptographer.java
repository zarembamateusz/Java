package Lab4.Program;

import java.io.*;
import java.util.LinkedList;

public class Cryptographer {
    static Algorithm nowy;

    Cryptographer(Algorithm referencja){
        nowy=referencja;
    }

    public static void crypfile(String in_, String out_) throws IOException {
        FileReader plik_do_czytania = new FileReader(in_);
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        LinkedList<String> lista =new LinkedList<String>();
        LinkedList<String> lista2 =new LinkedList<String>();
        String linie;
        int liczbaLinii = 0;
        while((linie = bf.readLine()) != null){
            lista.add(linie);
        }
        bf.close();
        for(int j=0;j<lista.size();j++){

            lista2.add(nowy.crypt(lista.get(j)));
        }



        FileWriter fileWriter = new FileWriter(out_);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int i=0;
        while(i<lista2.size()){
            bufferedWriter.write(lista2.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();

    }
    public static void decryptfile(String out_, String in_) throws IOException{
        FileReader plik_do_czytania = new FileReader(out_);
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        LinkedList<String> lista =new LinkedList<String>();
        LinkedList<String> lista2 =new LinkedList<String>();
        String linie;
        int liczbaLinii = 0;
        while((linie = bf.readLine()) != null){
            lista.add(linie);
        }
        bf.close();

        for(int j=0;j<lista.size();j++){

            lista2.add(nowy.decrypt(lista.get(j)));
        }

        FileWriter fileWriter = new FileWriter(out_);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int i=0;
        while(i<lista2.size()){
            bufferedWriter.write(lista2.get(i));
            bufferedWriter.newLine();
        }
        bufferedWriter.close();



    }
}
