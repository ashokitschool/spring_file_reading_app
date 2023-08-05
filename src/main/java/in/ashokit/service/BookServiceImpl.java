package in.ashokit.service;

import in.ashokit.beans.Book;
import in.ashokit.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao bookDao;

    @Override
    public void processBooks() {
        String filePath = "C://Users\\ashok\\classes\\33-SBMS\\workspace\\BooksApp\\book.txt";
        try{
            Stream<String> lines = Files.lines(Paths.get(filePath));

            lines.forEach(line -> {
                String[] values = line.split(",");

                Book b = new Book();
                b.setBookId(Integer.parseInt(values[0]));
                b.setBookName(values[1]);
                b.setBookPrice(Double.parseDouble(values[2]));

                int cnt = bookDao.insertBook(b);

                if(cnt > 0){
                    System.out.println("Record Inserted..");
                }
            });

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
