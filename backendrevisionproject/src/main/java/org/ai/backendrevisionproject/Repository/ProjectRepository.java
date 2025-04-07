package org.ai.backendrevisionproject.Repository;

import org.ai.backendrevisionproject.Model.Project;
import org.ai.backendrevisionproject.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project>findByOwner(User owner);

    List<Project>findByNameContainingAndTeamContains(String partialName,User user);

    List<Project>findByTeamContainingOrOwner(User user1,User owner);

    @Query("SELECT p from Project p join p.team t where t=:user")
    List<Project>findProjectByTeam(@Param("user") User user);




}
