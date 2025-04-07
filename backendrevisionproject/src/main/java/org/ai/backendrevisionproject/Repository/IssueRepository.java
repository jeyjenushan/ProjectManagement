package org.ai.backendrevisionproject.Repository;

import org.ai.backendrevisionproject.Model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {

    List<Issue>findByProjectId(Long projectId);



}
