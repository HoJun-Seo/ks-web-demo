package kr.ac.ks.app.controller;

import kr.ac.ks.app.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
    @PostMapping("/api/students/")
    public ResponseEntity createStudent(){ //ResponseEntity : 반환값을 무조건 json 으로 변환
        Student student = new Student();
        return ResponseEntity.status(HttpStatus.CREATED).body(student);//HTTP 상태코드는 201 로 내보내라
    }
}
