package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Chat;
import org.ai.backendrevisionproject.Model.Message;
import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.MessageRepository;
import org.ai.backendrevisionproject.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;

    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User user=userService.findUserById(senderId);
        Chat chat=projectService.getProjectById(projectId).getChat();
        Message message=new Message();
        message.setMessage(content);
        message.setUser(user);
        message.setChat(chat);
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;

    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat=projectService.getChatById(projectId);
        List<Message>messages=messageRepository.findByChat_IdOrderByCreatedAtAsc(chat.getId());
        return messages;
    }
}
