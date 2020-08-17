package tian.springframework.petclinic.controllers;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.*;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.model.Visit;
import tian.springframework.petclinic.services.PetService;
import tian.springframework.petclinic.services.VetService;
import tian.springframework.petclinic.services.VisitService;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tian
 */
@Controller
public class AdyenController {

    @Value("${WebServiceUser}")
    private String webServiceUser;

    @Value("${X-API-Key}")
    private String xApiKey;

    @Value("${merchantAccount}")
    private String merchantAccount;

    @Value("${merchantReference}")
    private String merchantReference;

    @Value("${clientKey}")
    private String clientKey;

    @Value("${locale}")
    private String locale;

    private PaymentMethodsResponse paymentMethodsResponse;

    private final VisitService visitService;
    private final PetService petService;
    private final VetService vetService;

    public AdyenController(VisitService visitService, PetService petService, VetService vetService) {
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


    @GetMapping("/owners/*/pets/*/visits/{visitId}/adyen/getPaymentMethod")
    public ModelAndView getPaymentMethod(@PathVariable("visitId") Long visitId){

        Visit visit = visitService.findById(visitId);
        Owner owner = visit.getPet().getOwner();

        // 1,Submit payment methods request
        // initial client
        Client client = new Client(this.xApiKey, Environment.TEST);
        Checkout checkout = new Checkout(client);

        //initial amount
        Amount amount = new Amount();
        amount.setCurrency(visit.getVet().getPrice().getCurrency());
        amount.setValue(visit.getAmount());

        //initial payment methods request
        PaymentMethodsRequest paymentMethodsRequest = new PaymentMethodsRequest();
        paymentMethodsRequest.setMerchantAccount(this.merchantAccount);
        paymentMethodsRequest.setCountryCode(owner.getCity());
        paymentMethodsRequest.setShopperLocale(this.locale);
        paymentMethodsRequest.setAmount(amount);
        paymentMethodsRequest.setChannel(PaymentMethodsRequest.ChannelEnum.WEB);
        paymentMethodsResponse = null;

        // do request for payment methods
        try {
            paymentMethodsResponse = checkout.paymentMethods(paymentMethodsRequest);
        } catch (ApiException | IOException e) {
            e.printStackTrace();
        }

        // 2,get list of payment methods, then pass response to Drop-in
        ModelAndView mav = new ModelAndView("adyen/showPaymentMethod");
        mav.addObject(paymentMethodsResponse);

        return mav;
    }

    @GetMapping("/owners/*/pets/*/visits/{visitId}/adyen/credit-card/{brand}")
    public ModelAndView getCardBrandAndReturnConfig(@PathVariable("visitId") Long visitId,
                                                    @PathVariable String brand,
                                                    Map<String, Object> model) {
        model.put("clientKey", this.clientKey);
        model.put("paymentMethodsResponse", this.getPaymentMethodsResponse());
        model.put("locale", this.locale);

        return new ModelAndView("adyen/fillPaymentDetail");
    }
//
//    @PostMapping("/owners/*/pets/*/visits/*/adyen/credit-card/{brand}")
//    public ModelAndView receivePaymentDetail(@PathVariable("brand") String brand){
//        return new ModelAndView("adyen/fillPaymentDetail");
//    }

    @PostMapping("/owners/*/pets/*/visits/{visitId}/adyen/{paymentMethod}/{brand}/submitPaymentRequest")
    public PaymentsResponse postPaymentRequest(@PathVariable("visitId") Long visitId,
                                               @PathVariable("paymentMethod") String paymentMethod,
                                               @PathVariable("brand") String brand,
                                               @RequestBody String data) {

        Map<String,String> creditCardDetails = null;
        String encryptedCardNumber = null;
        String encryptedExpiryMonth = null;
        String encryptedExpiryYear = null;
        String encryptedSecurityCode = null;
        String holderName = null;
        try {
            creditCardDetails =
                    new ObjectMapper().readValue(data, HashMap.class);

            encryptedCardNumber = creditCardDetails.get("encryptedCardNumber");
            encryptedExpiryMonth = creditCardDetails.get("encryptedExpiryMonth");
            encryptedExpiryYear = creditCardDetails.get("encryptedExpiryYear");
            encryptedSecurityCode = creditCardDetails.get("encryptedSecurityCode");
            holderName = creditCardDetails.get("holderName");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Visit visit = visitService.findById(visitId);
        Owner owner = visit.getPet().getOwner();

        // 3, submit payment request with Drop-in event data.
        // Set your X-API-KEY with the API key from the Customer Area.
        Client client = new Client(xApiKey, Environment.TEST);

        Checkout checkout = new Checkout(client);

        Amount amount = new Amount();
        amount.setCurrency(visit.getVet().getPrice().getCurrency());
        amount.setValue(visit.getAmount());

        PaymentsRequest paymentsRequest = new PaymentsRequest();
        paymentsRequest.setMerchantAccount(merchantAccount);
        paymentsRequest.setAmount(amount);

        paymentsRequest.setReference("TIAN 's testing");
        paymentsRequest.addEncryptedCardData(encryptedCardNumber,encryptedExpiryMonth, encryptedExpiryYear, encryptedSecurityCode, "John Smith");
        paymentsRequest.setReturnUrl("http://localhost:8080/owners/1");
        PaymentsResponse paymentsResponse = null;
        try {
            paymentsResponse = checkout.payments(paymentsRequest);
        } catch (ApiException | IOException e) {
            e.printStackTrace();
        }

        // 4, get payment result, then pass response to Drop-in
        return paymentsResponse;
    }

    public PaymentMethodsResponse getPaymentMethodsResponse() {
        return paymentMethodsResponse;
    }
}
