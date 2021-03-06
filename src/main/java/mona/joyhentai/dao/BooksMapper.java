package mona.joyhentai.dao;

import mona.joyhentai.model.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper {

    /**
     * 修改本子详细信息
     * @param booksList
     * @return
     */
    int updateBooksDetails(@Param("books") List<Books> booksList);

    /**
     * 未下载完成的本子集合
     * @return
     */
    List<Books> loadNotDownSuccessBooks();
    /**
     * 修改本子的下载状态
     * @param books
     * @return
     */
    int updateBooksStatus(Books books);

    /**
     * 添加备份
     * @param books
     * @return
     */
    int addBuckup(Books books);

    /**
     * 根据名字查Books
     * @param books
     * @return
     */
    List<Books> loadBooksByName(Books books);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteBooks(@Param("ids") List<Integer> ids);
    /**
     * 批量插入
     * @param booksList
     * @return
     */
    int addBooks(@Param("books") List<Books> booksList);
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int insert(Books record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    Books selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    List<Books> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table books
     *
     * @mbggenerated Mon Oct 11 17:20:19 CST 2021
     */
    int updateByPrimaryKey(Books record);
}