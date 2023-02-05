package com.debatemanagement.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false)
    private int id;
    @Column(name = "student_first_name", nullable = false)
    private String firstName;
    @Column(name = "student_last_name", nullable = false)
    private String lastName;
    @Column(name = "student_course", nullable = false)
    private String course;
    @Column(name = "student_country", nullable = false)
    private String country;
}
