package org.ai.backendrevisionproject.Service;

import org.ai.backendrevisionproject.Model.Comment;

import java.util.List;

public interface CommentService {


    Comment createComment(Long issueId, String comment, Long userId) throws Exception;
    void deleteComment(Long userId,Long commentId)throws  Exception;
    List<Comment> findByCommentsByIssueId(Long issueId);

}
