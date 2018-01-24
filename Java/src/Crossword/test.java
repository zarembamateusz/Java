package Crossword;

import java.io.IOException;

public class test
{
    public static void main(String[] args) {
        try {
            MyPanel startFrame = new MyPanel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
