import mona.joyhentai.http.util.JoyHentaiBooksDetailAnalyse;
import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksPages;
import mona.joyhentai.service.BooksPagesService;
import mona.joyhentai.service.BooksService;
import mona.joyhentai.service.impl.BooksPagesServiceImpl;
import mona.joyhentai.service.impl.BooksServiceImpl;

import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 20:25
 */
public class AnalyseBooksDetailTest {
    public static void main(String[] args) {

        /*JoyHentaiBooksDetailAnalyse joyHentaiBooksDetailAnalyse = new JoyHentaiBooksDetailAnalyse();
        BooksService booksService = new BooksServiceImpl();
        BooksPagesService booksPagesService = new BooksPagesServiceImpl();
        String host = "https://zh.joyhentai.fun/";
        List<Books> booksList = booksService.loadAllBooks();
        for (int i = 0;i<booksList.size();i++) {
            Books books = booksList.get(i);
            List<BooksPages> booksPages = joyHentaiBooksDetailAnalyse.analyseBooksDetial(host, books);
            if(booksPages.size()>0){
                booksPagesService.addBooksPages(booksPages);
            }else {
                booksService.addBuckup(booksList.get(i));
            }
        }*/
    }
}
