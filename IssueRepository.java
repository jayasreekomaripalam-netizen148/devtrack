package com.example.devtrack.repository;

import com.example.devtrack.model.Issue;
import com.example.devtrack.model.IssueStatus;
import com.example.devtrack.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByStatusOrderByUpdatedAtDesc(IssueStatus status);
    List<Issue> findByPriorityOrderByUpdatedAtDesc(Priority priority);
}
