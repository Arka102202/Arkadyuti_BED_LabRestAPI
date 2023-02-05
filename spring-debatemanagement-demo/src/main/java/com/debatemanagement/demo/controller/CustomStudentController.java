package com.debatemanagement.demo.controller;

import com.debatemanagement.demo.entity.Student;
import com.debatemanagement.demo.entity.UserResponse;
import com.debatemanagement.demo.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Component
@RequestMapping("/student-dash-board")
public class CustomStudentController {

    @Autowired
    private StudentService studentService;

    Logger logger = LoggerFactory.getLogger(CustomStudentController.class);
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public UserResponse getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserResponse user = new UserResponse();
        user.setUsername(authentication.getName());
        user.setRole(
                new ArrayList<GrantedAuthority>(authentication
                        .getAuthorities())
                        .get(0).getAuthority()
        );
        return user;
    }

    @RequestMapping("/students")
    public String showTickets(Model model) {

        logger.info(ANSI_WHITE + "something" + ANSI_RESET);

        model.addAttribute
                ("students",
                        studentService.findAll(
                                Sort.by(Sort.Direction.ASC, "firstName")
                        )
                );
        model.addAttribute("name", "");
        model.addAttribute("user", getUserDetails());
        return "student-list";
    }

    @GetMapping("/edit-page/{id}")
    public String showEditPage(@PathVariable("id") int id, Model model, @ModelAttribute("user") UserResponse user) {
        model.addAttribute("user", getUserDetails());
        if (getUserDetails().getRole().equals("USER") && id != 0)
            return "error";
        if (id > 0) model.addAttribute("student", studentService.findById(id));
        else model.addAttribute("student", new Student());
        return "edit-page";
    }

    @GetMapping("/view-page/{id}")
    public String showViewPage(@PathVariable("id") int id, Model model) {

        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("user", getUserDetails());
        return "view-page";
    }

    @PostMapping("/new-update-student")
    public String createNewTicket(@ModelAttribute("student") Student student) {
        studentService.addOrUpdateTicket(student);
        return "redirect:/student-dash-board/students";
    }

    @RequestMapping("/delete-student/{id}")
    public String deleteTicket(@PathVariable("id") int id) {
        UserResponse user = getUserDetails();
        studentService.deleteTicket(studentService.findById(id));
        return "redirect:/student-dash-board/students";
    }

    @RequestMapping("/error")
    public String accessDenied(Model model) {
        model.addAttribute("user", getUserDetails());
        return "error";
    }
}