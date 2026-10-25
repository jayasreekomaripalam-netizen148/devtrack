package com.example.devtrack.controller;

import com.example.devtrack.model.Issue;
import com.example.devtrack.model.IssueStatus;
import com.example.devtrack.model.Priority;
import com.example.devtrack.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueApiController {
    private final IssueService service;

    public IssueApiController(IssueService service) { this.service = service; }

    @GetMapping
    public List<Issue> all(
            @RequestParam(required = false) IssueStatus status,
            @RequestParam(required = false) Priority priority) {
        return service.findAll(status, priority);
    }

    @GetMapping("/{id}")
    public Issue one(@PathVariable Long id) { return service.findById(id); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Issue create(@Valid @RequestBody Issue issue) {
        return service.create(issue);
    }

    @PutMapping("/{id}")
    public Issue update(@PathVariable Long id, @Valid @RequestBody Issue issue) {
        return service.update(id, issue);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) { service.delete(id); }
}
