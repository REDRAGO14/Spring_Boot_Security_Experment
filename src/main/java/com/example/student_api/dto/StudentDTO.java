package com.example.student_api.dto;

public class StudentDTO {
    private Long id;
    private String  name;

    public StudentDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
    return  name;

    }
}
