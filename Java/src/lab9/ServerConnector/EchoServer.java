package lab9.ServerConnector;



import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Random;

public class EchoServer {
    public static String GenerationId(){
        Random generator = new Random();
        String id="";
        int r=1;
        String[] key={"1","2","3","4","5","6","7","8","9","0","a","b","c","d","e","f","g","h","i",
                "j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        for(int i=0; i<10; i++) {
            id+=key[generator.nextInt(36)];
        }
        return id;
    }
    public static void main(String[] args) throws IOException {
        int tmp=0;
        LinkedList<String> idList=new LinkedList<>();
        LinkedList<String> loginList=new LinkedList<>();
        LinkedList<String> passwordList=new LinkedList<>();
        loginList.add("mateusz");
        passwordList.add("mat");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6666);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 6666");
            System.exit(-1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed: 6666");
            System.exit(-1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {

            if(tmp==1){
                tmp=0;
                int login_index=-1;
                String login="";
                String password="";
                int log_pass=1;
                for(int i=0;i<inputLine.length();i++){
                    if(";".equals(inputLine.charAt(i)+"")){
                        log_pass=2;
                        i++;
                    }
                    if(log_pass==1) {
                        login += inputLine.charAt(i);
                    }else if (log_pass==2){
                        password += inputLine.charAt(i);
                    }
                }
                if (!"".equals(login) && !"".equals(password)) {
                    for(int j =0;j<loginList.size();j++){
                        if(login.equals(loginList.get(j))){
                            login_index=j;
                            break;
                        }
                    }
                    if(login_index!=-1){
                        if(password.equals(passwordList.get(login_index))){
                            String id="";
                            boolean is_free=false;
                            while(!is_free){
                                id=GenerationId();
                                is_free=true;
                                for(int k=0;k<idList.size();k++){
                                    if(id.equals(idList.get(k)))
                                        is_free=false;
                                }
                            }
                            idList.add(id);
                            out.println(id);
                        }else {
                            out.println(Levenshtein.levenshtein(passwordList.get(login_index),password));
                        }
                    }
                }else {
                    out.println("false");
                }

            }else if(tmp==2) {
                tmp=0;
                int index=-1;
                for (int i=0;i<idList.size();i++){
                    if(inputLine.equals(idList.get(i))){
                        index=i;
                        break;
                    }
                }
                if(index!=-1){
                    idList.remove(index);
                    out.println("true");
                }else {
                    out.println("false");
                }


            }else if(tmp==3) {
                tmp=0;
                int index=-1;
                for (int i=0;i<idList.size();i++){
                    if(inputLine.equals(idList.get(i))){
                        index=i;
                        break;
                    }
                }
                if(index!=-1){
                    idList.remove(index);
                    out.println("plik1;plik2");
                }else {
                    out.println("false");
                }

            }else if(tmp==4) {
                tmp=0;
                int index=-1;
                for (int i=0;i<idList.size();i++){
                    if(inputLine.equals(idList.get(i))){
                        index=i;
                        break;
                    }
                }
                if(index!=-1){
                    idList.remove(index);
                    out.println("zawartosc pliku");
                }else {
                    out.println("false");
                }

            }else if("LOGIN".equals(inputLine)) {
                tmp=1;
                out.println("Podaj login :");
            }else if("LOGOUT ID".equals(inputLine)) {
                tmp=2;
                out.println("Podaj ID :");

            }else if("LS ID".equals(inputLine)){
                tmp=3;
                out.println("Podaj ID :");

            }else if("GET ID".equals(inputLine)){
                tmp =4;
            }else {
                out.println("błedna komenda spróbój jeszcze raz");
            }


        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();

    }
}


