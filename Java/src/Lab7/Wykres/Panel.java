package Lab7.Wykres;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.LinkedList;
import java.util.List;

public class Panel extends JPanel {
    private JFrame mainFrame;
    private JPanel panel=new JPanel();
    private JTextField probka =new JTextField(10);
    private JTextField wsp =new JTextField(10);
    private JTextField x1_ =new JTextField(2);
    private JTextField x2_ =new JTextField(2);
    private JButton drowButton =new JButton("Rysuj wykres");
    private double[] tab_=new double[3];
   // private double h=0.1;
    //private double x1=-10;
    //private double x2=10;
    LinkedList<Point> punkty = new LinkedList<>();

    public Panel(){
        prepareGUI();
    }

    private void prepareGUI(){
        licz();
        Draw uklad = new Draw(punkty);


        mainFrame = new JFrame("Wolfram wersja 0,000001 xD");
        mainFrame.setSize(700,700);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        /*if(punkty.size()!=0){

        }else {
            panel.add(probka);
            panel.add(x1_);
            panel.add(x2_);
            panel.add(wsp);
            panel.add(drowButton);
            mainFrame.add(panel);

        }*/
       // downloadValue();
        mainFrame.add(uklad);
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    private void downloadValue(){
        drowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //h=Double.parseDouble(probka.getText());
                //x1=Double.parseDouble(x1_.getText());
                //x2=Double.parseDouble(x2_.getText());
                String ws=wsp.getText().toString();
                String liczba="";
                int len=1;
                for (int i=0;i<ws.length();i++){
                    if(",".equals(ws.charAt(i))){
                        len++;
                    }
                }
                tab_=new double[len];
                int pos=0;

                for (int k=0;k<ws.length();k++){
                    if(",".equals(ws.charAt(k))){
                        tab_[pos]=Double.parseDouble(liczba);
                        pos++;
                        liczba="";
                    }else {
                        liczba+=ws.charAt(k);
                    }

                }
                System.out.print("tutaj");
                licz();
            }
        });

    }

    private void licz(){
        tab_[0]=0;
        tab_[1]=0;
        tab_[2]=1;
        double h=0.1;
        double x1=-10;
        double x2=10;
            while (x1 <= x2) {
                double wynik = 0;
                for (int i = 0; i < tab_.length; i++)
                    wynik += (tab_[i] * Math.pow(x1, i));
                punkty.add(new Point(x1, wynik));
                x1 += h;
            }

        /*if(punkty.size()>0) {
            System.out.print("tutaj3" +punkty.size());
            Draw uklad = new Draw(punkty);
            mainFrame.add(uklad);
            mainFrame.repaint();
        }*/

    }


}
