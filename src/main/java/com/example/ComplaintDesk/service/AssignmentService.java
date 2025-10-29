package com.example.ComplaintDesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ComplaintDesk.model.Assignment;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.repo.AssignmentRepo;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepo assignmentRepo;

    public Assignment createAssignment(Assignment assignment) {
        assignment.setAssignedAt(LocalDate.now());
        assignment.setStatus("assigned");
        return assignmentRepo.save(assignment);
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepo.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepo.findById(id).orElse(null);
    }

    public List<Assignment> getAssignmentsByStaff(User staff) {
        return assignmentRepo.findByAssignedTo(staff);
    }

    public List<Assignment> getAssignmentsByAdmin(User admin) {
        return assignmentRepo.findByAssignedBy(admin);
    }

    public List<Assignment> getAssignmentsByComplaint(Complaint complaint) {
        return assignmentRepo.findByComplaint(complaint);
    }

    public List<Assignment> getAssignmentsByStatus(String status) {
        return assignmentRepo.findByStatus(status);
    }

    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Optional<Assignment> existingAssignmentOpt = assignmentRepo.findById(id);
        if (existingAssignmentOpt.isPresent()) {
            Assignment existingAssignment = existingAssignmentOpt.get();
            existingAssignment.setStatus(updatedAssignment.getStatus());
            return assignmentRepo.save(existingAssignment);
        }
        return null;
    }

    public void deleteAssignment(Long id) {
        assignmentRepo.deleteById(id);
    }
}
