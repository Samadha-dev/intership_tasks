package com.avirat.student.studentService.Impl;

import com.avirat.student.requestDTO.StudentRequestDTO;
import com.avirat.student.responseDTO.CourseResponseDTO;
import com.avirat.student.responseDTO.StudentResponseDTO;
import com.avirat.student.studentEntity.CourseEntity;
import com.avirat.student.studentEntity.StudentEntity;
import com.avirat.student.studentRepo.CourseRepository;
import com.avirat.student.studentRepo.StudentRepository;
import com.avirat.student.studentService.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    public StudentResponseDTO createStudent(StudentRequestDTO request) {
        StudentEntity student = modelMapper.map(request, StudentEntity.class);

        // Map courses from IDs
        if (request.getCourseRequestDTOS() != null && !request.getCourseRequestDTOS().isEmpty()){
            List<CourseEntity> courses = courseRepository.findAllById(request.getCourseRequestDTOS());
            student.setCourses(courses);
        }

        StudentEntity saved = studentRepository.save(student);
        return convertToResponse(saved);
    }

    // READ One
    public StudentResponseDTO getStudentById(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id"+id));
        return convertToResponse(student);
    }

    // READ All
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // UPDATE
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO request) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Map non-ID fields
        modelMapper.map(request, student);

        // Update courses
        if (request.getCourseRequestDTOS() != null && !request.getCourseRequestDTOS().isEmpty()) {
            List<CourseEntity> courses = courseRepository.findAllById(request.getCourseRequestDTOS());
            student.setCourses(courses);
        } else {
            student.setCourses(List.of()); // clear if none passed
        }

        StudentEntity updated = studentRepository.save(student);
        return convertToResponse(updated);
    }

    // DELETE
    public void deleteStudent(Long id) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // Helper to map entity -> response DTO
    private StudentResponseDTO convertToResponse(StudentEntity student) {
        StudentResponseDTO response = modelMapper.map(student, StudentResponseDTO.class);

        // Manually map courses
        List<CourseResponseDTO> courseDTOs = student.getCourses()
                .stream()
                .map(course -> modelMapper.map(course, CourseResponseDTO.class))
                .collect(Collectors.toList());

        response.setCourses(courseDTOs);
        return response;
    }
}
