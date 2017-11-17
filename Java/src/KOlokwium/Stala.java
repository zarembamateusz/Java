package KOlokwium;

import java.util.Stack;

public class Stala extends Operator0Arg {
    Stala(String value){
        super(value);
    }
    public double oblicz(Stack<Operator> jakis){
        return Double.valueOf(getWartsc());
    };

}
