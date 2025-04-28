package com.zhul.erp.client.api.passport;

import com.zhul.erp.client.dto.passport.cqrs.command.ResourceAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.ResourceModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.ResourceListQry;
import com.zhul.erp.client.dto.passport.viewobject.ResourceDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.ResourceListVO;
import java.util.List;

/**
 * Created by yanglikai on 2023/12/17.
 */
public interface IResourceService {

  /**
   * 新增资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean add(ResourceAddCmd cmd);

  /**
   * 删除资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean delete(ResourceDeleteCmd cmd);

  /**
   * 修改资源
   * <p></p>
   *
   * @param cmd
   * @return
   */
  Boolean modify(ResourceModifyCmd cmd);

  /**
   * 资源列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  List<ResourceListVO> list(ResourceListQry qry);

  /**
   * 资源详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  ResourceDtlVO dtl(ResourceDtlQry qry);
}
