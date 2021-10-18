import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksRepeat;
import mona.joyhentai.service.BooksRepeatService;
import mona.joyhentai.service.BooksService;
import mona.joyhentai.service.impl.BooksRepeatServiceImpl;
import mona.joyhentai.service.impl.BooksServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 23:56
 */
public class RepeatBooksTest {
    public static void main(String[] args) {
        BooksRepeatService booksRepeatService = new BooksRepeatServiceImpl();
        BooksService booksService = new BooksServiceImpl();

        List<Books> booksList = booksService.loadAllBooks();

//        List<BooksRepeat> booksRepeatList = new ArrayList<>();

        /*for(int i = 0 ;i<booksList.size() ; i++){
            int reCount = 0;
            List<String> id_arr = new ArrayList<>();
            List<String> pages_arr = new ArrayList<>();
            List<String> code_arr = new ArrayList<>();
            BooksRepeat booksRepeat = new BooksRepeat();
            Books books = booksList.get(i);
            String name = books.getName();

            for(int j =1 ; j<booksList.size() ; j++){
                Books jBooks = booksList.get(j);
                String jName = jBooks.getName();
                if(name.equals(jName)){
                    if(booksRepeat.getBookId()==null){
                        booksRepeat.setBookId(jBooks.getId());
                        booksRepeat.setBookName(jName);
                        id_arr.add(books.getId()+"");
                        pages_arr.add(books.getPages()+"");
                        code_arr.add(books.getCode()+"");
                    }
                    id_arr.add(jBooks.getId()+"");
                    pages_arr.add(jBooks.getPages()+"");
                    code_arr.add(jBooks.getCode()+"");
                    reCount++;
                    booksList.remove(jBooks);
                }

                if(j==booksList.size()-1&&booksRepeat.getBookId()!=0){
                    booksRepeat.setCodeArr(code_arr.toString());
                    booksRepeat.setIdArr(id_arr.toString());
                    booksRepeat.setPagesArr(pages_arr.toString());
                    booksRepeat.setRepeatCount(reCount+"");
                    booksRepeatList.add(booksRepeat);
                }
            }
        }*/
        /*System.out.println(booksRepeatList.size());
        for (BooksRepeat booksRepeat:booksRepeatList) {
            System.out.println(booksRepeat);
        }*/

//        do{
            booksList = booksService.loadAllBooks();
            fo1:for(int i = 0 ;i<booksList.size() ; i++){
//                List<Books> list1 = booksService.loadBooksByName(booksList.get(i));
//                if(list1.size()>1){
//                    BooksRepeat booksRepeat = new BooksRepeat();
//                    booksRepeat.setBookId(booksList.get(i).getId());
//                    booksRepeat.setBookName(booksList.get(i).getName());
//                    int reCount = 0;
//                    List<Integer> id_arr = new ArrayList<>();
//                    List<String> pages_arr = new ArrayList<>();
//                    List<String> code_arr = new ArrayList<>();
//                    for (Books bo1:list1) {
//                        reCount++;
//                        id_arr.add(bo1.getId());
//                        pages_arr.add(bo1.getPages()+"");
//                        code_arr.add(bo1.getCode()+"");
//                    }
//                    booksRepeat.setCodeArr(code_arr.toString());
//                    booksRepeat.setIdArr(id_arr.toString());
//                    booksRepeat.setPagesArr(pages_arr.toString());
//                    booksRepeat.setRepeatCount(reCount+"");
//                    booksRepeatService.addBooksRepeat(booksRepeat);
//                    booksService.deleteBooks(id_arr);
//                    break fo1;
                    booksService.addBuckup(booksList.get(i));
//                    List<Integer> ids = new ArrayList<>();
//                    for (Books bo1:list1) {
//                        ids.add(bo1.getId());
//                    }
//                    booksService.deleteBooks(ids);
//                }
                /*if(i==booksList.size()-1){
                    return;
                }*/
            }
//        }while (1==1);

    }

    public static void fun1(){
        BooksRepeatService booksRepeatService = new BooksRepeatServiceImpl();
        BooksService booksService = new BooksServiceImpl();

        List<Books> booksList = booksService.loadAllBooks();

        List<BooksRepeat> booksRepeatList = new ArrayList<>();
        for(int i = 0 ;i<booksList.size() ; i++) {
            int reCount = 0;
            List<String> id_arr = new ArrayList<>();
            List<String> pages_arr = new ArrayList<>();
            List<String> code_arr = new ArrayList<>();
            BooksRepeat booksRepeat = new BooksRepeat();
            Books books = booksList.get(i);
            String name = books.getName();
            List<Books> booksList1 = booksService.loadBooksByName(books);
        }
    }

    public void test(){
        /*List<Books> booksList = booksService.loadAllBooks();
//        System.out.println(booksList.size());

        List<BooksRepeat> booksRepeatList = new ArrayList<>();

        for(int i = 0 ;i<booksList.size() ; i++){
            int reCount = 0;
            List<String> id_arr = new ArrayList<>();
            List<String> pages_arr = new ArrayList<>();
            List<String> code_arr = new ArrayList<>();
            BooksRepeat booksRepeat = new BooksRepeat();
            Books books = booksList.get(i);
            String name = books.getName();
            for(int j =1 ; j<booksList.size() ; j++){
                Books jBooks = booksList.get(j);
                String jName = jBooks.getName();
                if(name.equals(jName)){
                    if(booksRepeat.getBookId()==null){
                        booksRepeat.setBookId(jBooks.getId());
                        booksRepeat.setBookName(jName);
                        id_arr.add(books.getId()+"");
                        pages_arr.add(books.getPages()+"");
                        code_arr.add(books.getCode()+"");
                    }
                    id_arr.add(jBooks.getId()+"");
                    pages_arr.add(jBooks.getPages()+"");
                    code_arr.add(jBooks.getCode()+"");
                    reCount++;
                    booksList.remove(jBooks);
                }

                if(j==booksList.size()-1&&booksRepeat.getBookId()!=0){
                    booksRepeat.setCodeArr(code_arr.toString());
                    booksRepeat.setIdArr(id_arr.toString());
                    booksRepeat.setPagesArr(pages_arr.toString());
                    booksRepeat.setRepeatCount(reCount+"");
                    booksRepeatList.add(booksRepeat);
                }
            }
        }
        System.out.println(booksRepeatList.size());
        for (BooksRepeat booksRepeat:booksRepeatList) {
            System.out.println(booksRepeat);
        }*/
    }
}
