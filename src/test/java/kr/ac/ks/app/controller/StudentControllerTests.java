package kr.ac.ks.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.domain.StudentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//위의 두 annotation 은 필수로 알아야 한다.
public class StudentControllerTests {
    //URL -> 접속 -> 결과 -> 비교

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper; //객체 JSON 변환을 위한 선언
    
    @Test //응답이 JSON 으로 돌아오는지 및 HTTP 응답 코드 201이 나오는지 확인
    public void createStudentHTTPCode() throws Exception {

        Student student = Student.builder()
                .id(100L)
                .name("홍길동")
                .email("hong@test.com")
                .phoneNo("01012345678")
                .status(StudentStatus.ATTENDING)
                .build();

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON) //JSON 응답 확인
                .accept(MediaType.APPLICATION_JSON) //주는 것도 JSON 으로 전달
                .content(objectMapper.writeValueAsString(student))
        )
                .andDo(print()) //상태코드와 JSON 응답이 정확한지 확인하기 위한 출력문
                .andExpect(status().isCreated()); //상태 코드 확인
    }

    @Test
    public void createStudentJSONFormat()throws Exception{
        //학생 정보를 만들어서
        //결과가 JSON 인지 확인한다.
        Student student = Student.builder()
                .id(100L)
                .name("홍길동")
                .email("hong@test.com")
                .phoneNo("01012345678")
                .status(StudentStatus.ATTENDING)
                .build();

        //전달해야 한다.(POST)
        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(student))) //Object -> JSON 변환
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }
}
