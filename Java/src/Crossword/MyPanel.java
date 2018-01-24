package Crossword;


import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.LinkedList;
import java.util.Random;




public class MyPanel extends JPanel {
    private JFrame mainFrame;
    private JPanel topPanel =new JPanel();
    private JPanel bottomPanel =new JPanel();
    private JPanel generatePanel = new JPanel();
    private JPanel optionPanel = new JPanel();
    private JFileChooser loadCrossword = new JFileChooser();
    private JSpinner sizeSpinner =new JSpinner();
    private JButton generateButton =new JButton("Generuj");
    private JButton printButton =new JButton("To PDF");
    private JButton saveButton =new JButton("Zapisz");
    private JButton solveButton =new JButton("Rozwiaz");
    private JButton loadButton =new JButton("Otworz");

    private LinkedList<Word> actualCrossword=new LinkedList<>();
    private LinkedList<Word> wordsListnewUpdate= new LinkedList<>();
    private LinkedList<Word> wordsList=new LinkedList<>();
    private LinkedList<String> listOfWords= new LinkedList<>();
    private LinkedList<String> descriptionOfWords = new LinkedList<>();
    //private String mainKey;
    private Word mainKey;
    private int [] actualLetterTab;
    private LinkedList<String> actualWords= new LinkedList<>();
    private LinkedList<String> actualdescriptionWords= new LinkedList<>();
    private LinkedList<String> listOfWordsUpdate= new LinkedList<>();
    private LinkedList<String> descriptionOfWordsUpdadte = new LinkedList<>();

