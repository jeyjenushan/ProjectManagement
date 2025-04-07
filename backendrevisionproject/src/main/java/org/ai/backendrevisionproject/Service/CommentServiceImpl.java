package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Comment;
import org.ai.backendrevisionproject.Model.Issue;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Repository.CommentRepository;
import org.ai.backendrevisionproject.Repository.IssueRepository;
import org.ai.backendrevisionproject.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IssueRepository issueRepository;



    @Override
    public Comment createComment(Long issueId, String comment, Long userId) throws Exception {

        Optional<Issue>issueOptional=issueRepository.findById(issueId);
        Optional<User>userOptional=userRepository.findById(userId);
        if(issueOptional.isEmpty() || userOptional.isEmpty()){

            throw new Exception("Issue or User Not Found");
        }
        Comment createdComment=new Comment();
        createdComment.setIssue(issueOptional.get());
        createdComment.setUser(userOptional.get());
        createdComment.setContent(comment);
        createdComment.setCreatedDateTime(LocalDateTime.now());
        Comment savedComment=commentRepository.save(createdComment);
        return savedComment;




    }

    @Override
    public void deleteComment(Long userId, Long commentId) throws Exception {
   Optional<User> userOptional=userRepository.findById(userId);
   Optional<Comment>commentOptional=commentRepository.findById(commentId);

   if(userOptional.isEmpty() || commentOptional.isEmpty()){
       throw  new Exception("Comment and user not found");
   }

   User user=userOptional.get();
   Comment comment=commentOptional.get();

   if(comment.getUser().equals(user)){
       commentRepository.delete(comment);
   }
   else{
       throw new Exception("User does not have permisssion to delete this comment");
   }





    }

    @Override
    public List<Comment> findByCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssue_Id(issueId);
    }
}
