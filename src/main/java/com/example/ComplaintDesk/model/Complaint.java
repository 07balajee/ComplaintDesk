package com.example.ComplaintDesk.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Entity
@Data
//@Table(name = "complaint")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private String status; //three types: open, solved, in progress

    private LocalDate createdAt;

    private String attachmentUrl;

    // Link to the user who filed the complaint
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy;

}
