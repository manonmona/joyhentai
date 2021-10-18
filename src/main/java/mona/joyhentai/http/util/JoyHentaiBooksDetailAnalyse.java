package mona.joyhentai.http.util;

import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksPages;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 21:27
 */
public class JoyHentaiBooksDetailAnalyse {

    /**
     * 解析BooksDetail
     * @param host
     * @return
     */
    public List<BooksPages> analyseBooksDetial(String host, Books books){
        ArrayList<BooksPages> list = new ArrayList<>();
        Document doc =null;
        try {
            doc = Jsoup.connect(host+books.getSrc()).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 1.获取里面每一个<noscript>标签，里面包含了pic src的信息，<noscript>标签包含了<img>标签
        Elements as = doc.getElementsByTag("noscript");
        for (int i =0 ; i < as.size() ; i++){
            BooksPages booksPages = new BooksPages();
            Elements imgs = as.get(i).getElementsByTag("img");
            //获取src
            String src = imgs.get(0).attr("src");
            // 获取book code

            booksPages.setBookId(books.getId());
            // 本子状态：0未下载1下载完成2下载中
            booksPages.setStatus(0);
            booksPages.setUrl(src);
            list.add(this.analyseBooksDetailSuffixAndName(booksPages,src));
        }
        return list;
    }

    /**
     * 解析页数，后缀，设置名称
     * @param booksPages
     * @param imgSrc
     * @return
     */
    private BooksPages analyseBooksDetailSuffixAndName(BooksPages booksPages, String imgSrc){
        int start = imgSrc.lastIndexOf("/");
        int end = imgSrc.lastIndexOf(".");
        String pageText = imgSrc.substring(start+1,end);
        String suffix = imgSrc.substring(end);
        int page = Integer.parseInt(pageText);
        String name = this.renamePic(pageText);
        booksPages.setPage(page);
        booksPages.setSuffix(suffix);
        booksPages.setName(name);
        return booksPages;
    }

    /**
     * 重命名pic名称，让其至少四位数
     * @return
     */
    private String renamePic(String pageText){
        switch (pageText.length()){
            case 1:
                pageText="000"+pageText;
                break;
            case 2:
                pageText="00"+pageText;
                break;
            case 3:
                pageText="0"+pageText;
                break;
        }
        return pageText;
    }
}
