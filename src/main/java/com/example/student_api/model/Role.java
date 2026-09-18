package com.example.student_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN(
            Set.of(
                    Permission.STUDENT_READ, Permission.STUDENT_WRITE, Permission.STUDENT_DELETE
            )
    ),
    STUDENT(
            Set.of(
                    Permission.STUDENT_READ
            )
    );

    private final Set<Permission> permissions;



}
