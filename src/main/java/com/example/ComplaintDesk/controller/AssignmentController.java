package com.example.ComplaintDesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.ComplaintDesk.model.Assignment;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.service.AssignmentService;
import com.example.ComplaintDesk.service.ComplaintService;
import com.example.ComplaintDesk.service.UserService;
import java.util.List;

@Controller
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String assignmentsPage(Model model) {
        List<Assignment> assignments = assignmentService.getAllAssignments();
        model.addAttribute("assignments", assignments);
        return "assignments";
    }

    @GetMapping("/new")
    public String newAssignmentPage(Model model) {
        List<Complaint> complaints = complaintService.getAllComplaints();
        List<User> users = userService.getAllUsers();
        model.addAttribute("complaints", complaints);
        model.addAttribute("users", users);
        return "new-assignment";
    }

    @PostMapping("/create")
    public String createAssignment(@RequestParam Long complaintId,
                                  @RequestParam Long assignedToId,
                                  @RequestParam Long assignedById,
                                  Model model) {
        Complaint complaint = complaintService.getComplaintById(complaintId);
        User assignedTo = userService.getUserById(assignedToId);
        User assignedBy = userService.getUserById(assignedById);
        
        if (complaint != null && assignedTo != null && assignedBy != null) {
            Assignment assignment = new Assignment();
            assignment.setComplaint(complaint);
            assignment.setAssignedTo(assignedTo);
            assignment.setAssignedBy(assignedBy);
            assignmentService.createAssignment(assignment);
        }
        return "redirect:/assignments";
    }

    // REST API endpoints
    @GetMapping("/api/all")
    @ResponseBody
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @GetMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        Assignment assignment = assignmentService.getAssignmentById(id);
        return ResponseEntity.ok(assignment);
    }

    @PostMapping("/api/create")
    @ResponseBody
    public ResponseEntity<Assignment> createAssignmentApi(@RequestBody Assignment assignment) {
        Assignment saved = assignmentService.createAssignment(assignment);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @RequestBody Assignment assignment) {
        Assignment updated = assignmentService.updateAssignment(id, assignment);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
        assignmentService.deleteAssignment(id);
        return ResponseEntity.noContent().build();
    }
}
