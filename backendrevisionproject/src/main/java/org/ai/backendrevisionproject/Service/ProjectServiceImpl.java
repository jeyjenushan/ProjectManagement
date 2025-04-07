package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Chat;
import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;


    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project newProject=new Project();
        newProject.setName(project.getName());
        newProject.setDescription(project.getDescription());
        newProject.setOwner(user);
        newProject.setTags(project.getTags());
        newProject.setCategory(project.getCategory());
        newProject.getTeam().add(user);
        Project createdProject=projectRepository.save(newProject);
        Chat chat=new Chat();
        chat.setProject(createdProject);
        Chat chat1=chatService.createChat(chat);
        createdProject.setChat(chat1);
        return createdProject;




    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project>projects=projectRepository.findByTeamContainingOrOwner(user,user);
        if(category!=null){
            projects=projects.stream().filter(p->p.getCategory().equals(category)).collect(Collectors.toList());
        }
        if(tag!=null){
            projects=projects.stream().filter(project -> project.getTags().contains(tag)).collect(Collectors.toList());
        }

        return projects;


    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project>project=projectRepository.findById(projectId);
        if(project.isEmpty()){
            throw new Exception("Project not found");
        }
        return project.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {

        projectRepository.deleteById(projectId);
    }

    @Override
    public Project updateProject(Project updatedProject, Long projectId) throws Exception {
        Project oldProject=projectRepository.findById(projectId).get();
        updatedProject.setName(oldProject.getName());
        updatedProject.setDescription(oldProject.getDescription());
        updatedProject.setTags(oldProject.getTags());
        return projectRepository.save(updatedProject);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project=getProjectById(projectId);
        User user=userService.findUserById(userId);
        if(!project.getTeam().contains(user)){
            project.getChat().getUser().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
        Project project=getProjectById(projectId);
        User user=userService.findUserById(userId);
        if(project.getTeam().contains(user)){
            project.getChat().getUser().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);

    }

    @Override
    public Chat getChatById(Long projectId) throws Exception {
        Project project=getProjectById(projectId);
        return project.getChat();
    }

    @Override
    public List<Project> searchProject(String keyword, User user) throws Exception {
        List<Project> projects=projectRepository.findByNameContainingAndTeamContains(keyword,user);
        return projects;
    }
}
