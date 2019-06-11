package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tian.springframework.petclinic.services.VetService;

/**
 * @author tianyh
 * created by tianyh on 6/7/19 5:52 PM
 */
@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets","/vets/index","/vets/index.html","/vets.html"})
    public String  listVets(Model model){

        model.addAttribute("vets", this.vetService.findAll());

        return "vets/index";
    }
}
