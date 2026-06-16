package com.learning.RestApi.Service;

import com.learning.RestApi.StudentDTO.AddStudentRequestDto;
import com.learning.RestApi.StudentDTO.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
   List<StudentDto> getAllStudents();

    StudentDto getAllStudentsById(Long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Long id);

    StudentDto updateStudent(Long id , AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartialStudent(Long id, Map<String, Object> updates);
}
