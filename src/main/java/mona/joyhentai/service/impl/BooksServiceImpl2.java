package mona.joyhentai.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mona.joyhentai.dao.BooksMapper;
import mona.joyhentai.model.Books;
import mona.joyhentai.model.JoyResult;
import mona.joyhentai.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/23 15:40
 */
@Service
public class BooksServiceImpl2 implements BooksService{

    @Autowired
    private BooksMapper booksMapper;

    @Override
    public boolean addBook(Books books) {
        return false;
    }

    @Override
    public boolean addBooks(List<Books> books) {
        return false;
    }

    @Override
    public boolean updateBooksStatus(Books books) {
        return false;
    }

    @Override
    public List<Books> loadAllBooks() {
        return null;
    }

    @Override
    public boolean deleteBooks(List<Integer> ids) {
        return false;
    }

    @Override
    public List<Books> loadBooksByName(Books books) {
        return null;
    }

    @Override
    public boolean addBuckup(Books books) {
        return false;
    }

    @Override
    public JoyResult getNotDownSuccessBooks(Integer page , Integer limit) {
        //设置分页Limit 0,20
        PageHelper.startPage(page,limit);
        List<Books> booksList = booksMapper.loadNotDownSuccessBooks();
        PageInfo<Books> pageInfo = new PageInfo<>(booksList);
        JoyResult joyResult = new JoyResult();
        joyResult.setData(booksList);
        joyResult.setCode(0);
        joyResult.setCount(pageInfo.getTotal());
        joyResult.setMsg("成功");
        return joyResult;
    }

    @Override
    public boolean updateBooksDetails(List<Books> books) {
        return false;
    }
}
