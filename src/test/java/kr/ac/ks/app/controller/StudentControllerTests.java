package kr.ac.ks.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ks.app.domain.Student;
import kr.ac.ks.app.domain.StudentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    public void builderStudent() {
        student = Student.builder()
                .name("홍길동")
                .email("hong@test.com")
                .phoneNo("01012345678")
                .status(StudentStatus.ATTENDING)
                .build();
    }

    @Test
    public void createStudentHttpStateCode() throws Exception {

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void createStudentJSONFormat() throws Exception {

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }

    @Test
    public void createStudentHeaderURL() throws Exception {

        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").exists())
                .andExpect(jsonPath("email").exists())
                .andExpect(jsonPath("phoneNo").exists())
                .andExpect(jsonPath("status").exists());
    }

    @Test
    public void createStudentRepository() throws Exception {
        mockMvc.perform(post("/api/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(student)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("id").exists());
    }

}
