package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;


import org.springframework.web.bind.annotation.GetMapping;


@Controller
@ResponseBody
public class BookController {

    @GetMapping("/index")
    public String index() {
        return "Welcome to the bookstore";
    }
    
}
