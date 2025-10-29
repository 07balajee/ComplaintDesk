package com.example.ComplaintDesk.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity//object relational mapping is done
@Data//creation of getters and setters
//@Table(name = "assignments")it is not necessary if class name and table name are same
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Complaint entity
    @ManyToOne
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    // Link to Staff user (who will solve the complaint)
    @ManyToOne
    @JoinColumn(name = "assigned_to", nullable = false)
    private User assignedTo;

    // Link to Admin user (who assigned the task)
    @ManyToOne
    @JoinColumn(name = "assigned_by", nullable = false)
    private User assignedBy;

    private LocalDate assignedAt;

    private String status; //assigned to staff,in progress,completed
}