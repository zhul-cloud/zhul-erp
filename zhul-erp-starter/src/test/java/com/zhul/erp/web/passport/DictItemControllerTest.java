package com.zhul.erp.web.passport;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONUtil;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemListQry;
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
public class DictItemControllerTest {

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
    String value = FileUtil.readUtf8String("json/dictItem-add-01.json");

    DictItemAddCmd cmd = JSONUtil.toBean(value, DictItemAddCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/web/v1/passport/dicts/{pid}/items", cmd.getPid())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void delete() {

    DictItemDeleteCmd cmd = new DictItemDeleteCmd();
    cmd.setId(5);

    mockMvc.perform(
        MockMvcRequestBuilders.delete("/api/web/v1/passport/dicts/item/{id}", cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void modify() {

    String value = FileUtil.readUtf8String("json/dictItem-modify-00.json");

    DictItemModifyCmd cmd = JSONUtil.toBean(value, DictItemModifyCmd.class);

    mockMvc.perform(
        MockMvcRequestBuilders
            .put("/api/web/v1/passport/dicts/{pid}/item/{id}", cmd.getPid(), cmd.getId())
            .content(JSONUtil.toJsonStr(cmd))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void list() {

    DictItemListQry qry = new DictItemListQry();
    qry.setPid(2);
    qry.setSearchKey("");

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/dicts/{pid}/items", qry.getPid())
            .param("searchKey", qry.getSearchKey())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }

  @SneakyThrows
  @Test
  public void dtl() {

    DictItemDtlQry qry = new DictItemDtlQry();
    qry.setId(4);

    mockMvc.perform(
        MockMvcRequestBuilders.get("/api/web/v1/passport/dicts/item/{id}", qry.getId())
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk()).andDo(print());
  }
}
