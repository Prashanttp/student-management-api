package com.learning.RestApi.Service.impl;
import com.learning.RestApi.Service.StudentService;
import com.learning.RestApi.StudentDTO.AddStudentRequestDto;
import com.learning.RestApi.StudentDTO.StudentDto;
import com.learning.RestApi.entity.Student;
import com.learning.RestApi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .toList();
    }
    @Override
    public StudentDto getAllStudentsById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No student id available: " + id));
        return modelMapper.map(student, StudentDto.class);
    }
    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto , Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student , StudentDto.class);
    }
    @Override
    public void deleteStudentById(Long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not existed by id: " + id);
        }
         studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Long id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No student to update available: " + id));
        modelMapper.map(addStudentRequestDto , student);
        student = studentRepository.save(student);
        return modelMapper.map(student , StudentDto.class);
    }

    @Override
    public StudentDto updatePartialStudent(Long id, Map<String, Object> updates) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Student not found with ID: " + id));

        updates.forEach((String field, Object value) -> {

            switch (field) {

                case "name":
                    student.setName((String) value);
                    break;

                case "email":
                    student.setEmail((String) value);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Field is not supported"
                    );
            }
        });

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentDto.class);
    }
}
