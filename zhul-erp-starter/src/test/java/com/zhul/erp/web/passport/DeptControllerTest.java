package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.domain.model.entity.Department;
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
public class DeptControllerTest {

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
    String value = FileUtil.readUtf8String("json/dept-add-01.json");

    Department department = JSONUtil.toBean(value, Department.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/depts")
            .content(JSONUtil.toJsonStr(department))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    Integer id = 2;

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/depts/{id}", id)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {

    String value = FileUtil.readUtf8String("json/dept-modify-00.json");

    Department department = JSONUtil.toBean(value, Department.class);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/passport/depts/{id}", department.getId())
            .content(JSONUtil.toJsonStr(department))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    Department department = new Department();
    department.setPid(0);
    department.setCode("");
    department.setName("");
    department.setAllName("");
    department.setLeaderId(0);
    department.setStatus(0);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/depts")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    Integer id = 1;

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/depts/{id}", id)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void tree() {

    String searchKey = "人事";

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/depts/tree").param("searchKey", searchKey)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
