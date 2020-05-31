package kr.ac.ks.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.domain.StudentStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//위의 두 annotation 은 필수로 알아야 한다.
public class StudentControllerTests {
    //URL -> 접속 -> 결과 -> 비교

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; //객체 JSON 변환을 위한 선언

    Student student = Student.builder()
            .id(100L)
            .name("홍길동")
            .email("hong@test.com")
            .phoneNo("01012345678")
            .status(StudentStatus.ATTENDING)
            .build();
    //학생 정보를 만든다.


    @Test //응답이 JSON 으로 돌아오는지 및 HTTP 응답 코드 201이 나오는지 확인
    public void createStudentHTTPCode() throws Exception {


        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON) //JSON 응답 확인
                .accept(MediaTypes.HAL_JSON) //주는 것도 JSON 으로 전달
                .content(objectMapper.writeValueAsString(student))
        )
                .andDo(print()) //상태코드와 JSON 응답이 정확한지 확인하기 위한 출력문
                .andExpect(status().isCreated()); //상태 코드 확인
    }

    @Test
    public void createStudentJSONFormat()throws Exception{

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student))) //Object -> JSON 변환
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }

    @Test //Locaton 헤더에 생성된 이벤트를 조회 할 수 있는 URI 가 담겨 있는지 확인한다.
    public void createStudentHeader() throws Exception{

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }

    @Test //id 는 DB 에 들어갈 때 자동생성된 값으로 나오는 지 확인한다.
    public void createStudentRepository() throws Exception{
        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").value(Matchers.not(100)));
    }
}
