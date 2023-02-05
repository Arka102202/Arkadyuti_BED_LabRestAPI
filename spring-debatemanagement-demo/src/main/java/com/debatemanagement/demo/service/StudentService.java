package com.debatemanagement.demo.service;

import com.debatemanagement.demo.entity.Student;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface StudentService {

    List<Student> findAll(Sort sort);

    Student findById(int id);

    Student addOrUpdateTicket(Student student);

    void deleteTicket(Student student);

}
