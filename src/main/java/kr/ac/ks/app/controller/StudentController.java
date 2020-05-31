package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.repository.StudentRepository;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
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

    private StudentRepository studentRepository;
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public ResponseEntity createStudent(@RequestBody Student student){ //ResponseEntity : 반환값을 무조건 json 으로 변환
        Student savedStudent = studentRepository.save(student);
        URI uri = linkTo(StudentController.class).slash(savedStudent.getId()).toUri(); //링크를 달아준다 : http://localhost/api/students/100, uri 와 내용물을 함께 반환하겠다.
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);//HTTP 상태코드는 201 로 내보내라
        return ResponseEntity.created(uri).body(savedStudent);
    }
}
