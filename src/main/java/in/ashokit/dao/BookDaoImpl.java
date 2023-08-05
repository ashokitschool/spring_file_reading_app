package in.ashokit.dao;

import in.ashokit.beans.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@Repository
public class BookDaoImpl implements BookDao {

    @Value("${DB_URL}")
    private String DB_URL;

    @Value("${DB_UNAME}")
    private String DB_UNAME;

    @Value("${DB_PWD}")
    private String DB_PWD ;

    @Override
    public int insertBook(Book book) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD)){
            PreparedStatement pstmt =
                    con.prepareStatement("INSERT INTO BOOKS VALUES (?,?,?)");

            pstmt.setInt(1, book.getBookId());
            pstmt.setString(2, book.getBookName());
            pstmt.setDouble(3, book.getBookPrice());

            int i = pstmt.executeUpdate();
            return i;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
