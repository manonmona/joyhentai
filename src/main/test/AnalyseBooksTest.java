import mona.joyhentai.http.util.JoyHentaiBooksAnalyse;
import mona.joyhentai.model.Books;
import mona.joyhentai.service.BooksService;
import mona.joyhentai.service.impl.BooksServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

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
        cacheBooks();
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
