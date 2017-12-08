package Lab8.ProgramSQL;

public class Book {
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    private String title;
    private String author;
    private int year;

    public Book(String isbn_,String title_,String author_,int year_){
        isbn=isbn_;
        title=title_;
        author=author_;
        year=year_;
    }
}
