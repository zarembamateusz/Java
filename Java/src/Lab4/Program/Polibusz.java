package Lab4.Program;

public class Polibusz implements Algorithm {
    static final String[][] tablica= new String[5][5];

    public Polibusz(){
        int u=0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(Character.toString((char)(97+j+i*5))=="j"){
                    u++;

                }
                tablica[i][j]=Character.toString((char)(97+j+i*5+u));
            }
        }
    }

    @Override
    public String crypt(String tocrypt_) {
        int k=0;
        String tocrypt =tocrypt_;
        String crypt= new String();
        tocrypt.toLowerCase();
        while(k<tocrypt.length()){

            for(int i=0;i<5;i++){
                for(int j =0;j<5;j++){
                    if(tocrypt.charAt(k)=="j".charAt(0)){
                        crypt+="24";
                        k++;
                        continue;
                    }
                    if(tocrypt.charAt(k)==tablica[i][j].charAt(0)){
                        crypt+=Integer.toString(i+1);
                        crypt+=Integer.toString(j+1);
                    }
                }
            }
            k++;
        }
        return crypt;
    }
    @Override
    public String decrypt(String todecrypt_) {
        String decrypt = new String();
        String todecrypt=todecrypt_;
        int k=0;
        while (k<todecrypt.length()){
            int a,b;
            a=Integer.parseInt(Character.toString(decrypt.charAt(k)));
            b=Integer.parseInt(Character.toString(decrypt.charAt(k+1)));
            decrypt+=tablica[a-1][b-1];
            k++;
            k++;
        }
        return decrypt;
    }
}
