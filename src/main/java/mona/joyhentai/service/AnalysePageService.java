package mona.joyhentai.service;

import mona.joyhentai.model.BooksPages;

import java.util.List;

/**
 * 解析漫画页码信息
 * @Author manonmona
 * @@Date 2021/10/25 18:26
 */
public interface AnalysePageService {

    /**
     * 解析漫画页码信息
     * @param url
     * @return
     */
    List<BooksPages> analysePages(String url);
}
