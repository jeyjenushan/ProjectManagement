package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Chat;
import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project, User user)throws Exception;
    List<Project> getProjectByTeam(User user, String category, String tag)throws Exception;
    Project getProjectById(Long projectId)throws Exception;
    void deleteProject(Long projectId,Long userId)throws Exception;
    Project updateProject(Project updatedProject,Long userId)throws Exception;
    void addUserToProject(Long projectId,Long userId)throws Exception;
    void removeUserFromProject(Long projectId,Long userId)throws Exception;
    Chat getChatById(Long projectId)throws Exception;
    List<Project>searchProject(String keyword,User user)throws Exception;
}
