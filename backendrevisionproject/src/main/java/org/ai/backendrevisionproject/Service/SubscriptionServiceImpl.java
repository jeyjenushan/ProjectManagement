package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.PlanType;
import org.ai.backendrevisionproject.Model.Subscription;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class SubscriptionServiceImpl implements SubscriptionService{


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private UserService userService;



    @Override
    public Subscription createSubscription(User user) {
        Subscription subscription=new Subscription();
        subscription.setUser(user);
        subscription.setSubscriptionStartDate(LocalDate.now());
        subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        return subscriptionRepository.save(subscription);

    }

    @Override
    public Subscription getSubscription(Long userId) throws Exception {
        Subscription subscription=subscriptionRepository.findByUser_Id(userId);
        if(!isValid(subscription)){
            subscription.setPlanType(PlanType.FREE);
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setSubscriptionStartDate(LocalDate.now());
        }
        return subscription;
    }

    @Override
    public Subscription upgradeSubscription(Long userId, PlanType planType) {
        Subscription subscription=subscriptionRepository.findByUser_Id(userId);
        subscription.setPlanType(planType);
        subscription.setSubscriptionStartDate(LocalDate.now());
        if(planType.equals(PlanType.ANNUALLY)){
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(12));
        }
        else {
            subscription.setSubscriptionEndDate(LocalDate.now().plusMonths(1));

        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
     if(subscription.getPlanType().equals(PlanType.FREE)){
         return true;
     }
     LocalDate subscriptionEndDate=subscription.getSubscriptionEndDate();
     LocalDate currentDate=LocalDate.now();
     return subscriptionEndDate.isAfter(currentDate)|| subscriptionEndDate.equals(currentDate);
    }
}
