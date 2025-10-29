package com.example.ComplaintDesk.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ComplaintDesk.model.Feedback;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
    List<Feedback> findByComplaint(Complaint complaint);
    List<Feedback> findByUser(User user);
}
