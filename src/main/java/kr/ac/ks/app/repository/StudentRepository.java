package kr.ac.ks.app.repository;

import kr.ac.ks.app.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
