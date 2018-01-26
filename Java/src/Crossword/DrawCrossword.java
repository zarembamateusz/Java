package Crossword;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class DrawCrossword extends JPanel {
    private LinkedList<Word> actualCrossword=new LinkedList<>();

    public DrawCrossword(LinkedList<Word> actualCrossword_){
        actualCrossword=actualCrossword_;
        setPreferredSize(new Dimension(800, 400));
    }
    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<actualCrossword.size();i++){
            int in=i+1;
            //rysowanie pół przed główna ramką
            for(int j=0;j<actualCrossword.get(i).getUseLetter()-1;j++){
                int jn=j+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385-30*jn,30*in,30,30);
                if(j==actualCrossword.get(i).getUseLetter()-2){
                    g2.drawString((i+1)+".",385-30*(jn+1),30*in+20);
                    for(int p=0;p<actualCrossword.get(i).getSolve().length();p++){
                        if(actualCrossword.get(i).getIsCorrect()) {
                            g2.setColor(Color.RED);
                        }
                        g2.drawString(actualCrossword.get(i).getSolve().charAt(p)+"",385-30*(jn)+10+30*p,30*in+20);
                        g2.setColor(Color.BLACK);
                    }
                }
                g2.draw(tmp3);

            }
            //rysowanie opiu pól gdy pierwsze pole to główna ramka
            if(actualCrossword.get(i).getUseLetter()==1){
                g2.drawString((i+1)+".",355,30*in+20);
                for(int p=0;p<actualCrossword.get(i).getSolve().length();p++){
                    if(actualCrossword.get(i).getIsCorrect()) {
                        g2.setColor(Color.RED);
                    }
                    g2.drawString(actualCrossword.get(i).getSolve().charAt(p)+"",385+10+30*p,30*in+20);
                    g2.setColor(Color.black);

                }
            }
            //rysowanie okienek po głownej ramce
            for(int k=0;k<actualCrossword.get(i).size()-actualCrossword.get(i).getUseLetter();k++){
                int kn=k+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385+30*kn,30*in,30,30);
                g2.draw(tmp3);
            }
            //rysowanie głównej ramki
            Rectangle2D tmp =new Rectangle2D.Double(385,30*in,30,30);
            Rectangle2D tmp2 =new Rectangle2D.Double(386,30*in+1,28,28);
            g2.draw(tmp);
            g2.draw(tmp2);
            //rysowanie pytań
            if(i==actualCrossword.size()-1){
                for(int l=0;l<actualCrossword.size();l++){
                    g2.drawString((l+1)+" "+actualCrossword.get(l).getDescription(),50,30*(in)+15*(l+2));
                }
            }
        }
    }
}
