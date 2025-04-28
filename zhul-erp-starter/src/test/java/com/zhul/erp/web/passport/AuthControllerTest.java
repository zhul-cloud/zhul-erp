package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.client.dto.passport.cqrs.command.LoginCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.PasswordModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.SignupCmd;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by yanglikai on 2023/2/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AuthControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @SneakyThrows
  @Test
  public void signup() {
    String value = FileUtil.readUtf8String("json/auth-signup-00.json");

    SignupCmd cmd = JSONUtil.toBean(value, SignupCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/auth/sign-up").content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void login() {
    LoginCmd cmd = new LoginCmd();
    cmd.setUsername("yanglikai");
    cmd.setPassword("yanglikai");

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/auth/login").content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void logout() {
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/auth/logout")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void resetPassword() {

    Integer userId = 1000000;

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/auth/password-reset/{userId}", userId)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void updatePassword() {

    PasswordModifyCmd cmd = new PasswordModifyCmd();
    cmd.setOldPassword("12345678");
    cmd.setNewPassword("12345678");

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/auth/password-update")
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
