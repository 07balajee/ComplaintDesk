package com.example.ComplaintDesk.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ComplaintDesk.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
