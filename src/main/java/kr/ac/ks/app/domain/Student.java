package kr.ac.ks.app.domain;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = "id")
@Builder
public class Student {
    private Long id;
    private String name;
    private String email;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
