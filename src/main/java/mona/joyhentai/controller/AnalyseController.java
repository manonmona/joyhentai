package mona.joyhentai.controller;

import mona.joyhentai.model.Books;
import mona.joyhentai.model.JyResult;
import mona.joyhentai.service.AnalyseBookService;
import mona.joyhentai.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author manonmona
 * @@Date 2021/10/25 17:59
 */
@Controller
@RestController
public class AnalyseController {

    @Autowired
    private BooksService booksService;

    @Autowired
    private AnalyseBookService analyseBookService;

    @GetMapping("/analyseMany")
    public JyResult analyseMany(String url){
        JyResult jyResult = new JyResult();
        jyResult.setCode(0);
        jyResult.setMsg("成功");
        return jyResult;
    }

    @GetMapping("/analyseOne")
    public JyResult analyseOne(String url){
        Books books = null;
        try {
            books = analyseBookService.analyseOne(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JyResult jyResult = new JyResult();
        jyResult.setCode(0);
        jyResult.setMsg("成功");
        jyResult.setData(books);
        return jyResult;
    }
}
