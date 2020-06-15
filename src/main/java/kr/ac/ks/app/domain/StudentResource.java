package kr.ac.ks.app.domain;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

public class StudentResource extends EntityModel<Student> {
    public StudentResource(Student content, Link... links){
        super(content, links);
    }
}
