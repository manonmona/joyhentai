package mona.joyhentai.service.impl;

import mona.joyhentai.model.BooksPages;
import mona.joyhentai.service.AnalysePageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/26 10:49
 */
@Service
public class JoyhentaiAnalysePageServiceImpl1 extends AbstractJoyhentaiAnalysePageService implements AnalysePageService {

    @Override
    public List<BooksPages> analysePages(Integer bookId , String url) throws IOException {
        ArrayList<BooksPages> list = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        // 1.获取里面每一个<noscript>标签，里面包含了pic src的信息，<noscript>标签包含了<img>标签
        Elements as = doc.getElementsByTag("noscript");

        for (int i =0 ; i < as.size() ; i++){
            Elements imgs = as.get(i).getElementsByTag("img");
            //获取src
            String src = imgs.get(0).attr("src");

            BooksPages booksPages =this.analysePageContent(src);

            booksPages.setBookId(bookId);
            // 本子状态：0未下载1下载完成2下载中
            booksPages.setStatus(0);
            booksPages.setUrl(src);
            list.add(booksPages);
        }
        return list;
    }
}
