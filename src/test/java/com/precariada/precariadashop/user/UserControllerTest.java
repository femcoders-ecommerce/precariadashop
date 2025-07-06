package com.precariada.precariadashop.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.precariada.precariadashop.dtos.users.UserResponse;
import com.precariada.precariadashop.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.precariada.precariadashop.dtos.users.UserRequest;
import org.mockito.Mockito;




import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("tests")

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

   @MockBean
   private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

  private List<UserResponse> userResponses;

  @BeforeEach
    void setUp(){
      userResponses = new ArrayList<>();
      UserResponse user1 = new UserResponse(1L, "Lara", "lara@correo.com", "Lara123.");
      UserResponse user2 = new UserResponse(2L,"Sofía","soffinutria@nutria.com", "Nutria321.");

      userResponses.add(user1);
      userResponses.add(user2);
  }

  @Test
  void shouldGetAllUsers() throws Exception {
      given(userService.getAllUsers()).willReturn(userResponses);

      mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.length()").value(userResponses.size()))
              .andExpect(jsonPath("$[0].id").value(userResponses.get(0).id()))
              .andExpect(jsonPath("$[0].username").value(userResponses.get(0).username()))
              .andExpect(jsonPath("$[0].email").value(userResponses.get(0).email()))
              .andExpect(jsonPath("$[1].id").value(userResponses.get(1).id()))
              .andExpect(jsonPath("$[1].username").value(userResponses.get(1).username()))
              .andExpect(jsonPath("$[1].email").value(userResponses.get(1).email()));
  }

    @Test
    void shouldGetUserById() throws Exception {
        UserResponse user = userResponses.get(0);
        given(userService.getUserById(user.id())).willReturn(user);

        mockMvc.perform(get("/api/users/{id}", user.id()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.id()))
                .andExpect(jsonPath("$.username").value(user.username()))
                .andExpect(jsonPath("$.email").value(user.email()));
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserRequest request = new UserRequest("newuser", "newuser@gmail.com", "Password1!.");
        UserResponse savedUser = new UserResponse(4L, "newuser", "newuser@gmail.com", "Password1!.");

        given(userService.addUser(Mockito.any(UserRequest.class))).willReturn(savedUser);

        String json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(savedUser.id()))
                .andExpect(jsonPath("$.username").value(savedUser.username()))
                .andExpect(jsonPath("$.email").value(savedUser.email()));
    }
    @Test
    void shouldUpdateUser() throws Exception {
        UserRequest updateRequest = new UserRequest("updatedUser", "updated@gmail.com", "NewPass1!@");
        UserResponse updatedUser = new UserResponse(4L, "updatedUser", "updated@gmail.com", "NewPass1!@");

        given(userService.updateUser(Mockito.eq(1L), Mockito.any(UserRequest.class))).willReturn(updatedUser);

        String json = objectMapper.writeValueAsString(updateRequest);

        mockMvc.perform(put("/api/users/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedUser.id()))
                .andExpect(jsonPath("$.username").value(updatedUser.username()))
                .andExpect(jsonPath("$.email").value(updatedUser.email()));
    }
    @Test
    void shouldDeleteUser() throws Exception {
        Mockito.doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
