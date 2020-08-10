package tian.springframework.petclinic.controllers;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.PaymentMethodsRequest;
import com.adyen.model.checkout.PaymentMethodsResponse;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.model.Pet;
import tian.springframework.petclinic.model.Vet;
import tian.springframework.petclinic.model.Visit;
import tian.springframework.petclinic.services.PetService;
import tian.springframework.petclinic.services.VetService;
import tian.springframework.petclinic.services.VisitService;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by tianyh on 8/8/19 9:39 PM
 */
@Controller
public class VisitController {

    private final VisitService visitService;
    private final PetService petService;
    private final VetService vetService;

    public VisitController(VisitService visitService, PetService petService, VetService vetService) {
        this.visitService = visitService;
        this.petService = petService;
        this.vetService = vetService;
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }
    /**
     * Called before each and every @RequestMapping annotated method.
     * 2 goals:
     * - Make sure we always have fresh data
     * - Since we do not use the session scope, make sure that Pet object always has an id
     * (Even though id is not part of the form fields)
     *
     * @param petId
     * @return Pet
     */
    @ModelAttribute("visit")
    public Visit loadPetWithVisit(@PathVariable("petId") Long petId, Map<String, Object> model) {
        Pet pet = petService.findById(petId);
        List<Vet> vetList = new ArrayList<Vet>();
        vetList.addAll(vetService.findAll());
        model.put("pet", pet);
        model.put("vets", vetList);

        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return visit;
    }

    /**
     *  Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
     */
    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initNewVisitForm(@PathVariable("petId") Long petId, Map<String, Object> model) {
        return "pets/createOrUpdateVisitForm";
    }

    /**
     *  Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
     */
    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processNewVisitForm(@Valid Visit visit, @PathVariable Long ownerId, @PathVariable Long petId,
                                      BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return "pets/createOrUpdateVisitForm";
        } else {
            Visit newVisit = visitService.save(visit);
            Owner owner = newVisit.getPet().getOwner();
            model.put("newVisit", newVisit);
            model.put("owner", owner);
            model.put("pet", newVisit.getPet());
            String checkoutUrl = "redirect:/owners/" + ownerId
                    + "/pets/" + petId
                    + "/visits/" + newVisit.getId()
                    + "/checkout";
            return checkoutUrl;
        }
    }

    @GetMapping("/owners/*/pets/{petId}/visits/{visitId}/checkout")
    public ModelAndView processNewVisitCheckout(@PathVariable("visitId") Long visitId, @PathVariable("petId") Long petId) {
        ModelAndView mav = new ModelAndView("pets/reviewCurrentVisit");
        mav.addObject(visitService.findById(visitId));
        return mav;
    }
}
