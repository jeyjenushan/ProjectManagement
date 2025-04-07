package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.PlanType;
import org.ai.backendrevisionproject.Model.Subscription;
import org.ai.backendrevisionproject.Model.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);
    Subscription getSubscription(Long userId)throws Exception;
    Subscription upgradeSubscription(Long userId, PlanType planType);
    boolean isValid(Subscription subscription);
}
