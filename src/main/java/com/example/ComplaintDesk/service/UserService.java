package com.example.ComplaintDesk.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ComplaintDesk.model.User;
import com.example.ComplaintDesk.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOpt = userRepo.findById(id);
        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setCreatedAt(updatedUser.getCreatedAt());
            return userRepo.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    public List<User> getUsersByName(String name) {
        return userRepo.findByName(name);
    }
}
