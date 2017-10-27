package Lab4.Program;

public class ROT11 implements Algorithm {
    static final String alfabet="abcdefghijklmnopqrstuvwxyz";
    static final int przesuniecie=11;

    public ROT11(){

    }
    @Override
    public String crypt(String tocrypt_) {
        String crypt=new String();
        String tocrypt=tocrypt_;
        for(int i=0;i<tocrypt.length();i++){
            for (int j =0;j<alfabet.length();j++){
                if (tocrypt.charAt(i) == alfabet.charAt(j)) {
                    crypt+=Character.toString(alfabet.charAt((j+przesuniecie) % 25));
                }
            }
        }
        return crypt;
    }

    @Override
    public String decrypt(String todecrypt_) {
        String decrypt=new String();
        String todecrypt=todecrypt_;
        for(int i=0;i<todecrypt.length();i++){
            for (int j =0;j<alfabet.length();j++){
                if (todecrypt.charAt(i) == alfabet.charAt(j)) {
                    int l=0;
                    if(j-przesuniecie<0){
                        l=j+j-przesuniecie;
                    }
                    else
                        l=j-przesuniecie;
                    decrypt+=Character.toString(alfabet.charAt(l));
                }
            }
        }
        return decrypt;

    }

}
