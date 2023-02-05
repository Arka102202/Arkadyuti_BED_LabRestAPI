package com.debatemanagement.demo.service;

import com.debatemanagement.demo.entity.Student;
import com.debatemanagement.demo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public List<Student> findAll(Sort sort) {
        return studentRepo.findAll(sort);
    }

    @Override
    public Student findById(int id) {
        return studentRepo.findById(id).get();
    }

    @Override
    public Student addOrUpdateTicket(Student ticket) {
        return studentRepo.save(ticket);
    }

    @Override
    public void deleteTicket(Student ticket) {
        studentRepo.delete(ticket);
    }
}
