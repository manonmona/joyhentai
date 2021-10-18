package mona.joyhentai.service;

import mona.joyhentai.model.BooksPages;

import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 23:06
 */
public interface BooksPagesService {
    /**
     * 添加本子张数信息
     */
    boolean addBooksPage(BooksPages booksPages);

    /**
     * 添加本子张数复数信息
     * @param booksPages
     * @return
     */
    boolean addBooksPages(List<BooksPages> booksPages);

    /**
     * 修改本子张数下载状态
     * @param booksPages
     * @return
     */
    boolean updateBooksPageStatus(BooksPages booksPages);

    /**
     * 修改复数本子张数下载状态
     * @param booksPagesList
     * @return
     */
    boolean updateBooksPageStatus(List<BooksPages> booksPagesList);

    /**
     * 获取未下载完成的本子张数集合
     * @param bookId
     * @return
     */
    List<BooksPages> getNotDownSuccessBooksPages(Integer bookId);

}
