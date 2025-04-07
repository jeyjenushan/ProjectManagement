package org.ai.backendrevisionproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;
    private String name;
    private String description;
    private String category;
    private List<String> tags=new ArrayList<>();

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Issue>issues=new ArrayList<>();

    @JsonIgnore
    @OneToOne(mappedBy = "project" ,cascade = CascadeType.ALL)
    private Chat chat;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User>team=new ArrayList<>();

    public Project() {
    }

    public Project(int id, String name, String description, String category, List<String> tags, List<Issue> issues, Chat chat, User owner, List<User> team) {
        this.projectId = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.tags = tags;
        this.issues = issues;
        this.chat = chat;
        this.owner = owner;
        this.team = team;
    }

    public int getId() {
        return projectId;
    }

    public void setId(int id) {
        this.projectId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getTeam() {
        return team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }
}
