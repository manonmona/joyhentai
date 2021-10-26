package mona.joyhentai.controller;

import mona.joyhentai.model.Books;
import mona.joyhentai.model.JyResult;
import mona.joyhentai.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author manonmona
 * @@Date 2021/10/23 15:39
 */
@Controller
@RestController
public class BooksController {

    @Autowired
    private BooksService booksService;

    @GetMapping("/getNotSuccessBooks")
    public JyResult getNotSuccessBooks(@RequestParam(value = "page",defaultValue = "1")Integer page ,
                                       @RequestParam(value = "limit",defaultValue = "15")Integer limit){
        JyResult jyResult = booksService.getNotDownSuccessBooks(page, limit);
        return jyResult;
    }
}
