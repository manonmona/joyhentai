package mona.joyhentai.service.impl;

import mona.joyhentai.model.Books;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author manonmona
 * @@Date 2021/10/25 19:03
 */
public abstract class AbstractJoyhentaiAnalyseBookService {

    /*public Books analyseMany(){

    }

    public Books analyseOne(){

    }*/

    /**
     * 解析漫画的主题内容
     * @param contentEl package-list-text 单个对象
     * @return
     */
    public Books analyseBooksDetail(Element contentEl){
        Elements h3 = contentEl.getElementsByTag("h3");
        Elements time = contentEl.getElementsByClass("pubdate");
        Elements page = contentEl.getElementsByTag("p");
        String bookName = h3.text().trim();
        String createTimeText = time.text().replaceAll(" ","").replaceAll("　","");

        // 张数里面包含了发布时间，这里把它去掉，直接替换成空字符串
        String pagesText = page.text().toUpperCase();
        pagesText = pagesText.substring(0, pagesText.indexOf("P"));

        int pages = Integer.parseInt(pagesText);
        long createTime = this.dateFormat(createTimeText , "yy/MM/dd");

        Books books = new Books();
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
    protected Long dateFormat(String dateStr , String template) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date.getTime();
        } catch (ParseException e) {
            System.out.println("《"+dateStr+"》："+dateStr.length());
            e.printStackTrace();
        }
        return 0l;
    }


    /**
     * 去除文本中其他字符，只留下数字
     * @param str
     * @return
     */
    protected Integer getNum(String str){
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

}
