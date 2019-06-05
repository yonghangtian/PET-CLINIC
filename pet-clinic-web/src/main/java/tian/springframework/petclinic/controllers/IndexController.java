package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianyh
 * created by tianyh on 6/5/19 5:39 PM
 */
@Controller
public class IndexController {
    @RequestMapping({"","/","index","index.html"})
    public String index(){
        // this will look for a html file called "index", like index.html
        return "index";
    }
}
