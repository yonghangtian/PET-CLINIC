package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianyh
 * created by tianyh on 6/7/19 5:58 PM
 */
// add a class level mapping, so that mappings inside will auto add this mapping string.
@RequestMapping("/owners")
@Controller
public class OwnerController {
    // so here we means "/owners", "owners/", "owners/index", "owners/index.html"
    @RequestMapping({"","/","/index","/index.html"})
    public String  listOwners(){
        return "owners/index";
    }
}
