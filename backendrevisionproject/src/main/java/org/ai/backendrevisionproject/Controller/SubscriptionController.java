package org.ai.backendrevisionproject.Controller;

import org.ai.backendrevisionproject.Model.PlanType;
import org.ai.backendrevisionproject.Model.Subscription;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Service.SubscriptionService;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/api/subscription")
    public class SubscriptionController {
        @Autowired
        private UserService userService;
        @Autowired
        private SubscriptionService subscriptionService;


        @GetMapping("/user")
        public ResponseEntity<Subscription> getUserSubscription(
                @RequestHeader("Authorization") String jwt
        ) throws Exception {
            User user=userService.findByUserProfileByJwt(jwt);
            Subscription subscription=subscriptionService.getSubscription(user.getId());
            return new ResponseEntity<>(subscription, HttpStatus.OK);


        }
        @PatchMapping("/upgrade")
        public ResponseEntity<Subscription> upgradeSubscription(
                @RequestHeader("Authorization") String jwt,
                @RequestParam PlanType plantype
        ) throws Exception {
            User user=userService.findByUserProfileByJwt(jwt);
            Subscription subscription=subscriptionService.upgradeSubscription(user.getId(),plantype);
            return new ResponseEntity<>(subscription, HttpStatus.OK);


        }








    }
