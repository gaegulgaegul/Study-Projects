package com.gaegul;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.gaegul.ApiDocumentUtils.getDocumentRequest;
import static com.gaegul.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final List<User> userList = List.of(
            new User(1L, "홍길동1", 10),
            new User(2L,"홍길동2", 11),
            new User(3L,"홍길동3", 12),
            new User(4L,"홍길동4", 13),
            new User(5L,"홍길동5", 14),
            new User(6L,"홍길동6", 15),
            new User(7L,"홍길동7", 16),
            new User(8L,"홍길동8", 17),
            new User(9L,"홍길동9", 18),
            new User(10L,"홍길동10", 19)
    );

    @Test
    @DisplayName("User 리스트 조회")
    void getUsers() throws Exception {
        // given
        given(userService.getUsers()).willReturn(userList);

        // when

        // then
        mockMvc.perform(get("/api/user/list"))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("get-users-200-ok",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        responseFields(
                                fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("[].name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("[].age").type(JsonFieldType.NUMBER).description("나이")
                        )));
    }

    @Test
    @DisplayName("User 조회")
    void getUser() throws Exception {
        // given
        Long id = 3L;
        User user = userList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .get();
        given(userService.getUser(any())).willReturn(user);

        // when
        // then
        mockMvc.perform(get("/api/user?id=", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(print())
                .andDo(document("get-user-with-id-200-ok",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestParameters(
                                parameterWithName("id").description("아이디")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        )));
    }

    @Test
    @DisplayName("User 생성 OK")
    void saveUser() throws Exception {
        // given
        User user = new User(11L, "홍길동11", 20);
        given(userService.saveUser(any())).willReturn(user);

        // when

        // then
        mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(print())
                .andDo(document("post-user-200-ok",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        )));
    }

    @Test
    @DisplayName("User 수정 OK")
    void updateUser() throws Exception {
        // given
        User user = new User(3L, "홍길동333", 30);
        given(userService. updateUser(any())).willReturn(user);

        // when

        // then
        mockMvc.perform(put("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.age").value(user.getAge()))
                .andDo(print())
                .andDo(document("put-user-200-ok",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("아이디"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                                fieldWithPath("age").type(JsonFieldType.NUMBER).description("나이")
                        )));
    }

    @Test
    @DisplayName("User 삭제 OK")
    void deleteUser() throws Exception {
        // given
        Long id = 4L;

        // when

        // then
        mockMvc.perform(delete("/api/user/{id}", id))
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("delete-user-200-ok",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("id").description("아이디")
                        )));
    }
}