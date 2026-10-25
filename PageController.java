package com.example.devtrack.controller;

import com.example.devtrack.model.Issue;
import com.example.devtrack.model.IssueStatus;
import com.example.devtrack.model.Priority;
import com.example.devtrack.service.IssueService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PageController {
    private final IssueService service;

    public PageController(IssueService service) { this.service = service; }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("issues", service.findAll(null, null));
        model.addAttribute("newIssue", new Issue());
        model.addAttribute("total", service.count());
        model.addAttribute("todo", service.countByStatus(IssueStatus.TODO));
        model.addAttribute("inProgress", service.countByStatus(IssueStatus.IN_PROGRESS));
        model.addAttribute("done", service.countByStatus(IssueStatus.DONE));
        model.addAttribute("statuses", IssueStatus.values());
        model.addAttribute("priorities", Priority.values());
        return "index";
    }

    @PostMapping("/issues")
    public String create(@Valid @ModelAttribute("newIssue") Issue issue,
                         BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            redirect.addFlashAttribute("error", result.getFieldError() == null
                ? "Please check the issue details." : result.getFieldError().getDefaultMessage());
            return "redirect:/";
        }
        service.create(issue);
        redirect.addFlashAttribute("success", "Issue created successfully.");
        return "redirect:/";
    }

    @PostMapping("/issues/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam IssueStatus status,
                               RedirectAttributes redirect) {
        Issue issue = service.findById(id);
        issue.setStatus(status);
        service.update(id, issue);
        redirect.addFlashAttribute("success", "Issue status updated.");
        return "redirect:/";
    }

    @PostMapping("/issues/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        service.delete(id);
        redirect.addFlashAttribute("success", "Issue deleted.");
        return "redirect:/";
    }
}
