package org.ai.backendrevisionproject.Controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentLink;
import com.stripe.model.PaymentMethod;
import net.minidev.json.JSONObject;
import org.ai.backendrevisionproject.Model.PlanType;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Response.PaymentResponse;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/payment")
public class PaymentController {

    @Value("${stripe.api.secret}")
    private String stripeSecret;

    @Autowired
    private UserService userService;

    @PostMapping("/{planType}")
    public ResponseEntity<PaymentResponse>createPaymentlink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization")String jwt
            ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        int amount=799*100;
        if(planType.equals(PlanType.ANNUALLY)){
            amount=amount*12;
            amount=(int)(amount*0.7);
        }


        Stripe.apiKey = stripeSecret;

        PaymentIntent paymentIntent;
        JSONObject paymentLinkResponse = new JSONObject();

        try {
            // Create a new customer object with user's details
            Customer stripeCustomer = Customer.create(Map.of(
                    "name", user.getName(),
                    "email", user.getEmail()
            ));




            // Create PaymentIntent with the payment amount, currency, and callback URL
            paymentIntent = PaymentIntent.create(Map.of(
                    "amount", (int)(amount * 100),  // amount in smallest currency unit (e.g., cents)
                    "currency", "usd",
                    "customer", stripeCustomer.getId(),
                    "description", "Payment for " + planType,
                    "payment_method_types", List.of("card"),
                    "confirmation_method", "manual",
                    "confirm", true, // Auto-confirm for demonstration; remove in production
                    "return_url", "http://localhost:5173/success?planType=" + planType // redirect URL after payment completion
            ));

            // Get the client secret from the PaymentIntent
            String clientSecret = paymentIntent.getClientSecret();
            String paymentLinkUrl = "https://checkout.stripe.com/c/pay/" + clientSecret;  // example link

            PaymentResponse paymentResponse = new PaymentResponse();
            paymentResponse.setPayment_link_url(paymentLinkUrl);
            paymentResponse.setPayment_link_id(paymentIntent.getId());

            return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);

        } catch (StripeException e) {
            throw new Exception(e.getMessage());
        }

    }

}
