package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.UserDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.UserListQry;
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
public class UserControllerTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @SneakyThrows
  @Test
  public void add() {
    String value = FileUtil.readUtf8String("json/user-add-00.json");

    UserAddCmd cmd = JSONUtil.toBean(value, UserAddCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/users").content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    UserDeleteCmd cmd = new UserDeleteCmd();
    cmd.setId(1000000005);

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/users/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {

    String value = FileUtil.readUtf8String("json/user-modify-00.json");

    UserModifyCmd cmd = JSONUtil.toBean(value, UserModifyCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/passport/users/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    UserListQry qry = new UserListQry();
    qry.setType(0);
    qry.setUsername("");
    qry.setName("");
    qry.setPhone("");
    qry.setPids(Lists.newArrayList());
    qry.setStatus(0);
    qry.setPage(0);
    qry.setSize(0);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/users")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    UserDtlQry qry = new UserDtlQry();
    qry.setId(1000000006);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/users/{id}", qry.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dropdownList() {

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/users/dropdown-list")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
