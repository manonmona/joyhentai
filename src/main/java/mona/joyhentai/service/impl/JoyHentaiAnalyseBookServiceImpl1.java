package mona.joyhentai.service.impl;

import mona.joyhentai.model.Books;
import mona.joyhentai.service.AnalyseBookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Joyhentai漫画解析实现
 * @Author manonmona
 * @@Date 2021/10/25 18:41
 */
public class JoyHentaiAnalyseBookServiceImpl1 extends AbstractJoyhentaiAnalyseBookService implements AnalyseBookService{
    @Override
    public List<Books> analyseMany(String url) throws IOException {
        List<Books> list = new ArrayList<>();

        // 获取链接HTML
        Document doc = Jsoup.connect(url).get();

        // 漫画信息分两部分，链接部分和主题部分(描述漫画信息的资料)
        // 1.链接部分
        Elements as = doc.getElementsByClass("target-by-blank");


        if(as.size()>0){
            // 2.描述部分
            Elements contents = doc.getElementsByClass("package-list-text");

            for (int i =0 ; i < as.size() ; i++){

                // 获取链接
                String href = as.get(i).attr("href");

                // 获取主题部分
                Books books = this.analyseBooksDetail(contents.get(i));

                // 链接
                books.setSrc(href);

                list.add(books);
            }
        }

        return list;
    }

    @Override
    public Books analyseOne(String url) throws IOException {

        // 获取链接HTML
        Document doc = Jsoup.connect(url).get();

        // 漫画名称
        Elements nameEl = doc.getElementsByTag("h1");
        String name = nameEl.text();

        String href = url;

        if(url.contains("https:")||url.contains("http:")){
            href = href.substring(9);
        }
        href = href.substring(href.indexOf("/"));

        Books books = new Books();
        books.setName(name);
        books.setSrc(href);

        Elements aClass = doc.getElementsByClass("detail-box");
        for (Element ele:aClass) {


            Elements tag = ele.getElementsByClass("tag-icon");
            Elements writer = ele.getElementsByClass("circle-icon");
            Elements language = ele.getElementsByClass("language-icon");
            Elements day = ele.getElementsByClass("upload-day-icon");
            // 标签
            if(tag.size()!=0){
                Elements as = ele.getElementsByTag("a");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < as.size(); i++) {
                    sb.append(as.get(i).text());
                    if(i<as.size()-1){
                        sb.append(",");
                    }
                }
                books.setTag(sb.toString());
            }else if(writer.size()!=0){
                Elements as = ele.getElementsByTag("a");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < as.size(); i++) {
                    sb.append(as.get(i).text());
                    if(i<as.size()-1){
                        sb.append(",");
                    }
                }
                books.setWriter(sb.toString());
            }else if(language.size()!=0){
                Elements as = ele.getElementsByTag("a");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < as.size(); i++) {
                    sb.append(as.get(i).text());
                    if(i<as.size()-1){
                        sb.append(",");
                    }
                }
                books.setLanguage(sb.toString());
            }else if(day.size()!=0){
                Elements as = ele.getElementsByTag("a");
                books.setCreateTime(this.dateFormat(as.get(0).text() , "yyyy/MM/dd"));
                books.setPages(this.getNum(as.get(1).text()));
                books.setType(as.get(2).text());
            }
        }

        return books;
    }
}
