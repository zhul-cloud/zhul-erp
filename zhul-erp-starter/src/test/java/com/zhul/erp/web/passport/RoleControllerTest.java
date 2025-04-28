package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.RoleModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.RoleListQry;
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
public class RoleControllerTest {

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
    String value = FileUtil.readUtf8String("json/role-add-00.json");

    RoleAddCmd cmd = JSONUtil.toBean(value, RoleAddCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/roles").content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    RoleDeleteCmd cmd = new RoleDeleteCmd();
    cmd.setId(1);

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/roles/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {

    String value = FileUtil.readUtf8String("json/role-modify-00.json");

    RoleModifyCmd cmd = JSONUtil.toBean(value, RoleModifyCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/passport/roles/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    RoleListQry qry = new RoleListQry();
    qry.setCode("");
    qry.setName("");
    qry.setStatus(0);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/roles")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    RoleDtlQry qry = new RoleDtlQry();
    qry.setId(1);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/roles/{id}", qry.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
