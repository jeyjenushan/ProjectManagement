package org.ai.backendrevisionproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String chatName;
    @OneToOne
    private Project project;
    @JsonIgnore
    @OneToMany(mappedBy = "chat",cascade = CascadeType.ALL)
    private List<Message>messages;
    @ManyToMany
    private List<User> user=new ArrayList<>();

    public Chat() {
    }

    public Chat(Long id, String chatName, Project project, List<Message> messages, User user) {
        this.id = id;
        this.chatName = chatName;
        this.project = project;
        this.messages = messages;
        this.user = (List<User>) user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
