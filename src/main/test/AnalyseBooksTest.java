import mona.joyhentai.http.util.JoyHentaiBooksAnalyse;
import mona.joyhentai.model.Books;
import mona.joyhentai.service.BooksService;
import mona.joyhentai.service.impl.BooksServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author manonmona
 * @@Date 2021/10/11 18:12
 */
public class AnalyseBooksTest {
    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("https://zh.joyhentai.fun/tag/full-color/chinese").get();

        /*String title = doc.title();
        System.out.println(title);
        Elements elementsByClass = doc.getElementsByClass("card-panel white-text blue accent-2");
        String text = elementsByClass.text();
        System.out.println(text);
        char[] h1 = text.toCharArray();

        // 正则
        String rule = "[0-9]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h1.length; i++) {
            System.out.println((h1[i]+"").matches(rule));
            if((h1[i]+"").matches(rule)){
                sb.append(h1[i]);
            }
        }
        System.out.println(sb);*/

        // 检索<ul>,获取其中每一个li标签，将数据保存
        /*Elements as = doc.getElementsByClass("target-by-blank");
        Elements details = doc.getElementsByClass("package-list-text");
        System.out.println(as.size()+","+details.size());
        for (Element a: as){
            //获取href
            String href = a.attr("href");
            System.out.println(href);
            int start = href.lastIndexOf("/");
            int end = href.indexOf("o");
            // 获取book code
            System.out.println(href.substring(start+1,end));
        }*/

//        JoyHentaiBooksAnalyse joyHentaiBooksAnalyse = new JoyHentaiBooksAnalyse("https://zh.joyhentai.fun/tag/full-color/chinese");
//        System.out.println(joyHentaiBooksAnalyse.getBooksCount());
//        List<Books> books = joyHentaiBooksAnalyse.analyseBooks("https://zh.joyhentai.fun/tag/full-color/chinese");
//        System.out.println(books.size());
//        System.out.println(books);
//        System.out.println(books);
//        System.out.println(books);
//        cacheBooks();
        cacheBooksDetail();
    }

    public static void cacheBooksDetail(){
        String host = "https://zh.joyhentai.fun/";
        BooksService booksService = new BooksServiceImpl();
        List<Books> booksList = booksService.loadAllBooks();
        final List<Books> booksDetail = new ArrayList<>();
        //获取CPU数量
        int processors = Runtime.getRuntime().availableProcessors();
        // 创建cpu个数+1个线程
        ExecutorService pool = Executors.newFixedThreadPool(processors+2);
        for (int i = 0; i < booksList.size(); i++) {
            final int index = i;
            pool.execute(() -> {
                try {
                    Books books = JoyHentaiBooksAnalyse.analyseBooksDetail(host , booksList.get(index));
                    booksDetail.add(books);
                    if(booksDetail.size()==10){
                        boolean b = booksService.updateBooksDetails(booksDetail);
                        booksDetail.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    booksService.updateBooksDetails(booksDetail);
                }
            });
        }

        pool.shutdown();
        while (true){
            if(pool.isTerminated()){
                booksService.updateBooksDetails(booksDetail);
                return;
            }
        }
    }


    public static void cacheBooks(){
        BooksService booksService = new BooksServiceImpl();

        JoyHentaiBooksAnalyse joyHentaiBooksAnalyse = new JoyHentaiBooksAnalyse("https://zh.joyhentai.fun/tag/full-color/chinese");
        String pageUrl = "https://zh.joyhentai.fun/tag/full-color/chinese/page/";
        int count  = joyHentaiBooksAnalyse.getBooksCount();

        for (int i = 1;count>0;i++) {
            List<Books> books = joyHentaiBooksAnalyse.analyseBooks(pageUrl+i);
            count -= books.size();
            boolean b = booksService.addBooks(books);
            if(!b)break;
        }
    }

}
