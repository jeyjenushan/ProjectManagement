package org.ai.backendrevisionproject.Controller;

import org.ai.backendrevisionproject.Dto.IssueDTO;
import org.ai.backendrevisionproject.Model.Issue;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Request.IssueRequest;
import org.ai.backendrevisionproject.Response.MessageResponse;
import org.ai.backendrevisionproject.Service.IssueService;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;


    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId)throws Exception{
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId)throws Exception{
        return ResponseEntity.ok(issueService.getIssueByProjectId(projectId));
    }


    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueRequest issueRequest, @RequestHeader("Authorization")String jwt) throws Exception {

        System.out.println("issue-----"+issueRequest);
        User tokenUser=userService.findByUserProfileByJwt(jwt);
        User user=userService.findUserById(tokenUser.getId());

        Issue createdIssue=issueService.createIssue(issueRequest,user);
        IssueDTO issueDTO=new IssueDTO();
        issueDTO.setDescription(createdIssue.getDescription());
        issueDTO.setDueDate(createdIssue.getDueDate());
        issueDTO.setId(createdIssue.getId());
        issueDTO.setPriority(createdIssue.getPriority());
        issueDTO.setProject(createdIssue.getProject());
        issueDTO.setProjectId(createdIssue.getProjectId());
        issueDTO.setStatus(createdIssue.getStatus());
        issueDTO.setTitle(createdIssue.getTitle());
        issueDTO.setTags(createdIssue.getTags());
        issueDTO.setAssignee(createdIssue.getAssignee());



        return ResponseEntity.ok(issueDTO);

    }

    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization")String jwt) throws Exception{
        User user=userService.findByUserProfileByJwt(jwt);
        issueService.deleteIssue(issueId,user.getId());
        MessageResponse authResponse=new MessageResponse("Issue deleted");

        return ResponseEntity.ok(authResponse);

    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue>addUserToIssue
            (@PathVariable Long issueId,@PathVariable Long userId)throws  Exception{
        Issue issue=issueService.addUserToIssue(issueId,userId);
        return ResponseEntity.ok(issue);
    }

    @PutMapping(
            "/{issueId}/status/{status}"
    )
    public ResponseEntity<Issue>updateIssueStatus
            (@PathVariable String status,
             @PathVariable Long issueId
            )throws Exception{

        Issue issue=issueService.updateStatus(issueId,status);
        return ResponseEntity.ok(issue);

    }















}
