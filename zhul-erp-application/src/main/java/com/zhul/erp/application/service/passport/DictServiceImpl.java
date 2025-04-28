package com.zhul.erp.application.service.passport;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.api.passport.IDictService;
import com.zhul.erp.client.dto.passport.cqrs.command.DictAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.DictModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.DictDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.DictListQry;
import com.zhul.erp.client.dto.passport.viewobject.DictDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.DictListVO;
import com.zhul.erp.domain.mapper.MapperExpand;
import com.zhul.erp.domain.model.entity.Dict;
import com.zhul.erp.domain.model.repository.IDictRepository;
import com.zhul.erp.domain.model.value.DictTypeEnum;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Service
public class DictServiceImpl implements IDictService {

    @Autowired
    private IDictRepository dictRepository;

    /**
     * 新增字典
     * <p></p>
     *
     * @param cmd
     * @return
     */
    @Override
    public Boolean add(DictAddCmd cmd) {

        // 1.创建字典
        Dict dict = Mapper.map(cmd, Dict.class);
        dict.setType(DictTypeEnum.DICT.code());

        // 2.数据持久化
        this.dictRepository.save(dict);

        return true;
    }

    /**
     * 删除字典
     * <p></p>
     *
     * @param cmd
     * @return
     */
    @Override
    public Boolean delete(DictDeleteCmd cmd) {

        this.dictRepository.removeById(cmd.getId());

        return true;
    }

    /**
     * 修改字典
     * <p></p>
     *
     * @param cmd
     * @return
     */
    @Override
    public Boolean modify(DictModifyCmd cmd) {

        // 1.更新字典
        Dict dict = Mapper.map(cmd, Dict.class);

        // 2.数据持久化
        this.dictRepository.updateById(dict);

        return true;
    }

    /**
     * 字典列表
     * <p></p>
     *
     * @param qry
     * @return
     */
    @Override
    public Page<DictListVO> list(DictListQry qry) {

        // 1.查询数据
        Page<Dict> pages = this.dictRepository
                .page(qry.tPage(), new LambdaQueryWrapper<Dict>()
                        .eq(Dict::getPid, 0)
                        .eq(StringUtils.isNotBlank(qry.getSearchKey()), Dict::getCode, qry.getSearchKey()).or()
                        .like(StringUtils.isNotBlank(qry.getSearchKey()), Dict::getName, qry.getSearchKey())
                        .orderByDesc(Dict::getCreateTime));

        // 2.数据转换
        return MapperExpand.mapToPage(pages, (el -> Mapper.map(el, DictListVO.class)));
    }

    /**
     * 字典详情
     * <p></p>
     *
     * @param qry
     * @return
     */
    @Override
    public DictDtlVO dtl(DictDtlQry qry) {

        // 1.查询数据
        Dict dict = this.dictRepository.lambdaQuery().eq(Dict::getId, qry.getId()).one();

        // 2.数据转换
        return Mapper.map(dict, DictDtlVO.class);
    }

    @Override
    public List<Keyvalue> dropdownList(String code) {
        if (StrUtil.isEmpty(code)) {
            return Lists.newArrayList();
        }
        Dict dict = dictRepository.lambdaQuery().eq(Dict::getCode, code).one();
        List<Keyvalue> keyvalues = Lists.newArrayList();
        if (null == dict) {
            return keyvalues;
        }
        List<Dict> list = dictRepository.lambdaQuery().eq(Dict::getPid, dict.getId()).list();
        for (Dict item : list) {
            Keyvalue keyvalue = new Keyvalue();
            keyvalue.setKey(item.getValue());
            keyvalue.setValue(item.getName());
            keyvalues.add(keyvalue);
        }
        return keyvalues;
    }

    @Override
    public Map<String, String> mapKeyValueByCodes(String code) {
        List<Keyvalue> keyvalues = this.dropdownList(code);
        if (CollectionUtil.isEmpty(keyvalues)){
            return Maps.newHashMap();
        }
        Map<String, String> map = new HashMap<>();
        for (Keyvalue keyvalue : keyvalues) {
            map.put(keyvalue.getKey(),keyvalue.getValue());
        }
        return map;
    }

    @Override
    public Map<String, String> mapValueKeyBycode(String code) {
        List<Keyvalue> keyvalues = this.dropdownList(code);
        if (CollectionUtil.isEmpty(keyvalues)){
            return Maps.newHashMap();
        }
        Map<String, String> map = new HashMap<>();
        for (Keyvalue keyvalue : keyvalues) {
            map.put(keyvalue.getValue(),keyvalue.getKey());
        }
        return map;
    }


}
