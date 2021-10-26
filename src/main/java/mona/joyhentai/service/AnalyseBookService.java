package mona.joyhentai.service;

import mona.joyhentai.model.Books;

import java.io.IOException;
import java.util.List;

/**
 * 解析漫画服务
 * @Author manonmona
 * @@Date 2021/10/25 18:19
 */
public interface AnalyseBookService {

    /**
     * 解析多本漫画
     * @param url
     * @return
     */
    List<Books> analyseMany(String url) throws IOException;

    /**
     * 解析单本漫画
     * @param url
     * @return
     */
    Books analyseOne(String url) throws IOException;
}
