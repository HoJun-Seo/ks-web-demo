package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.domain.StudentDto;
import kr.ac.ks.app.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/api/students", produces = MediaTypes.HAL_JSON_VALUE)
public class StudentController {

    private final StudentRepository studentRepository;

    private final ModelMapper modelMapper;

    public StudentController(StudentRepository studentRepository, ModelMapper modelMapper){
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody StudentDto studentDto){ //ResponseEntity : 반환값을 무조건 json 으로 변환
        Student student = modelMapper.map(studentDto, Student.class); // studentDto 를 Student class 에 맞춰서 알아서 생성해라.
        Student savedStudent = studentRepository.save(student);
        URI uri = linkTo(StudentController.class).slash(savedStudent.getId()).toUri(); //링크를 달아준다 : http://localhost/api/students/100, uri 와 내용물을 함께 반환하겠다.
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);//HTTP 상태코드는 201 로 내보내라
        return ResponseEntity.created(uri).body(savedStudent);
    }
}
