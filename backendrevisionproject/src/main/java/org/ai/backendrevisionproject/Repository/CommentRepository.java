package org.ai.backendrevisionproject.Repository;

import org.ai.backendrevisionproject.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>findByIssue_Id(Long issue_id);
}
