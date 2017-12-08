package Lab8.ProgramSQL;
import java.sql.*;
import java.util.LinkedList;

public class DB{
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    public void connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn =
                    //DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/nazwa_bazy",
                     //       "username","password");
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/java",
                            "root","");

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }catch(Exception e){e.printStackTrace();}
    }
    public LinkedList<Book> bookOfISBN(String isbn_){
        LinkedList<Book> books=new LinkedList<>();
        try {
            connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE isbn=? ;");
            stmt.setString(1, isbn_);
            rs = stmt.executeQuery();
                while( rs.next() ) {
                    books.add(new Book(rs.getString(1),rs.getString(2),
                            rs.getString(3),rs.getInt(4)));
                    System.out.println(rs.getString(1));
                }



        }catch (SQLException ex){

        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { }
                stmt = null;
            }
        }
        return books;
    }
    public LinkedList<Book> bookOfAuthor(String author_){
        LinkedList<Book> books=new LinkedList<>();
        try {
            connect();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE author LIKE ?;");
            stmt.setString(1, author_);
            rs = stmt.executeQuery();
            while( rs.next() ) {
                books.add(new Book(rs.getString(1),rs.getString(2),
                        rs.getString(3),rs.getInt(4)));
                System.out.println(rs.getString(1));
            }



        }catch (SQLException ex){

        }finally {
            // zwalniamy zasoby, które nie będą potrzebne
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
        return books;
    }
    public void addBook(String isbn_,String title_,String author_, int year_) throws SQLException {
        //stmt = conn.createStatement();
        //stmt.executeUpdate(
        //        "INSERT INTO books(isbn,title,author,year) VALUES ('nic','nic2','nic3',15)");

        PreparedStatement prepStmt = conn.prepareStatement(
                "insert into books values (?,?,?,?);");
        prepStmt.setString(1, isbn_);
        prepStmt.setString(2, title_);
        prepStmt.setString(3, author_);
        prepStmt.setInt(4, year_);
        prepStmt.execute();


    }
}

