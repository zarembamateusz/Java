package lab9.ServerConnector;



import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        LinkedList<String> hasla=new LinkedList<>();
        LinkedList<String> p_hasla=new LinkedList<>();
        int [] odchylenie=new int[3];
        int index=0;
        int number=0;
        int ster=0;

        hasla.add("mat3");
        hasla.add("mat4");
        hasla.add("mat5");
        hasla.add("mat");
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("localhost", 6666);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(
                new InputStreamReader(System.in));
        String userInput;

        System.out.println("Type a message: ");
        while ((userInput = stdIn.readLine()) != null) {
         /*
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        */
            if(number==0) {
                out.println("LOGIN");
                number++;
            }
            String tmp=in.readLine();
            System.out.println("echo: " + tmp);
            if(tmp.equals("Podaj login :")){
                ster=1;
            }else if(tmp.equals("false")){
                ster=0;
                out.println("LOGIN");
            }else if(tmp.length()<3){
                ster=0;
                odchylenie[index] = Integer.parseInt(tmp);
                index++;
                out.println("LOGIN");
            }else {
                System.out.println("Zalogowano " + tmp);

            }

            if(ster==1) {
                String pass = "";
                if (index < 4) {
                    pass = hasla.get(index);
                    out.println("mateusz;" + pass);

                }
                else if(index==4){
                    for(int i=3;i<hasla.size();i++){
                        if(Levenshtein.levenshtein(hasla.get(i),hasla.get(0))==odchylenie[0]&&Levenshtein.levenshtein(hasla.get(i),hasla.get(1))==odchylenie[1] &&Levenshtein.levenshtein(hasla.get(i),hasla.get(2))==odchylenie[2])
                            p_hasla.add(hasla.get(i));
                    }
                    pass= p_hasla.get(p_hasla.size()-1);
                    out.println("mateusz;"+pass);
                    p_hasla.removeLast();
                }else {
                    pass= p_hasla.get(p_hasla.size()-1);
                    out.println("mateusz;"+pass);
                    p_hasla.removeLast();
                }
            }
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}

