package com.example.ComplaintDesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.ComplaintDesk.model.Feedback;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.service.FeedbackService;
import com.example.ComplaintDesk.service.ComplaintService;
import com.example.ComplaintDesk.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String feedbackPage(Model model) {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        model.addAttribute("feedbacks", feedbacks);
        return "feedback";
    }

    @GetMapping("/new")
    public String newFeedbackPage(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        List<User> users = userService.getAllUsers();
        model.addAttribute("complaints", complaints);
        model.addAttribute("users", users);
        return "new-feedback";
    }

    @PostMapping("/create")
    public String createFeedback(@RequestParam Long complaintId,
                                @RequestParam Long userId,
                                @RequestParam Integer rating,
                                @RequestParam String comments,
                                Model model) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        User user = userService.getUserById(userId);
        
        if (complaint != null && user != null) {
            Feedback feedback = new Feedback();
            feedback.setComplaint(complaint);
            feedback.setUser(user);
            feedback.setRating(rating);
            feedback.setComments(comments);
            feedbackService.createFeedback(feedback);
        }
        return "redirect:/feedback";
    }

    // REST API endpoints
    @GetMapping("/api/all")
    @ResponseBody
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @PostMapping("/api/create")
    @ResponseBody
    public ResponseEntity<Feedback> createFeedbackApi(@RequestBody Feedback feedback) {
        Feedback saved = feedbackService.createFeedback(feedback);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Feedback> updateFeedback(@PathVariable Long id, @RequestBody Feedback feedback) {
        Feedback updated = feedbackService.updateFeedback(id, feedback);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
