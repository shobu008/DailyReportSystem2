package com.techacademy;

import javax.persistence.Column;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="authentication")
public class Authentication {
    @Id
    @Column(length = 20, nullable = false)
    private String code;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 10, nullable = false)
    private String role;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employee_id;

}