package org.ai.backendrevisionproject.Controller;

import org.ai.backendrevisionproject.Model.Chat;
import org.ai.backendrevisionproject.Model.Message;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Request.MessageRequest;
import org.ai.backendrevisionproject.Service.MessageService;
import org.ai.backendrevisionproject.Service.ProjectService;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageRequest request, @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByUserProfileByJwt(jwt);
        if (user == null) {
            throw new Exception("User not found with id" + request.getUserId());

        }
        Chat chat = projectService.getProjectById(request.getProjectId()).getChat();
        if (chat == null) {
            throw new Exception("chats not found with id");
        }
        Message message = messageService.sendMessage(request.getUserId(), request.getProjectId(), request.getContent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);

    }
}


