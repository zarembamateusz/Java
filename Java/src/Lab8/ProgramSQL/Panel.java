package Lab8.ProgramSQL;

import org.jdesktop.swingx.prompt.PromptSupport;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.LinkedList;


public class Panel extends JPanel {
    private JFrame mainFrame;
    private DB baza;
    private JPanel searchPanel= new JPanel();
    private JPanel addBookPanel=new JPanel();
    private JLabel searchName =new JLabel();
    private JButton addButton = new JButton("Dodaj");
    private JButton showButton = new JButton("Wyszukaj ");
    private JEditorPane searchEditorPanel =new JEditorPane();
    private JTextField bookISBN =new JTextField(10);
    private JTextField bookTitle =new JTextField(10);
    private JTextField bookAuthor =new JTextField(10);
    private JTextField bookYear =new JTextField(10);
    private DefaultListModel seartchList= new DefaultListModel();
    private JList seartchJList = new JList(seartchList);
    private JScrollPane scrollSearchtPanel = new JScrollPane(seartchJList);

    public Panel(){

        prepareGUI();
        baza= new DB();
        seartchJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        seartchJList.setSelectedIndex(0);
        seartchJList.setVisibleRowCount(3);
    }

    private void prepareGUI(){

        search();
        addBook();
        mainFrame = new JFrame("SQL by java");
        mainFrame.setSize(700,700);
        mainFrame.setLayout(new GridLayout(3, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        searchPanel.setVisible(true);
        searchPanel.add(searchName);
        mainFrame.add(searchPanel);
        mainFrame.add(new JLabel("Dodaj ksizke : "));
        mainFrame.add(addBookPanel);

        mainFrame.setVisible(true);
    }

    private void search(){

        final DefaultListModel optionName= new DefaultListModel();
        optionName.addElement("Po nazwisku autora");
        optionName.addElement("Po numerze ISBN");
        final JList scrollList = new JList(optionName);
        scrollList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollList.setSelectedIndex(0);
        scrollList.setVisibleRowCount(3);
        JScrollPane scrollListPanel = new JScrollPane(scrollList);
        searchPanel.add(searchEditorPanel);
        searchPanel.add(showButton);
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (scrollList.getSelectedIndex() != -1) {
                    if(scrollList.getSelectedIndex()==0){
                        seartchList.clear();
                        data = searchEditorPanel.getText();
                        seartchList(baza.bookOfAuthor("%"+searchEditorPanel.getText()+"%"));
                        //searchEditorPanel.setText("");
                    }
                    else if(scrollList.getSelectedIndex()==1) {
                        seartchList.clear();
                        data = searchEditorPanel.getText();
                        seartchList(baza.bookOfISBN(searchEditorPanel.getText()));
                        //searchEditorPanel.setText("");
                    }
                    searchName.setText(data);
                    searchPanel.add(scrollSearchtPanel);
                }
            }
        });

        searchPanel.add(scrollListPanel);

    }
    private void addBook(){
        PromptSupport.setPrompt("ISBN",bookISBN);
        PromptSupport.setPrompt("Author",bookAuthor);
        PromptSupport.setPrompt("Title",bookTitle);
        PromptSupport.setPrompt("Year",bookYear);
        addBookPanel.add(bookISBN);
        addBookPanel.add(bookAuthor);
        addBookPanel.add(bookTitle);
        addBookPanel.add(bookYear);
        addBookPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    baza.addBook(bookISBN.getText(),bookTitle.getText(),bookAuthor.getText(),Integer.parseInt(bookYear.getText()));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }

    private void seartchList(LinkedList<Book> lista){
        for(int i=0;i<lista.size();i++){
            seartchList.addElement(lista.get(i).getIsbn()+"   "+lista.get(i).getTitle()+"   "+lista.get(i).getAuthor()
            +"   "+lista.get(i).getYear());
        }
    }








}
