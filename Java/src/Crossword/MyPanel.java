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
    private JPanel solvePanel =new JPanel();
    private JFileChooser loadCrossword = new JFileChooser();
    private JSpinner sizeSpinner =new JSpinner();
    private JSpinner nuberSpinner =new JSpinner();
    private JTextField wordTextFild =new JTextField();

    private JButton generateButton =new JButton("Generuj");
    private JButton printButton =new JButton("To PDF");
    private JButton saveButton =new JButton("Zapisz");
    private JButton solveButton =new JButton("Rozwiaz");
    private JButton loadButton =new JButton("Otworz");
    private JButton levelButton = new JButton("Trudny");

    private boolean easy ;
    private LinkedList<Word> actualCrossword=new LinkedList<>();
    private LinkedList<Word> wordsListnewUpdate= new LinkedList<>();
    private LinkedList<Word> wordsList=new LinkedList<>();
    private Word mainKey;


    public MyPanel() throws IOException {
        easy=true;
        LoadDirectrory();
        prepareGUI();
    }
    private void prepareGUI(){
        mainFrame = new JFrame("Crosswords");
        mainFrame.setSize(800,800);
        mainFrame.setLayout(new GridLayout(2, 1));
        topPanel.setLayout(new GridLayout(1,3));
        //generatePanel.setLayout(new GridLayout(1,3));
        //optionPanel.setLayout(new GridLayout(1,3));
        //solvePanel.setLayout(new GridLayout(2,2));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        loadCrossword.setFileSelectionMode(JFileChooser.FILES_ONLY);
        generatePanel.add(sizeSpinner);
        generatePanel.add(generateButton);
        optionPanel.add(levelButton);
        optionPanel.add(printButton);
        optionPanel.add(saveButton);
        wordTextFild.setColumns(10);
        solvePanel.add(nuberSpinner);
        solvePanel.add(wordTextFild);
        solvePanel.add(solveButton);
        optionPanel.add(solvePanel);
        topPanel.add(generatePanel);
        topPanel.add(loadButton);
        topPanel.add(optionPanel);
        bottomPanel.setBackground(Color.white);
        mainFrame.add(topPanel);
        mainFrame.add(bottomPanel);

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(easy){
                    easy=false;
                    levelButton.setText("Latwy");
                }else {
                    easy=true;
                    levelButton.setText("Trudny");
                }
            }
        });

        //ladowanie zapisanej krzyzówki
        loadButton.addActionListener(new ActionListener() {
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
        });

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

       //zapisywanie krzyzówki
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveCrossword();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        //rozwiazywanie
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SolveCrossword();
                Draw();
            }
        });
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
        DrawCrossword drow=new DrawCrossword(actualCrossword);
        drow.setBackground(Color.white);
        bottomPanel.add(drow);
        bottomPanel.setVisible(true);
        bottomPanel.revalidate();
        bottomPanel.repaint();
    }

    private void LoadCrossword() throws IOException {
        FileReader plik_do_czytania = new FileReader("C:\\Users\\Mateusz\\Desktop\\ala.txt");
        BufferedReader bf = new BufferedReader(plik_do_czytania);
        String linie;
        int i=0;
        String pom= "";
        actualCrossword.clear();
        mainKey=new Word();
        while((linie = bf.readLine()) != null){
            String word="";
            String description="";
            if(i==0){
                mainKey=new Word(linie,"");
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
                            pom+=linie.charAt(j)+"";
                        }else if(tmp==3){
                            description+=linie.charAt(j);
                        }
                    }
                }
                actualCrossword.add(new Word(word,description));
                actualCrossword.get(actualCrossword.size()-1).setUseLetter(Integer.parseInt(pom));
                pom="";

            }
            i++;
        }
        bf.close();
    }

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
        int random=0;
        if (possibleKey.size() != 0) {
            if(possibleKey.size()>1){
                random = RandomNumer(possibleKey.size() - 1);
            }
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

    private void GenerateCrossword2D(){
        actualCrossword.clear();
        wordsListnewUpdate = wordsList;
        LinkedList<Word> possibleWord = new LinkedList<>();
        int pionSize=10;
        int pozSize=10;
        String [][] mainTab = new String[pozSize][pionSize];
        Word actualWord=new Word();
        int i=0;
        while (i<wordsListnewUpdate.size()){
            if(wordsListnewUpdate.get(i).size()<=pozSize)
                possibleWord.add(wordsListnewUpdate.get(i));

            i++;
        }
        actualWord=possibleWord.get(RandomNumer(possibleWord.size()-1));
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
            contentByte.addTemplate(template, 0, 300);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            if(document.isOpen()){
                document.close();
            }
        }
    }

    private void SaveCrossword() throws FileNotFoundException {
        PrintWriter zapis = new PrintWriter("C:\\Users\\Mateusz\\Desktop\\ala.txt");
        zapis.println(mainKey.getWord());
        for(int i=0;i<actualCrossword.size();i++){
            zapis.println(actualCrossword.get(i).getWord()+"["+actualCrossword.get(i).getUseLetter()+"]"+actualCrossword.get(i).getDescription());
        }
        zapis.close();
    }

    private void SolveCrossword(){
        int wordNumber=(int) nuberSpinner.getValue();
        if(wordNumber>0&&wordNumber<=actualCrossword.size()){
            String tmp=wordTextFild.getText();
            //System.out.println(wordNumber+"   " + tmp.length()+"  "+actualCrossword.get(wordNumber-1).getWord().length());
            if(tmp.length()==actualCrossword.get(wordNumber-1).getWord().length()){
                actualCrossword.get(wordNumber-1).setSolve(tmp);
                actualCrossword.get(wordNumber-1).setIsCorrect(easy);


            }

        }

    }

    private Word findWord(String l_) {
        boolean isPossible = false;
        Word returnValue=new Word();
        int random=0;
        while (!isPossible) {
            random = RandomNumer(wordsListnewUpdate.size());
            for (int i = 0; i < wordsListnewUpdate.get(random).size(); i++) {
                if (l_.equals("" + wordsListnewUpdate.get(random).getWord().charAt(i))) {
                    isPossible = true;
                    returnValue=wordsListnewUpdate.get(random);
                    returnValue.setUseLetter(i+1);
                }
            }if(isPossible){
                wordsListnewUpdate.remove(random);
            }
        }
        return returnValue;
    }

    private int RandomNumer(int range_){
        if (range_==0){
            return 0;
        }else {
            Random random = new Random();
            return random.nextInt(range_);
        }
    }
}
