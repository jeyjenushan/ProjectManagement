package org.ai.backendrevisionproject.Controller;


import org.ai.backendrevisionproject.Config.JwtProvider;
import org.ai.backendrevisionproject.Model.Chat;
import org.ai.backendrevisionproject.Model.Invitation;
import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Request.InvitationRequest;
import org.ai.backendrevisionproject.Response.MessageResponse;
import org.ai.backendrevisionproject.Service.InvitationService;
import org.ai.backendrevisionproject.Service.IssueService;
import org.ai.backendrevisionproject.Service.ProjectService;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvitationService invitationService;



    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects(
            @RequestParam(required = false)String category,
            @RequestParam(required = false)String tag,
            @RequestHeader("Authorization") String jwt

    ) throws Exception {
        User user= userService.findByUserProfileByJwt(jwt);
        List<Project> projects=projectService.getProjectByTeam(user,category,tag);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }


    @GetMapping("/{projectId}")
    public ResponseEntity<Project>getProjectById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        Project projects=projectService.getProjectById(projectId);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project>createProject(
            @RequestBody Project project,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        Project createdProject=projectService.createProject(project,user);

        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project>updateProject(
            @PathVariable Long projectId,
            @RequestBody Project project,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        Project updateProject=projectService.updateProject(project,projectId);

        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse>deleteProject(
            @PathVariable Long projectId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        projectService.deleteProject(projectId,user.getId());
        MessageResponse response=new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Project>>searchProjects(
            @RequestParam(required = false)String keyword,

            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        List<Project> projects=projectService.searchProject(keyword,user);

        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat>getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        Chat chat=projectService.getChatById(projectId);

        return new ResponseEntity<>(chat, HttpStatus.OK);
    }









    

    @PostMapping("/invite")
    public ResponseEntity<MessageResponse>inviteProject(
            @RequestBody InvitationRequest request,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        invitationService.sendInvitation(request.getEmail(),request.getProjectId());
        MessageResponse response=new MessageResponse("User invitation sent");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/accept_invitation")
    public ResponseEntity<Invitation>acceptInviteProject(
            @RequestParam String token,
            @RequestHeader("Authorization")String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        Invitation invitation= invitationService.acceptInvitation(token,user.getId());
        projectService.addUserToProject(invitation.getProjectId(), user.getId());


        return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
    }










}
