package mona.joyhentai.service;

import mona.joyhentai.model.Books;
import mona.joyhentai.model.JoyResult;

import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/11 17:56
 */
public interface BooksService {
    /**
     * 添加本子
     * @param books
     */
    boolean addBook(Books books);

    /**
     * 添加复数本子
     * @param books
     */
    boolean addBooks(List<Books> books);

    /**
     * 修改本子下载状态
     * @param books
     */
    boolean updateBooksStatus(Books books);

    /**
     * 加载所有本子
     * @return
     */
    List<Books> loadAllBooks();

    /**
     * 批量删除本子
     * @param ids
     * @return
     */
    boolean deleteBooks(List<Integer> ids);

    /**
     * 根据名称查本子
     * @param books
     * @return
     */
    List<Books> loadBooksByName(Books books);

    /**
     * 添加备份
     * @param books
     * @return
     */
    boolean addBuckup(Books books);

    /**
     * 获取未下载完成的本子
     * @return
     */
    JoyResult getNotDownSuccessBooks(Integer page , Integer limit);

    /**
     * 修改本子详细信息
     * @param books
     * @return
     */
    boolean updateBooksDetails(List<Books> books);
}
