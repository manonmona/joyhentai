package mona.joyhentai.http.util;

import mona.joyhentai.model.Books;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @Author manonmona
 * @@Date 2021/10/11 18:04
 */
public class JoyHentaiBooksAnalyse {
    /**
     * 本子总数
     */
    private Integer BooksCount = 0;
    private Document doc;

    /**
     * @param url JoyHentai本子地址
     */
    public JoyHentaiBooksAnalyse(String url){
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析HTML返回本子信息
     * @param url JoyHentai本子地址
     * @return
     */
    public List<Books> analyseBooks(String url){
        ArrayList<Books> list = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 1.获取里面每一个<a>标签，里面包含了Books的信息，<a>标签包含 "target-by-blank" 类
        Elements as = doc.getElementsByClass("target-by-blank");
        Elements details = doc.getElementsByClass("package-list-text");
//        Elements elementsByClass = doc.getElementsByClass("target-by-blank");
//        Elements elementsByClass = doc.getElementsByClass("target-by-blank");
//        System.out.println(as.size()+","+details.size());
        for (int i =0 ; i < as.size() ; i++){
            Books books = new Books();
            //获取href
            String href = as.get(i).attr("href");
            int start = href.lastIndexOf("/");
            int end = href.indexOf("o");
            // 获取book code
            String code = href.substring(start+1,end);

            books.setSrc(href);
            books.setCode(code);
            list.add(this.analyseBooksDetail(books,details.get(i)));
        }
        return list;
    }

    /**
     * 解析本子名称，张数，发布日期
     * @param books
     * @param packageEl package-list-text 单个对象
     * @return
     */
    private Books analyseBooksDetail(Books books, Element packageEl){
        Elements h3 = packageEl.getElementsByTag("h3");
        Elements span = packageEl.getElementsByClass("pubdate");
        Elements p = packageEl.getElementsByTag("p");
        String bookName = h3.text().trim();
        String createTimeText = span.text().replaceAll(" ","").replaceAll("　","");
        // 张数里面包含了发布时间，这里把它去掉，直接替换成空字符串
        String pagesText = p.text().replaceAll(createTimeText,"");
        int pages = this.getNum(pagesText);
        long createTime = this.dateFormat(createTimeText);
        books.setName(bookName);
        books.setPages(pages);
        books.setCreateTime(createTime);
        // 本子状态：0未下载1下载完成2下载中
        books.setStatus(0);
        return books;
    }

    /**
     * 把字符串"19/19/19"格式的时间转成时间戳
     * @param dateStr
     * @return
     */
    private Long dateFormat(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            System.out.println("《"+dateStr+"》："+dateStr.length());
            e.printStackTrace();
        }
        return 0l;
    }

    /**
     * 解析本子总数
     */
    private void analyseBooksCount(){
        // 本子总数在<h1>标签
        Elements elementsByClass = doc.getElementsByTag("h1");
        String text = elementsByClass.get(0).text();
        // 设置本子总数
        this.BooksCount = this.getNum(text);
    }

    /**
     * 去除文本中其他字符，只留下数字
     * @param str
     * @return
     */
    private Integer getNum(String str){
        char[] h1 = str.toCharArray();

        // 正则
        String rule = "[0-9]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h1.length; i++) {
            if((h1[i]+"").matches(rule)){
                sb.append(h1[i]);
            }
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 获取本子总数
     * @return
     */
    public Integer getBooksCount() {
        if(this.BooksCount==0){
            return getBooksCount(true);
        }
        return this.BooksCount;
    }

    /**
     * 获取本子总数
     * @param isLoad 是否重新加载获取本子总数
     * @return
     */
    public Integer getBooksCount(boolean isLoad) {
        this.analyseBooksCount();
        return this.BooksCount;
    }
}
