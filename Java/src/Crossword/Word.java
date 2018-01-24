package Crossword;

public class Word {
    private String word;
    private String description;
    private boolean inUse;
    private int useLetter;

    public Word(){
        word="";
        description="";
        inUse=false;
        useLetter=0;
    }
    public Word(String word_,String description_){
        word=word_;
        description=description_;
        inUse=false;
        useLetter=0;
    }
    public boolean thisSame(Word sec_){
        return word.equals(sec_.getWord());
    }
    public boolean getInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getUseLetter() {
        return useLetter;
    }

    public void setUseLetter(int useLetter) {
        this.useLetter = useLetter;
    }

    public int size(){
        return word.length();
    }

    public String getWord(){
        return word;
    }

    public String getDescription() {
        return description;
    }
}
