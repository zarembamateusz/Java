package Crossword;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

public class DrawCrossword extends JPanel {
    private int mainFrameNumber;
    private int[] beforeWordSize;
    private int[] letterTab;
    private LinkedList<String> wordsList;
    private LinkedList<String> descriptionList;

    private LinkedList<Word> actualCrossword=new LinkedList<>();

    public DrawCrossword(){
        setPreferredSize(new Dimension(800, 400));
    }
    public DrawCrossword(LinkedList<Word> actualCrossword_){
        actualCrossword=actualCrossword_;
        setPreferredSize(new Dimension(800, 400));
    }
    public DrawCrossword(int mainFrameNumber_,int[] letterTab_,LinkedList<String> wordsList_,LinkedList<String> descriptionList_){
        wordsList=wordsList_;
        descriptionList=descriptionList_;
        letterTab=letterTab_;
        mainFrameNumber=mainFrameNumber_;
        setPreferredSize(new Dimension(800, 400));
    }


    @Override
    protected void paintComponent (Graphics g){

        /*
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //rysowanie okna hasla
        for(int i=0;i<mainFrameNumber;i++){
            int in=i+1;
            for(int j=0;j<letterTab[i]-1;j++){
                int jn=j+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385-30*jn,30*in,30,30);
                if(j==letterTab[i]-2){
                    g2.drawString((i+1)+".",385-30*(jn+1),30*in+20);
                }
                g2.draw(tmp3);
            }
            if(letterTab[i]==1){
                g2.drawString((i+1)+".",355,30*in+20);
            }
            for(int k=0;k<wordsList.get(i).length()-letterTab[i];k++){
                int kn=k+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385+30*kn,30*in,30,30);
                g2.draw(tmp3);
            }
            Rectangle2D tmp =new Rectangle2D.Double(385,30*in,30,30);
            Rectangle2D tmp2 =new Rectangle2D.Double(386,30*in+1,28,28);
            g2.draw(tmp);
            g2.draw(tmp2);

            if(i==mainFrameNumber-1){
                for(int l=0;l<mainFrameNumber;l++){
                    g2.drawString((l+1)+" "+descriptionList.get(l),50,30*(in)+15*(l+2));
                }
            }
        }



*/



        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(int i=0;i<actualCrossword.size();i++){
            int in=i+1;
            for(int j=0;j<actualCrossword.get(i).getUseLetter()-1;j++){
                int jn=j+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385-30*jn,30*in,30,30);
                if(j==actualCrossword.get(i).getUseLetter()-2){
                    g2.drawString((i+1)+".",385-30*(jn+1),30*in+20);
                }
                g2.draw(tmp3);
            }
            if(actualCrossword.get(i).getUseLetter()==1){
                g2.drawString((i+1)+".",355,30*in+20);
            }
            for(int k=0;k<actualCrossword.get(i).size()-actualCrossword.get(i).getUseLetter();k++){
                int kn=k+1;
                Rectangle2D tmp3 =new Rectangle2D.Double(385+30*kn,30*in,30,30);
                g2.draw(tmp3);
            }
            Rectangle2D tmp =new Rectangle2D.Double(385,30*in,30,30);
            Rectangle2D tmp2 =new Rectangle2D.Double(386,30*in+1,28,28);
            g2.draw(tmp);
            g2.draw(tmp2);

            if(i==actualCrossword.size()-1){
                for(int l=0;l<actualCrossword.size();l++){
                    g2.drawString((l+1)+" "+actualCrossword.get(l).getDescription(),50,30*(in)+15*(l+2));
                }
            }
        }
    }

}
