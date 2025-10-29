package com.example.ComplaintDesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.repo.ComplaintRepo;

@Service
public class ComplaintService {

    @Autowired
    private ComplaintRepo complaintRepo;

    public Complaint createComplaint(Complaint complaint) {
        complaint.setCreatedAt(LocalDate.now());
        complaint.setStatus("open");
        return complaintRepo.save(complaint);
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return complaintRepo.findById(id).orElse(null);
    }

    public List<Complaint> getComplaintsByUser(User user) {
        return complaintRepo.findByCreatedBy(user);
    }

    public List<Complaint> getComplaintsByStatus(String status) {
        return complaintRepo.findByStatus(status);
    }

    public List<Complaint> getComplaintsByCategory(String category) {
        return complaintRepo.findByCategory(category);
    }

    public Complaint updateComplaint(Long id, Complaint updatedComplaint) {
        Optional<Complaint> existingComplaintOpt = complaintRepo.findById(id);
        if (existingComplaintOpt.isPresent()) {
            Complaint existingComplaint = existingComplaintOpt.get();
            existingComplaint.setTitle(updatedComplaint.getTitle());
            existingComplaint.setDescription(updatedComplaint.getDescription());
            existingComplaint.setCategory(updatedComplaint.getCategory());
            existingComplaint.setStatus(updatedComplaint.getStatus());
            existingComplaint.setAttachmentUrl(updatedComplaint.getAttachmentUrl());
            return complaintRepo.save(existingComplaint);
        }
        return null;
    }

    public void deleteComplaint(Long id) {
        complaintRepo.deleteById(id);
    }
}
