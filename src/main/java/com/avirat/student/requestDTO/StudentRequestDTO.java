package com.avirat.student.requestDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private String gender;

    private LocalDate dateOfBirth;

    private String phoneNo;

    private String address;

    private List<Long> courseRequestDTOS ;
}
