package com.example.student_api.controller;

import com.example.student_api.dto.StudentDTO;
import com.example.student_api.model.Student;
import com.example.student_api.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-training")
public class HelloController {
private final StudentService studentService;


    public HelloController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/createStud")
    public StudentDTO create(@RequestBody Student dto){
        return studentService.create(dto);
    }
    @GetMapping("/getAll")
    public List<StudentDTO> getAll(){
        return studentService.getAll();
    }
    @GetMapping("/getById/{id}")
    public StudentDTO getById(@PathVariable Long id){
        return studentService.getById(id);
    }
    @PutMapping("/updateStud/{id}")
    public StudentDTO updateStud(@PathVariable Long id, @RequestBody StudentDTO dto){return studentService.updateStud(id,dto);}
    @DeleteMapping("/deleteStud/{id}")
    public void deleteStud(@PathVariable Long id){ studentService.deleteStud(id);}
    @GetMapping("/health")
    public String healthCheck(){
        return "Healthy";
    }



}
