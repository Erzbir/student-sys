package com.erzbir.backend.controller;

import com.erzbir.backend.entity.Student;
import com.erzbir.backend.service.StudentService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@RestController
@RequestMapping(value = "/student", produces = "application/json")
//@Response
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public List<Student> list() {
        return studentService.list();
    }

    @GetMapping("/get")
    public Response<Student> get(@RequestParam Long id) {
        return Response.ok(studentService.getById(id));
    }

    @PostMapping("/add")
    public Response<Boolean> add(@RequestBody Student student) {
        if (studentService.getById(student.getId()) != null) {
            return Response.error("Student already exists");
        }
        return Response.ok(studentService.save(student));
    }

    @PostMapping("/update")
    public Response<Boolean> update(@RequestBody Student student) {
        return Response.ok(studentService.updateById(student));
    }

    @GetMapping("/delete")
    public Response<Boolean> delete(@RequestParam Long id) {
        return Response.ok(studentService.removeById(id));
    }
}
