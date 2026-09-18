package com.example.student_api.service;


import com.example.student_api.Dto.StudentDTO;
import com.example.student_api.model.Role;
import com.example.student_api.model.Student;
import com.example.student_api.respository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
private final StudentRepository studentRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
      public StudentDTO create(Student dto){
        dto.setRole(Role.ADMIN);
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        Student store=studentRepository.save(dto);
        return new StudentDTO(store.getId(), store.getUsername());
  }
public List<StudentDTO> getAll(){
        return studentRepository.findAll().stream().map(student -> new StudentDTO(student.getId(),student.getUsername())).toList();
}
public StudentDTO getById(Long id){
        Student student=studentRepository.findById(id).orElseThrow(()->new RuntimeException("not found")        );
        return new StudentDTO(student.getId(),student.getUsername());
}
public  StudentDTO updateStud(Long id, StudentDTO dto){
        Student student=studentRepository.findById(id).orElseThrow(()->new RuntimeException("not found"));
        student.setUsername(dto.getName());
        Student studUpdate=studentRepository.save(student);
        return new StudentDTO(studUpdate.getId(), studUpdate.getUsername());
}

    public void deleteStud(Long id){
        studentRepository.deleteById(id);
    }



}
