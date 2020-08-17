package tian.springframework.petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;
import tian.springframework.petclinic.model.*;
import tian.springframework.petclinic.services.PetService;
import tian.springframework.petclinic.services.VetService;
import tian.springframework.petclinic.services.VisitService;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * created by tianyh on 8/12/19 10:25 PM
 */
@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REVIEW_CURRENT_VISIT = "pets/reviewCurrentVisit";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String REDIRECT_CHECKOUT = "redirect:/owners/1/pets/1/visits/3/checkout";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    @Mock
    PetService petService;

    @Mock
    VisitService visitService;

    @Mock
    VetService vetService;

    @InjectMocks
    VisitController visitController;

    private MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void setUp() {
        Long petId = 1L;
        Long vetId = 1L;
        Long ownerId = 1L;
        int hourlyPay = 1000;
        when(petService.findById(anyLong()))
                .thenReturn(
                        Pet.builder()
                                .id(petId)
                                .birthDate(LocalDate.of(2018,11,13))
                                .name("Cutie")
                                .visits(new HashSet<>())
                                .owner(Owner.builder()
                                        .id(ownerId)
                                        .lastName("Doe")
                                        .firstName("Joe")
                                        .build())
                                .petType(PetType.builder()
                                        .name("Dog").build())
                                .build()
                );
//
//        when (vetService.findById(anyLong()))
//                .thenReturn(
//                        Vet.builder()
//                                .id(vetId)
//                                .lastName("John")
//                                .firstName("Smith")
//                                .price(Price.builder()
//                                        .id(vetId)
//                                        .currency("HKD")
//                                        .hourlyPay(hourlyPay)
//                                        .build())
//                                .specialities(new HashSet<>())
//                                .build()
//                );

        uriVariables.clear();
        uriVariables.put("ownerId", ownerId.toString());
        uriVariables.put("petId", petId.toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }

    // todo: debug
    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM))
        ;
    }

    // todo: debug
//    @Test
//    void processNewVisitForm() throws Exception {
//        when(visitService.save(any())).thenReturn(Visit.builder().id(1L).build());
//        //date=2020-09-11&description=dafasdfasd&duration=4&vet=2&petId=1
//        mockMvc.perform(post(visitsUri)
//                .param("date","2018-11-11")
//                .param("description", YET_ANOTHER_VISIT_DESCRIPTION)
//                .param("duration", "2")
//                .param("vet", "2")
//                .param("petId", "1"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("newVisit"))
//        ;
//    }
}