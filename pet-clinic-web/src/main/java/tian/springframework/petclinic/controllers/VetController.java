package tian.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tian.springframework.petclinic.model.Vet;
import tian.springframework.petclinic.services.VetService;

import java.util.Set;

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

    @GetMapping({"/vets","/vets/index","/vets/index.html","/vets.html"})
    public String  listVets(Model model){

        model.addAttribute("vets", this.vetService.findAll());

        return "vets/index";
    }

    @GetMapping("api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }
}
