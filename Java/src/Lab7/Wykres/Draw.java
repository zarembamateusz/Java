package Lab7.Wykres;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class Draw extends JPanel {
    private int x1;
    private int x2;
    private LinkedList<Point> lista;

    public Draw(){
        x1=10;
        x2=20;

    }
    public Draw(LinkedList<Point>lista_){
        lista=lista_;

    }



        @Override
        protected void paintComponent (Graphics g){
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            //Ustalamy skale
            double max_x,max_y=0;
            int maxInt_x,maxInt_y;
            for(int j=0;j<lista.size();j++){
                if(Math.abs(lista.get(j).getY())>=max_y)
                    max_y=Math.abs(lista.get(j).getY());
            }

            if(Math.abs(lista.get(0).x)>=Math.abs(lista.get(lista.size()-1).x)){
                max_x=Math.abs(lista.get(0).x);
            }else{
                max_x=Math.abs(lista.get(lista.size()-1).x);
            }
            maxInt_x=(int)max_x;
            maxInt_y=(int)max_y;
            //rysujemy punkty
            for(int i=0;i<lista.size()-1;i++){
                Line2D.Double point =new Line2D.Double((250/max_x*lista.get(i).x+250),(250-lista.get(i).y*250/max_y),(250/max_x*lista.get(i+1).x+250),(250-lista.get(i+1).y*250/max_y) );
                g2.draw(point);
            }
            //Rysowanie ukladu wspolrzednych
            Line2D.Double ox = new Line2D.Double(0, 250, 500, 250);
            Line2D.Double oy = new Line2D.Double(250, 0, 250, 500);

            g2.draw(ox);
            g2.draw(oy);

            //Opisujemy osie

            //OX

            for (int i = 0; i <= 500; i = i + 50)
                if (i != 250)
                    g2.drawString(new Integer(-maxInt_x+(i*maxInt_x/250)).toString(), i, 260);

            //OY
            for (int i = 0; i <= 500; i = i + 50)
                if (i != 250)
                    g2.drawString(new Integer(maxInt_y-(i*maxInt_y/250)).toString(), 250, i);
        }

}
