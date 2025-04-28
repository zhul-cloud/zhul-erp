package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceListQry;
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
public class ResourceControllerTest {

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
    String value = FileUtil.readUtf8String("json/resource-add-01.json");

    ResourceAddCmd cmd = JSONUtil.toBean(value, ResourceAddCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/resources")
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    ResourceDeleteCmd cmd = new ResourceDeleteCmd();
    cmd.setId(100003);

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/resources/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {
    String value = FileUtil.readUtf8String("json/resource-modify-00.json");

    ResourceModifyCmd cmd = JSONUtil.toBean(value, ResourceModifyCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/passport/resources/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    ResourceListQry qry = new ResourceListQry();
    qry.setType("1");

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/resources")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    ResourceDtlQry qry = new ResourceDtlQry();
    qry.setId(100002);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/resources/{id}", qry.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void tree() {

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/resources/tree")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
