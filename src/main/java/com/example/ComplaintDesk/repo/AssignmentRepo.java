package com.example.ComplaintDesk.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ComplaintDesk.model.Assignment;
import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.model.Complaint;

public interface AssignmentRepo extends JpaRepository<Assignment, Long> {
    List<Assignment> findByAssignedTo(User user);
    List<Assignment> findByAssignedBy(User user);
    List<Assignment> findByComplaint(Complaint complaint);
    List<Assignment> findByStatus(String status);
}
