import java.util.Scanner;

public class Sito {
    public static void main(String[] args){
        int k;
        System.out.println("Podaj do jakiej liczby ma ja byÄ‡ generowane liczby pierwsze : ");
        Scanner dana=new Scanner(System.in);
        k=dana.nextInt();
        if(k>=2) {
            boolean tab[] = new boolean[k];
            for(int i=0;i<k;i++)
                tab[i]=true;

            for(int i=2;i<=Math.sqrt(k);i++) {
                for(int j=i+i;j<k;j+=i)
                    tab[j]=false;
            }
            System.out.println("Oto liczby pierwsze mniejesze od " + k);
            for (int i = 2; i < k; i++)
                if (tab[i])
                    System.out.println(i);
        }else {
            System.out.print("Brak liczb pierwszych");
        }
    }
}
