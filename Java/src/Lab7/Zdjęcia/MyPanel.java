package Lab7.Zdjęcia;

import Lab6.Zad1.Paints;
import io.indico.Indico;
import io.indico.api.results.BatchIndicoResult;
import io.indico.api.utils.IndicoException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class MyPanel extends JPanel {
    JLabel statusLabel=new JLabel();
    private JPanel controlPanel =new JPanel();
    private JPanel controlPanel2 =new JPanel();
    private JFrame mainFrame;
    private JFileChooser load = new JFileChooser();
    private String[] zawartoscFolderu;
    private String[] zawartoscFolderu2;
    private String[] foty;
    private LinkedList<ObservableList<PieChart.Data>> prawdowpodobienstwa =new LinkedList<>();


    public MyPanel() throws IOException {
        prepareGUI();
    }

    private void prepareGUI() throws IOException {
        buttonSegregation();
        buttonafter();
        mainFrame = new JFrame("Segregator zdjęc");
        mainFrame.setSize(700,700);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.add(statusLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(controlPanel2);


        mainFrame.setVisible(true);
        mainFrame.repaint();
    }


    private void segregation(File f) throws IndicoException, IOException {
        Indico indico = new Indico("4888ed2cfd3d06ec25cf616684770600");
        //File f = new File("C:\\Users\\Mateusz\\Desktop\\foto");

        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith("jpg");
            }
        });
        int j = 0;
        foty = new String[matchingFiles.length];
        while (matchingFiles.length > j) {
            foty[j] = matchingFiles[j].getAbsolutePath();
            j++;
        }

        BatchIndicoResult multiple = indico.imageRecognition.predict(foty);
        List<Map<String, Double>> results = multiple.getImageRecognition();
        String folderDir = "C:\\Users\\Mateusz\\Desktop\\foto";
        String output = "";
        LinkedList<String> nazwy =new LinkedList<>();

        int i = 0;
        for (Map<String, Double> map : results) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            double chance = 0;
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                pieChartData.add(new PieChart.Data(pair.getKey().toString(),(double)pair.getValue()));
                if ((double) pair.getValue() > chance) {
                    chance = (double) pair.getValue();
                    output = pair.getKey().toString();
                }
                it.remove();
            }
            String directoryPath = folderDir + "\\" + output;
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            System.out.println(matchingFiles[i]);
            File activeFile = matchingFiles[i];
            activeFile.renameTo(new File(directoryPath + "\\" + activeFile.getName()));
            nazwy.add(activeFile.getName());
            prawdowpodobienstwa.add(pieChartData);
            i++;
            System.out.println(chance + ":" + output);
        }



    }

    private void selectFile(int ster) throws IOException, IndicoException {

        load.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        load.setAcceptAllFileFilterUsed(false);
        if (load.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File tmp =new File(load.getSelectedFile().getAbsolutePath());
            System.out.print(""+load.getCurrentDirectory()+load.getSelectedFile());
            if(ster==1){
            segregation(tmp);
            }else if(ster==2){

                File[] matchingFiles = tmp.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("jpg");
                    }
                });
                File[] matchingFiles2 = tmp.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.endsWith("");
                    }
                });

                int j = 0;
                if(matchingFiles!=null) {
                    zawartoscFolderu = new String[matchingFiles.length];
                    zawartoscFolderu2 = new String[matchingFiles.length];
                    while (matchingFiles.length > j) {
                        zawartoscFolderu[j] = matchingFiles[j].getAbsolutePath();
                        String onlyHere=matchingFiles2[j].getName();
                        String onlyHere2="";

                        for(int k=0;k<onlyHere.length();k++){
                            if(".".equals(""+onlyHere.charAt(k))){
                                break;
                            }else {
                                onlyHere2+=""+onlyHere.charAt(k);
                            }
                        }
                        zawartoscFolderu2[j]=onlyHere2;
                        j++;
                    }
                }
            }


        }
    }
    private void listView() throws IOException {
        final DefaultListModel fruitsName = new DefaultListModel();
        for(int i =0; i<zawartoscFolderu.length;i++ ){

            BufferedImage imagee;
            imagee = ImageIO.read(new File(zawartoscFolderu[i]));
            ImageIcon imadd =new ImageIcon(imagee);
            Image image = imadd.getImage(); // transform it
            Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            ImageIcon reimdd= new ImageIcon(newimg);  // transform it back
            fruitsName.addElement(reimdd);
        }

        final JList fruitList = new JList(fruitsName);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fruitList.setSelectedIndex(0);
        fruitList.setVisibleRowCount(3);
        JScrollPane fruitListScrollPane = new JScrollPane(fruitList);
        JButton showButton = new JButton("Show");
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (fruitList.getSelectedIndex() != -1) {
                    data = "Photo Selected: " + zawartoscFolderu2[fruitList.getSelectedIndex()];
                    statusLabel.setText(data);
                }
            }
        });
        controlPanel2.add(fruitListScrollPane);
        controlPanel2.add(showButton);
        controlPanel2.revalidate();
        controlPanel2.repaint();
    }

    private void plot(){


    }


    private void buttonSegregation() {
        JButton przegladajButton = new JButton("Do segregacji");
        przegladajButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile(1);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IndicoException e1) {
                    e1.printStackTrace();
                }
            }
        });
        controlPanel.add(przegladajButton);
    }
    private void buttonafter() {
        JButton przegladajButton = new JButton("Wybierz katalog");
        przegladajButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    selectFile(2);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (IndicoException e1) {
                    e1.printStackTrace();
                }
                try {
                    listView();
                    mainFrame.revalidate();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        controlPanel.add(przegladajButton);
    }
}