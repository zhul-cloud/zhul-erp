package com.zhul.erp.client.api.passport;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictItemDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictItemListVO;

/**
 * Created by yanglikai on 2023/12/17.
 */
public interface IDictItemService {

  /**
   * 新增字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean add(DictItemAddCmd cmd);

  /**
   * 删除字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean delete(DictItemDeleteCmd cmd);

  /**
   * 修改字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(DictItemModifyCmd cmd);

  /**
   * 字典项列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  Page<DictItemListVO> list(DictItemListQry qry);

  /**
   * 字典项详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  DictItemDtlVO dtl(DictItemDtlQry qry);
}
