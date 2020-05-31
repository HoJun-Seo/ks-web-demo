package kr.ac.ks.app.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString(of = {"id", "name", "email", "phoneNo", "status"})
@EqualsAndHashCode(of = {"id"})
@Builder
@Entity

public class Student {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String phoneNo;

    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
