package mona.joyhentai.service.impl;

import mona.joyhentai.model.BooksPages;

/**
 * @Author manonmona
 * @@Date 2021/10/26 10:53
 */
public class AbstractJoyhentaiAnalysePageService {


    /**
     * 解析页数，后缀，设置名称
     * @param url
     * @return
     */
    protected BooksPages analysePageContent(String url){
        BooksPages booksPages = new BooksPages();
        int lastIndex = url.lastIndexOf("/");   // url:http://xxxxxxx/风景.png
        int sufIndex = url.lastIndexOf(".");
        String pageText = url.substring(lastIndex+1,sufIndex);  // "风景"
        String suffix = url.substring(sufIndex);    // ".png"

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
    protected String renamePic(String pageText){
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
