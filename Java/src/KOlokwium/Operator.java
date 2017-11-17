package KOlokwium;

import java.util.Stack;

abstract class Operator implements Oblicznenie {
    Operator(String wartosc_){
        wartsc=wartosc_;
    }

    public String getWartsc() {
        return wartsc;
    }

    String wartsc;


}
