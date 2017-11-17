package KOlokwium;

import java.util.Stack;

public class WeWy {
    public WeWy(String nowy){

    }


    static Stack<Operator> pobierzDzialanie(String plik){
        String dzialanie="1 1 + 2 *";
        Stack<Operator> stack =new Stack<Operator>();
        for(int i=0;i<dzialanie.length();i++){
            if("!".equals(dzialanie.charAt(i))){
                stack.push(new Silnia(""+dzialanie.charAt(i)));

            }else if ("*".equals(dzialanie.charAt(i))) {

                stack.push(new Mnozenie(""+dzialanie.charAt(i)));

            }else if("+".equals(dzialanie.charAt(i))){
                stack.push(new Dodawanie(""+dzialanie.charAt(i)));

            }else if("/".equals(dzialanie.charAt(i))){
                stack.push(new Dzielenie(""+dzialanie.charAt(i)));

            }else{
                stack.push(new Stala(""+dzialanie.charAt(i)));
            }
        }
        return stack ;
    };

    public static double obliczk_koniec(Stack<Operator> licz){
        double val =licz.pop().oblicz(licz);

    return val;
    }

    static void zapiszWynik(double v){

        System.out.print(v);
    };
}
