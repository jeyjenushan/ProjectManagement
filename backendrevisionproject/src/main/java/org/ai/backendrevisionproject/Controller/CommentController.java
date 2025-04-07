package org.ai.backendrevisionproject.Controller;


import org.ai.backendrevisionproject.Model.Comment;
import org.ai.backendrevisionproject.Model.User;
import org.ai.backendrevisionproject.Request.CommentRequest;
import org.ai.backendrevisionproject.Response.MessageResponse;
import org.ai.backendrevisionproject.Service.CommentService;
import org.ai.backendrevisionproject.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Comment> addComment(
            @RequestBody CommentRequest comment,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
    User user=userService.findByUserProfileByJwt(jwt);

    Comment createComment=commentService.createComment(comment.getIssueId(),comment.getContent(),user.getId());
    return new ResponseEntity<>(createComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(
            @PathVariable Long commentId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        commentService.deleteComment(user.getId(),commentId);
        MessageResponse messageResponse=new MessageResponse("Comment deleted successfully");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId,    @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findByUserProfileByJwt(jwt);
        List<Comment>comments=commentService.findByCommentsByIssueId(issueId);
        return new ResponseEntity<>(comments,HttpStatus.OK);


    }

}
