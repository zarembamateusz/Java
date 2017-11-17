package KOlokwium;

import java.util.Stack;

interface Oblicznenie {
    default double oblicz(Stack<Operator> jakis){
        return 2;
    };
}

