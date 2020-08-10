package tian.springframework.petclinic.controllers;

import com.adyen.Client;
import com.adyen.enums.Environment;
import com.adyen.model.Amount;
import com.adyen.model.checkout.PaymentMethodsRequest;
import com.adyen.model.checkout.PaymentMethodsResponse;
import com.adyen.model.checkout.PaymentsRequest;
import com.adyen.model.checkout.PaymentsResponse;
import com.adyen.service.Checkout;
import com.adyen.service.exception.ApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import tian.springframework.petclinic.model.Owner;
import tian.springframework.petclinic.model.Visit;
import tian.springframework.petclinic.services.PetService;
import tian.springframework.petclinic.services.VetService;
import tian.springframework.petclinic.services.VisitService;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.time.LocalDate;

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
    public PaymentMethodsResponse getPaymentMethod(@PathVariable("visitId") Long visitId){

        Visit visit = visitService.findById(visitId);
        Owner owner = visit.getPet().getOwner();

        // 1,Submit payment methods request
        // initial client
        Client client = new Client(xApiKey, Environment.TEST);
        Checkout checkout = new Checkout(client);

        //initial amount
        Amount amount = new Amount();
        amount.setCurrency(visit.getVet().getPrice().getCurrency());
        amount.setValue(visit.getAmount());

        //initial payment methods request
        PaymentMethodsRequest paymentMethodsRequest = new PaymentMethodsRequest();
        paymentMethodsRequest.setMerchantAccount(merchantAccount);
        paymentMethodsRequest.setCountryCode(owner.getCity());
        paymentMethodsRequest.setShopperLocale("en-US");
        paymentMethodsRequest.setAmount(amount);
        paymentMethodsRequest.setChannel(PaymentMethodsRequest.ChannelEnum.WEB);
        PaymentMethodsResponse response = null;

        // do request for payment methods
        try {
            response = checkout.paymentMethods(paymentMethodsRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2,get list of payment methods, then pass response to Drop-in
        return response;
    }

    @PostMapping("/owners/*/pets/*/visits/{visitId}/adyen/submitPaymentRequest")
    public PaymentsResponse postPaymentRequest(@PathVariable("visitId") Long visitId) {

        Visit visit = visitService.findById(visitId);
        Owner owner = visit.getPet().getOwner();

        // 3, submit payment request with Drop-in event data.
        // Set your X-API-KEY with the API key from the Customer Area.
        Client client = new Client(xApiKey,Environment.TEST);

        Checkout checkout = new Checkout(client);

        Amount amount = new Amount();
        amount.setCurrency(visit.getVet().getPrice().getCurrency());
        amount.setValue(visit.getAmount());

        PaymentsRequest paymentsRequest = new PaymentsRequest();
        paymentsRequest.setMerchantAccount(merchantAccount);
        paymentsRequest.setAmount(amount);

        String encryptedCardNumber = "adyenjs_0_1_18$...encryptedCardNumber";
        String encryptedExpiryMonth = "adyenjs_0_1_18$...encryptedExpiryMonth";
        String encryptedExpiryYear = "adyenjs_0_1_18$...encryptedExpiryYear";
        String encryptedSecurityCode = "adyenjs_0_1_18$...encryptedSecurityCode";

        paymentsRequest.setReference("Your order number");
        paymentsRequest.addEncryptedCardData(encryptedCardNumber,encryptedExpiryMonth, encryptedExpiryYear, encryptedSecurityCode, "John Smith");
        paymentsRequest.setReturnUrl("https://your-company.com/checkout?shopperOrder=12xy..");
        PaymentsResponse paymentsResponse = null;
        try {
            paymentsResponse = checkout.payments(paymentsRequest);
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4, get payment result, then pass response to Drop-in
        return paymentsResponse;
    }
}
