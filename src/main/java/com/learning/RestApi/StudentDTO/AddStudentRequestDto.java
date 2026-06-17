package com.learning.RestApi.StudentDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AddStudentRequestDto {


    @NotBlank (message = "can't be blank")
    @Length(min = 3 , max = 30, message = "should be 3 to 30")
    private String name;

    @Email
    @NotBlank(message = "could not be blank")
    private String email;
}