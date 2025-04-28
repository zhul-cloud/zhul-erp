package com.zhul.erp.client.api.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.dto.passport.cqrs.command.DictAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictListVO;
import java.util.List;
import java.util.Map;

/**
 * Created by yanglikai on 2023/12/17.
 */
public interface IDictService {

  /**
   * 新增字典
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean add(DictAddCmd cmd);

  /**
   * 删除字典
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean delete(DictDeleteCmd cmd);

  /**
   * 修改字典
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(DictModifyCmd cmd);

  /**
   * 字典列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  Page<DictListVO> list(DictListQry qry);

  /**
   * 字典详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  DictDtlVO dtl(DictDtlQry qry);

  List<Keyvalue> dropdownList(String code);


  Map<String,String> mapKeyValueByCodes(String codes);

  Map<String,String> mapValueKeyBycode(String codes);
}
