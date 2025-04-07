package org.ai.backendrevisionproject.Service;


import org.ai.backendrevisionproject.Model.Issue;
import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.IssueRepository;
import org.ai.backendrevisionproject.Request.IssueRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;


    @Override
    public Issue getIssueById(Long issueId) throws Exception {
       Optional<Issue> issue=issueRepository.findById(issueId);
       if(issue.isEmpty()){
           throw  new Exception("Issue not found");
       }
       return issue.get();
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        List<Issue> issues=issueRepository.findByProjectId(projectId);
        return issues;
    }

    @Override
    public Issue createIssue(IssueRequest issue, User user) throws Exception {

        Issue newIssue=new Issue();
        newIssue.setTitle(issue.getTitle());
        newIssue.setDescription(issue.getDescription());
        newIssue.setStatus(issue.getStatus());
        newIssue.setProjectId(issue.getProjectId());
        newIssue.setPriority(issue.getPriority());
        newIssue.setDueDate(issue.getDueDate());
        Issue savedIssue=issueRepository.save(newIssue);
        return savedIssue;
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {

        issueRepository.deleteById(issueId);



    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
       User user=userService.findUserById(userId);
       Issue issue=getIssueById(issueId);
       issue.setAssignee(user);
       return issueRepository.save(issue);
    }

    @Override
    public Issue updateStatus(Long issueId, String status) throws Exception {
        Issue issue=getIssueById(issueId);
        issue.setStatus(status);
        return issueRepository.save(issue);
    }
}
