package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author tianyh
 * created by tianyh on 6/7/19 5:52 PM
 */
@Controller
public class VetController {

    @RequestMapping({"/vets","/vets/index","/vets/index.html"})
    public String  listVets(){
        return "vets/index";
    }
}
