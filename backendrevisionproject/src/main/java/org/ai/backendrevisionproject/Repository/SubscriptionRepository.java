package org.ai.backendrevisionproject.Repository;

import org.ai.backendrevisionproject.Model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {

    Subscription findByUser_Id(Long userId);



}
