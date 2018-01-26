package Crossword;

public class Word {
    private String word;
    private String description;
    private boolean isCorrect;
    private int useLetter;
    private String solve;




    public Word(){
        solve="";
        word="";
        description="";
        isCorrect=false;
        useLetter=0;

    }
    public Word(String word_,String description_){
        solve="";
        word=word_;
        description=description_;
        isCorrect=false;
        useLetter=0;
    }

    public String getSolve() {
        return solve;
    }

    public void setSolve(String solve) {
        this.solve = solve;

    }
    public boolean thisSame(Word sec_){
        return word.equals(sec_.getWord());
    }
    public boolean getIsCorrect() {
        return isCorrect;
    }

        public void setIsCorrect(boolean easy) {
        if(easy) {
            if (word.equals(solve))
                isCorrect = true;
            else
                isCorrect = false;
        }else
            isCorrect = false;

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
