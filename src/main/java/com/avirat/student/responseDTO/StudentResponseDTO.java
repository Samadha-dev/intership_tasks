package com.avirat.student.responseDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int age;

    private String gender;

    private LocalDate dateOfBirth;

    private String phoneNo;

    private String address;

    private List<CourseResponseDTO> courses;
}
