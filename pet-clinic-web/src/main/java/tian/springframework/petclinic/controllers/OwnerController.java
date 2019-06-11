package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tian.springframework.petclinic.services.OwnerService;

/**
 * @author tianyh
 * created by tianyh on 6/7/19 5:58 PM
 */
// add a class level mapping, so that mappings inside will auto add this mapping string.
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    // so here we means "/owners", "owners/", "owners/index", "owners/index.html"
    @RequestMapping({"","/","/index","/index.html"})
    public String  listOwners(Model model){

        model.addAttribute("owners",this.ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find"})
    public String findOwner() {
        return "notImplYet";
    }
}
