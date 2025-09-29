package com.avirat.student.requestDTO;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRequestDTO {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "last name is required")
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @Min(value = 12, message = "Age should be at least 12")
    @Max(value = 30, message = "Age should be less than 30")
    private int age;

    private String gender;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    private String phoneNo;

    private String address;

    private List<Long> courseRequestDTOS ;
}
