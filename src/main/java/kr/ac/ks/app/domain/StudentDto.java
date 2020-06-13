package kr.ac.ks.app.domain;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/*@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor*/
@Data // Getter, Setter, ToString, HashAndEqual, AllArgument 등등의 annotation 을 여러개 붙일 필요 없이 Data annotation 하나로 한번에 해결해준다.
//반면 entity 파트에서는 Data annotation 을 쓰면 안된다. 왜? - 무한루프에 빠질 가능성이 있음
// Dto 이고 DB에 들어갈 일이 없기 때문에 Data annotation 을 사용할 수 있다.
@Builder

public class StudentDto {
    private String name;
    private String email;
    private String phoneNo;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
}