    public MyPanel() throws IOException {
        LoadDirectrory();
        prepareGUI();
    }
    private void prepareGUI(){

        mainFrame = new JFrame("Crosswords");
        mainFrame.setSize(800,800);
        mainFrame.setLayout(new GridLayout(2, 1));
        topPanel.setLayout(new GridLayout(1,3));
        generatePanel.setLayout(new GridLayout(1,3));
        optionPanel.setLayout(new GridLayout(1,3));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        loadCrossword.setFileSelectionMode(JFileChooser.FILES_ONLY);
        generatePanel.add(sizeSpinner);
        generatePanel.add(generateButton);
        optionPanel.add(printButton);
        optionPanel.add(saveButton);
        optionPanel.add(solveButton);
        topPanel.add(generatePanel);
        topPanel.add(loadButton);
        topPanel.add(optionPanel);
        bottomPanel.setBackground(Color.white);
        mainFrame.add(topPanel);
        mainFrame.add(bottomPanel);

        //ladowanie zapisanej krzyzówki
        /*loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "tekst", "txt");
                loadCrossword.setFileFilter(filter);
                int returnVal = loadCrossword.showOpenDialog(new JPanel());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        LoadCrossword();
                        Draw();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }

            }
        });*/

        //generowanie nowej krzyzóowki
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((int)sizeSpinner.getValue()<8 &&(int)sizeSpinner.getValue()>2) {
                    GenerateCrossword();
                    Draw();
                }
                else{

                }
            }
        });

       /* //zapisywanie krzyzówki
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveCrossword();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });*/
        //zapisywanie do pdf
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrosswordToPdf();
            }
        });
        mainFrame.setVisible(true);
        bottomPanel.repaint();
        mainFrame.repaint();
    }

    /*private void LoadDirectrory() throws IOException {
        FileReader plik_do_czytania = new FileReader("C:\\Users\\Mateusz\\Desktop\\slownik.txt");
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        String linie;
        while((linie = bf.readLine()) != null){
            String word="";
            String descrioption="";
            int ster =1;
            for(int i=0;i<linie.length();i++){
                if(" ".equals(""+linie.charAt(i))&&ster==1){
                    ster=2;
                    i+=3;
                }
                if(ster==1){
                    word+=linie.charAt(i);
                }else {
                    descrioption+=linie.charAt(i);
                }
            }
            listOfWords.add(word);
            descriptionOfWords.add(descrioption);
        }
        bf.close();
    }*/

    private void LoadDirectrory() throws IOException {
        FileReader plik_do_czytania = new FileReader("C:\\Users\\Mateusz\\Desktop\\slownik.txt");
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        String linie;
        while((linie = bf.readLine()) != null){
            String word="";
            String descrioption="";
            int ster =1;
            for(int i=0;i<linie.length();i++){
                if(" ".equals(""+linie.charAt(i))&&ster==1){
                    ster=2;
                    i+=3;
                }
                if(ster==1){
                    word+=linie.charAt(i);
                }else {
                    descrioption+=linie.charAt(i);
                }
            }

            wordsList.add(new Word(word,descrioption));
        }
        bf.close();
    }

    private void Draw(){
        bottomPanel.removeAll();
        //DrawCrossword drow= new DrawCrossword(actualWords.size(),actualLetterTab,actualWords,actualdescriptionWords);
        DrawCrossword drow=new DrawCrossword(actualCrossword);
        drow.setBackground(Color.white);
        bottomPanel.add(drow);
        bottomPanel.setVisible(true);
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }
/*
    private void LoadCrossword() throws IOException {
        FileReader plik_do_czytania = new FileReader("C:\\Users\\Mateusz\\Desktop\\ala.txt");
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        String linie;
        int i=0;
        LinkedList<String> pom= new LinkedList<>();
        actualdescriptionWords.clear();
        actualWords.clear();;
        while((linie = bf.readLine()) != null){
            String word="";
            String description="";
            String number="";
            int num=0;
            if(i==0){
                mainKey=linie;
                System.out.println(mainKey);
            }else {
                int tmp=1;
                for(int j=0;j<linie.length();j++){
                    if("[".equals(""+linie.charAt(j))){
                        tmp=2;
                    }else if ("]".equals(""+linie.charAt(j))){
                        tmp=3;

                    }else {
                        if(tmp==1){
                            word+=linie.charAt(j);
                        }else if(tmp==2){
                            pom.add(linie.charAt(j)+"");
                        }else if(tmp==3){
                            description+=linie.charAt(j);
                        }
                    }
                }
                actualdescriptionWords.add(description);
                System.out.println(description);
                actualWords.add(word);
                System.out.println(word);
                actualLetterTab=new int[mainKey.length()];
            }
            i++;
        }
        actualLetterTab=new int[mainKey.length()];
        for(int k=0;k<pom.size();k++){
            actualLetterTab[k]=Integer.parseInt(pom.get(k));
        }
        bf.close();
    }

    /*private void GenerateCrossword() {
        actualdescriptionWords.clear();
        actualWords.clear();
        mainKey="";
        int size = (int) sizeSpinner.getValue();
        listOfWordsUpdate=listOfWords;
        descriptionOfWordsUpdadte=descriptionOfWords;
        LinkedList<String> possibleKey = new LinkedList<>();
        int[] keyTab= new int[size];
        int[] letterTab= new int[size];
        for (int i = 0; i < listOfWordsUpdate.size(); i++){
            if (listOfWordsUpdate.get(i).length() == size)
                possibleKey.add(listOfWordsUpdate.get(i));
        }
        if(possibleKey.size()!=0) {
            mainKey = possibleKey.get(RandomNumer(possibleKey.size()-1));
            for(int l=0;l<listOfWordsUpdate.size();l++){
                if(mainKey.equals(listOfWordsUpdate.get(l))) {
                    listOfWordsUpdate.remove(l);
                    descriptionOfWordsUpdadte.remove(l);
                }
            }

            //System.out.println(mainKey);
            for(int i=0;i<mainKey.length();i++){
                int []tmpTab=findWord(mainKey.charAt(i)+"");
                keyTab[i]=tmpTab[0];
                letterTab[i]=tmpTab[1];
                System.out.println(listOfWordsUpdate.get(tmpTab[0]));
                actualWords.add(listOfWordsUpdate.get(tmpTab[0]));
                listOfWordsUpdate.remove(tmpTab[0]);
                actualdescriptionWords.add(descriptionOfWordsUpdadte.get(tmpTab[0]));
                System.out.println(descriptionOfWordsUpdadte.get(tmpTab[0]));
                descriptionOfWordsUpdadte.remove(tmpTab[0]);
                //System.out.println(tmpTab[1]);
            }
        }
        actualLetterTab=letterTab;
    }*/

    private void GenerateCrossword() {
        actualCrossword.clear();
        wordsListnewUpdate = wordsList;

        int size = (int) sizeSpinner.getValue();
        LinkedList<Word> possibleKey = new LinkedList<>();

        for (int i = 0; i < wordsListnewUpdate.size(); i++) {
            if (wordsListnewUpdate.get(i).size() == size) {
                possibleKey.add(wordsListnewUpdate.get(i));
            }
        }

        if (possibleKey.size() != 0) {
            int random = RandomNumer(possibleKey.size() - 1);
            mainKey = possibleKey.get(random);
            for (int l = 0; l < wordsListnewUpdate.size(); l++) {
                if (mainKey.thisSame(wordsListnewUpdate.get(l)))
                    wordsListnewUpdate.remove(l);
            }
                System.out.println(mainKey.getWord());
                for (int i = 0; i < mainKey.size(); i++) {
                    actualCrossword.add(findWord(mainKey.getWord().charAt(i) + ""));

                }
        }
    }



    private void CrosswordToPdf(){
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\Mateusz\\Desktop\\tet.pdf"));
            document.open();
            PdfContentByte contentByte = writer.getDirectContent();
            PdfTemplate template = contentByte.createTemplate(800, 400);
            Graphics2D g2 = template.createGraphics(800, 400);
            bottomPanel.print(g2);
            g2.dispose();
            contentByte.addTemplate(template, 30, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(document.isOpen()){
                document.close();
            }
        }


    }

    /*private void SaveCrossword() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("C:\\Users\\Mateusz\\Desktop\\ala.txt");
        zapis.println(mainKey);
        for(int i=0;i<mainKey.length();i++)
        {
            zapis.println(actualWords.get(i)+"["+actualLetterTab[i]+"]"+actualdescriptionWords.get(i));
        }
        zapis.close();
    }*/
    /*
    private int[] findWord(String l_){
        boolean isPossible=false;
        int [] returnValue= new int[2];
        while (!isPossible) {
            int random = RandomNumer(listOfWordsUpdate.size());
            for (int i = 0; i < listOfWordsUpdate.get(random).length(); i++) {
                if (l_.equals("" + listOfWordsUpdate.get(random).charAt(i))) {
                    isPossible = true;
                    returnValue[0]=random;
                    returnValue[1]=i+1;
                }
            }
        }
        return returnValue;
    }*/

    private Word findWord(String l_) {
        boolean isPossible = false;
        Word returnValue=new Word();
        while (!isPossible) {
            int random = RandomNumer(wordsListnewUpdate.size());
            for (int i = 0; i < wordsListnewUpdate.get(random).size(); i++) {
                if (l_.equals("" + wordsListnewUpdate.get(random).getWord().charAt(i))) {
                    isPossible = true;
                    returnValue=wordsListnewUpdate.get(random);
                    returnValue.setUseLetter(i+1);
                    wordsListnewUpdate.remove(random);

                }
            }
        }
        return returnValue;
    }

    private int RandomNumer(int range_){
        Random random = new Random();
        return random.nextInt(range_);
    }
}
