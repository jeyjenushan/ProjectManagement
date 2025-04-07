package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Message;

import java.util.List;

public interface MessageService {

    Message sendMessage(Long senderId, Long chatId, String content) throws Exception;
    List<Message>getMessagesByProjectId(Long projectId)throws Exception;

}
