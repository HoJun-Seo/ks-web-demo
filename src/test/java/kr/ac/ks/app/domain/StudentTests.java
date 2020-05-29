package kr.ac.ks.app.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTests {
    @Test
    public void testcreateStudentObject(){
        Student student = new Student();
        assertThat(student).isNotNull();
    }

    @Test
    public void testStudentBean(){
        Long id = 100L;
        String name = "홍길동1";
        String email = "home@test.com";
        String phoneNo = "01012345678";

        Student student = new Student();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhoneNo(phoneNo);

        assertThat(student.getId()).isEqualTo(id);
        assertThat(student.getName()).isEqualTo(name);
        assertThat(student.getEmail()).isEqualTo(email);
        assertThat(student.getPhoneNo()).isEqualTo(phoneNo);
    }
    @Test
    public void testStudentBulider(){
        // Given
        String name = "홍길동1";
        String email = "home@test.com";
        String phoneNo = "01012345678";

        // When
        Student student = Student.builder()
                .name(name)
                .email(email)
                .phoneNo(phoneNo)
                .build();

        // Then
        assertThat(student.getName()).isEqualTo(name);
        assertThat(student.getEmail()).isEqualTo(email);
        assertThat(student.getPhoneNo()).isEqualTo(phoneNo);
    }

    @Test
    public void testStudentStatus() {
        // Given
        String name = "홍길동1";
        String email = "home@test.com";
        String phoneNo = "01012345678";
        StudentStatus status = StudentStatus.ATTENDING;

        // When
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);
        student.setPhoneNo(phoneNo);
        student.setStatus(status);
        

        // Then
        assertThat(student.getName()).isEqualTo(name);
        assertThat(student.getEmail()).isEqualTo(email);
        assertThat(student.getPhoneNo()).isEqualTo(phoneNo);
        assertThat(student.getStatus()).isEqualTo(status);
    }
}
