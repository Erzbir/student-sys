package com.erzbir.backend.controller;

import com.erzbir.backend.annotation.JsonResponse;
import com.erzbir.backend.entity.Student;
import com.erzbir.backend.service.StudentService;
import com.erzbir.backend.util.Response;
import org.springframework.web.bind.annotation.*;

/**
 * @author Erzbir
 * @Data: 2024/5/29 09:53
 */
@RestController
@RequestMapping(value = "/student", produces = "application/json")
@JsonResponse
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public Object list() {
        return Response.ok(studentService.list());
    }

    @GetMapping("/get")
    public Object get(@RequestParam Long id) {
        return Response.ok(studentService.getById(id));
    }

    @PostMapping("/add")
    public Object add(@RequestBody Student student) {
        if (studentService.getById(student.getId()) != null) {
            return Response.error("Student already exists");
        }
        return Response.ok(studentService.save(student));
    }

    @PostMapping("/update")
    public Object update(@RequestBody Student student) {
        return Response.ok(studentService.updateById(student));
    }

    @GetMapping("/delete")
    public Object delete(@RequestParam Long id) {
        return Response.ok(studentService.removeById(id));
    }
}
