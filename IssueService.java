package com.example.devtrack.service;

import com.example.devtrack.model.Issue;
import com.example.devtrack.model.IssueStatus;
import com.example.devtrack.model.Priority;
import com.example.devtrack.repository.IssueRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class IssueService {
    private final IssueRepository repository;

    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Issue> findAll(IssueStatus status, Priority priority) {
        if (status != null) return repository.findByStatusOrderByUpdatedAtDesc(status);
        if (priority != null) return repository.findByPriorityOrderByUpdatedAtDesc(priority);
        return repository.findAll(Sort.by(Sort.Direction.DESC, "updatedAt"));
    }

    @Transactional(readOnly = true)
    public Issue findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IssueNotFoundException(id));
    }

    public Issue create(Issue issue) {
        issue.setStatus(issue.getStatus() == null ? IssueStatus.TODO : issue.getStatus());
        issue.setPriority(issue.getPriority() == null ? Priority.MEDIUM : issue.getPriority());
        return repository.save(issue);
    }

    public Issue update(Long id, Issue input) {
        Issue existing = findById(id);
        existing.setTitle(input.getTitle());
        existing.setDescription(input.getDescription());
        if (input.getStatus() != null) existing.setStatus(input.getStatus());
        if (input.getPriority() != null) existing.setPriority(input.getPriority());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) throw new IssueNotFoundException(id);
        repository.deleteById(id);
    }

    public long count() { return repository.count(); }
    public long countByStatus(IssueStatus status) { return repository.countByStatus(status); }
}
