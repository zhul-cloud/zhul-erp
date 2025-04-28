package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.client.dto.passport.cqrs.command.DictAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictListQry;
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
public class DictControllerTest {

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
    String value = FileUtil.readUtf8String("json/dict-add-00.json");

    DictAddCmd cmd = JSONUtil.toBean(value, DictAddCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/dicts").content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    DictDeleteCmd cmd = new DictDeleteCmd();
    cmd.setId(1);

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/dicts/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {

    String value = FileUtil.readUtf8String("json/dict-modify-00.json");

    DictModifyCmd cmd = JSONUtil.toBean(value, DictModifyCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.put("/api/web/v1/passport/dicts/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    DictListQry qry = new DictListQry();
    qry.setSearchKey("");

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/dicts")
            .param("searchKey", qry.getSearchKey())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    DictDtlQry qry = new DictDtlQry();
    qry.setId(2);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/dicts/{id}", qry.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
