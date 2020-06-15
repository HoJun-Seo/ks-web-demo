package kr.ac.ks.app.domain;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class StudentDtoValid {
    public void validate(StudentDto studentDto, Errors errors){
        if (studentDto.getName().matches("^\\s*$")){ //하나라도 연속된 공백이 있으면 에러에 담는다.
            errors.rejectValue("name", "wrongValue", "Wrong Name");
        }
    }
}
