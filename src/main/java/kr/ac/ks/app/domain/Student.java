package kr.ac.ks.app.domain;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(of = {"id", "name", "email", "phoneNo", "status"})
@EqualsAndHashCode(of = {"id"})
@Builder

public class Student {
    private Long id;
    private String name;
    private String email;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
