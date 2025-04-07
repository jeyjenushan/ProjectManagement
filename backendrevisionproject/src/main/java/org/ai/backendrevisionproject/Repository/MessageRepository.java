package org.ai.backendrevisionproject.Repository;

import org.ai.backendrevisionproject.Model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {


    List<Message> findByChat_IdOrderByCreatedAtAsc(Long chatId);



}
