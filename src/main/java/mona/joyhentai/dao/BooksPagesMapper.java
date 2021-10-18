package mona.joyhentai.dao;

import java.util.List;
import mona.joyhentai.model.BooksPages;
import org.apache.ibatis.annotations.Param;

public interface BooksPagesMapper {

    /**
     * 获取未下载完成的本子张数集合
     * @param bookId
     * @return
     */
    List<BooksPages> getNotDownSuccessBooksPages(Integer bookId);

    /**
     * 修改复数本子张数状态
     * @param booksPagesList
     * @return
     */
    int updateBooksPageStatus(@Param("booksPages")List<BooksPages> booksPagesList);

    /**
     * 添加复数本子张数
     * @param booksPages
     * @return
     */
    int addBooksPages(@Param("booksPages")List<BooksPages> booksPages);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books_pages
     *
     * @mbggenerated Mon Oct 11 22:48:32 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books_pages
     *
     * @mbggenerated Mon Oct 11 22:48:32 CST 2021
     */
    int insert(BooksPages record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books_pages
     *
     * @mbggenerated Mon Oct 11 22:48:32 CST 2021
     */
    BooksPages selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books_pages
     *
     * @mbggenerated Mon Oct 11 22:48:32 CST 2021
     */
    List<BooksPages> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books_pages
     *
     * @mbggenerated Mon Oct 11 22:48:32 CST 2021
     */
    int updateByPrimaryKey(BooksPages record);
}