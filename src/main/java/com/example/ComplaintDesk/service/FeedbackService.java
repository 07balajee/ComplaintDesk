package com.example.ComplaintDesk.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ComplaintDesk.model.Feedback;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.repo.FeedbackRepo;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public Feedback createFeedback(Feedback feedback) {
        feedback.setSubmittedAt(LocalDate.now());
        return feedbackRepo.save(feedback);
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepo.findAll();
    }

    public Feedback getFeedbackById(Long id) {
        return feedbackRepo.findById(id).orElse(null);
    }

    public List<Feedback> getFeedbackByComplaint(Complaint complaint) {
        return feedbackRepo.findByComplaint(complaint);
    }

    public List<Feedback> getFeedbackByUser(User user) {
        return feedbackRepo.findByUser(user);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {
        Optional<Feedback> existingFeedbackOpt = feedbackRepo.findById(id);
        if (existingFeedbackOpt.isPresent()) {
            Feedback existingFeedback = existingFeedbackOpt.get();
            existingFeedback.setRating(updatedFeedback.getRating());
            existingFeedback.setComments(updatedFeedback.getComments());
            return feedbackRepo.save(existingFeedback);
        }
        return null;
    }

    public void deleteFeedback(Long id) {
        feedbackRepo.deleteById(id);
    }
}
