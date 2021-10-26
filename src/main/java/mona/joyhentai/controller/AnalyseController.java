package mona.joyhentai.controller;

import mona.joyhentai.model.Books;
import mona.joyhentai.model.BooksPages;
import mona.joyhentai.model.JoyResult;
import mona.joyhentai.service.AnalyseBookService;
import mona.joyhentai.service.AnalysePageService;
import mona.joyhentai.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private AnalysePageService analysePageService;

    @GetMapping("/analyseMany")
    public JoyResult analyseMany(String url){
        List<Books> list = null;
        try {
            list = analyseBookService.analyseMany(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg("成功");
        joyResult.setData(list);
        return joyResult;
    }

    @GetMapping("/analyseOne")
    public JoyResult analyseOne(String url){
        Books books = null;
        List<BooksPages> list = null;
        try {
            books = analyseBookService.analyseOne(url);
            list = analysePageService.analysePages(null, url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String , Object> map = new LinkedHashMap<>();
        map.put("Book" , books);
        map.put("Page" , list);
        JoyResult joyResult = new JoyResult();
        joyResult.setCode(0);
        joyResult.setMsg("成功");
        joyResult.setData(map);
        return joyResult;
    }

    @GetMapping("/downOne")
    public JoyResult downOne(String url){
        Books books = null;
        List<BooksPages> list = null;
        try {
            books = analyseBookService.analyseOne(url);
            list = analysePageService.analysePages(null, url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JoyResult joyResult = new JoyResult();
        if(books==null || list.size()==0){
            joyResult.setCode(1);
            joyResult.setMsg("失败");
        }else {

        }
        return joyResult;
    }
}
