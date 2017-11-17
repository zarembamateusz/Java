package KOlokwium;

import java.util.Stack;

public class Silnia extends Opereator1Arg {
    Silnia(String value){
        super(value);
    }
    private static double silnia(double i)
    { if (i < 1)
            return 1;
        else
            return i * silnia(i - 1);
    }
    public double oblicz(Stack<Operator> jakis){
        Operator fir= jakis.pop();
        double w1=fir.oblicz(jakis);
        return silnia(w1);
    };

}
