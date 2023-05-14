package com.techacademy.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;


@Data
@Entity
@Table(name="employee")
@Where(clause = "delete_flag = 0")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20, nullable = false)
    private String name;

    private Integer delete_flag;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private Authentication authentication;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Report> reports;
}