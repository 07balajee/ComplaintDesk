package com.example.ComplaintDesk.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ComplaintDesk.model.Complaint;
import com.example.ComplaintDesk.model.User;

public interface ComplaintRepo extends JpaRepository<Complaint, Long> {
    List<Complaint> findByCreatedBy(User user);
    List<Complaint> findByStatus(String status);
    List<Complaint> findByCategory(String category);
}
