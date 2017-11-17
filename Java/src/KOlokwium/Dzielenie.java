package KOlokwium;

import java.util.Stack;

public class Dzielenie extends Operator2Arg {
    Dzielenie(String value){
        super(value);
    }
    public double oblicz(Stack<Operator> jakis){
        Operator fir= jakis.pop();
        double w1= fir.oblicz(jakis);
        Operator sec= jakis.pop();
        double w2= sec.oblicz(jakis);
        return w1+w2;
    };

}
