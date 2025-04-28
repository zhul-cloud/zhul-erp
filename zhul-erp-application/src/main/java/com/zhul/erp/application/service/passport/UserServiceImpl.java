package com.zhul.erp.application.service.passport;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.api.passport.IUserService;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserDeleteCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.client.dto.passport.cqrs.query.UserDtlQry;
import com.zhul.erp.client.dto.passport.cqrs.query.UserListQry;
import com.zhul.erp.client.dto.passport.viewobject.UserDtlVO;
import com.zhul.erp.client.dto.passport.viewobject.UserListVO;
import com.zhul.erp.domain.manager.MdmCacheManager;
import com.zhul.erp.domain.mapper.MapperExpand;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.UserBasic;
import com.zhul.erp.domain.model.repository.IUserBasicRepository;
import com.zhul.erp.domain.model.repository.IUserRepository;
import com.zhul.erp.domain.model.value.StatusEnum;
import com.zhul.erp.domain.service.IUserDomainService;
import com.zhul.erp.domain.spec.UserCreateSpec;
import com.zhul.erp.domain.spec.UserDeleteSpec;
import com.zhul.erp.domain.spec.UserModifySpec;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  private IUserDomainService userDomainService;

  @Autowired
  private IUserRepository userRepository;

  @Autowired
  private IUserBasicRepository userBasicRepository;

  /**
   * 新增用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean add(UserAddCmd cmd) {
    // 1.创建用户域
    User user = this.userDomainService.create(cmd);

    // 2.规则效验
    new UserCreateSpec().satisfiedBy(user);

    // 3.数据持久化
    this.userRepository.save(user);

    return true;
  }

  /**
   * 删除用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean delete(UserDeleteCmd cmd) {

    // 1.查询用户域
    User user = this.userDomainService.queryByUserId(cmd.getId());

    // 2.规则效验
    new UserDeleteSpec().satisfiedBy(user);

    // 3.数据持久化
    this.userRepository.delete(user);

    return true;
  }

  /**
   * 修改用户
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public Boolean modify(UserModifyCmd cmd) {

    // 1.更新用户域
    User user = this.userDomainService.update(cmd);

    // 2.规则效验
    new UserModifySpec().satisfiedBy(user);

    // 3.数据持久化
    this.userRepository.update(user);

    return true;
  }

  /**
   * 用户列表
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public Page<UserListVO> list(UserListQry qry) {

    Integer type = qry.getType();
    Integer deptId = qry.getDeptId();
    String username = qry.getUsername();
    String name = qry.getName();
    String phone = qry.getPhone();
    List<Integer> pids = qry.getPids();
    Integer status = qry.getStatus();

    // 1.数据查询
    Page<UserBasic> pages = this.userBasicRepository.page(qry.tPage(),
        new LambdaQueryWrapper<UserBasic>()
            .eq(type != null, UserBasic::getType, type)
            .eq(deptId != null, UserBasic::getDeptId, deptId)
            .eq(StringUtils.isNotBlank(username), UserBasic::getUsername, username)
            .eq(StringUtils.isNotBlank(name), UserBasic::getName, name)
            .eq(StringUtils.isNotBlank(phone), UserBasic::getPhone, phone)
            .in(CollectionUtil.isNotEmpty(pids), UserBasic::getPid, pids)
            .eq(status != null, UserBasic::getStatus, status)
            .orderByDesc(UserBasic::getCreateTime));

    // 2.数据转换
    return MapperExpand.mapToPage(pages, (el -> {
      UserListVO vo = Mapper.map(el, UserListVO.class);
      vo.setPname(MdmCacheManager.getUser(el.getPid()).getValue());
      vo.setDeptName(MdmCacheManager.getDept(String.valueOf(el.getDeptId())).getValue());
      vo.setStatusName(StatusEnum.transform(el.getStatus()).desc());

      // 格式化角色名称
      List<Keyvalue> roles = MdmCacheManager.getRoles(Arrays.asList(el.getRoleCode().split(",")));
      if(CollectionUtil.isNotEmpty(roles)) {
        String format4RoleName = String
            .join(",", roles.stream().map(role -> role.getValue()).collect(Collectors.toList()));

        vo.setRoleName(format4RoleName);
      }

      return vo;
    }));
  }

  @Override
  public List<UserListVO> getByIds(Collection<Integer> ids) {
    if (CollectionUtil.isEmpty(ids)){
      return Lists.newArrayList();
    }
    List<UserBasic> userBasics = this.userBasicRepository.lambdaQuery()
            .in(UserBasic::getId,ids).list();

    return Mapper.map(userBasics, UserListVO.class);
  }

  /**
   * 用户详情
   * <p></p>
   *
   * @param qry
   * @return
   */
  @Override
  public UserDtlVO dtl(UserDtlQry qry) {
    // 1.查询用户域
    User user = this.userDomainService.queryByUserId(qry.getId());

    // 2.数据转换
    return MapperExpand.map(user, (el -> {
      UserBasic basic = el.getUser();

      UserDtlVO dtl = new UserDtlVO();
      dtl.setId(basic.getId());
      dtl.setPid(basic.getPid());
      dtl.setName(MdmCacheManager.getUser(basic.getPid()).getValue());
      dtl.setName(basic.getName());
      dtl.setUsername(basic.getUsername());
      dtl.setType(basic.getType());
      dtl.setDeptId(basic.getDeptId());
      dtl.setDeptName(MdmCacheManager.getDept(String.valueOf(basic.getDeptId())).getValue());
      dtl.setPhone(basic.getPhone());
      dtl.setStatus(basic.getStatus());
      dtl.setRoleIds(user.getAccount().roleIds());
      dtl.setOrgIds(user.getAccount().orgIds());

      return dtl;
    }));
  }

  /**
   * 用户下拉列表
   * <p></p>
   *
   * @return
   */
  @Override
  public List<Keyvalue> dropdownList() {

    return this.userDomainService.keyvalues();
  }
}
