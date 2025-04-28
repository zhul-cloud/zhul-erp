package com.zhul.erp.application.service.passport;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.erp.client.api.passport.IDictItemService;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictItemModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictItemListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictItemDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictItemListVO;
import com.zhul.erp.domain.mapper.MapperExpand;
import com.zhul.erp.domain.model.entity.Dict;
import com.zhul.erp.domain.model.repository.IDictRepository;
import com.zhul.erp.domain.model.value.DictTypeEnum;
import com.zhul.erp.domain.model.value.StatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Service
public class DictItemServiceImpl implements IDictItemService {

  @Autowired
  private IDictRepository dictRepository;

  /**
   * 新增字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean add(DictItemAddCmd cmd) {

    // 1.创建字典项
    Dict dictItem = Mapper.map(cmd, Dict.class);
    dictItem.setType(DictTypeEnum.DICT_ITEM.code());

    // 2.数据持久化
    this.dictRepository.save(dictItem);

    return true;
  }

  /**
   * 删除字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean delete(DictItemDeleteCmd cmd) {

    this.dictRepository.removeById(cmd.getId());

    return true;
  }

  /**
   * 修改字典项
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean modify(DictItemModifyCmd cmd) {

    // 1.更新字典项
    Dict dictItem = Mapper.map(cmd, Dict.class);

    // 2.数据持久化
    this.dictRepository.updateById(dictItem);

    return true;
  }

  /**
   * 字典项列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public Page<DictItemListVO> list(DictItemListQry qry) {

    // 1.查询数据
    Page<Dict> pages = this.dictRepository
        .page(qry.tPage(), new LambdaQueryWrapper<Dict>()
            .eq(Dict::getPid, qry.getPid())
            .eq(StringUtils.isNotBlank(qry.getSearchKey()), Dict::getCode, qry.getSearchKey()).or()
            .like(StringUtils.isNotBlank(qry.getSearchKey()), Dict::getName, qry.getSearchKey())
            .orderByDesc(Dict::getCreateTime));

    // 2.数据转换
    return MapperExpand.mapToPage(pages, (el -> {
      DictItemListVO vo = Mapper.map(el, DictItemListVO.class);

      vo.setStatusName(StatusEnum.transform(el.getStatus()).desc());

      return vo;
    }));
  }

  /**
   * 字典项详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public DictItemDtlVO dtl(DictItemDtlQry qry) {

    // 1.查询数据
    Dict dict = this.dictRepository.lambdaQuery().eq(Dict::getId, qry.getId()).one();

    // 2.数据转换
    return Mapper.map(dict, DictItemDtlVO.class);
  }
}
