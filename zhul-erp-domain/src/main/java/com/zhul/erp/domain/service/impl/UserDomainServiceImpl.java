package com.zhul.erp.domain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.zhul.cloud.common.mapper.Mapper;
import com.zhul.cloud.common.model.Keyvalue;
import com.zhul.erp.client.dto.passport.cqrs.command.UserAddCmd;
import com.zhul.erp.client.dto.passport.cqrs.command.UserModifyCmd;
import com.zhul.erp.domain.gateway.IMdmGateway;
import com.zhul.erp.domain.manager.UserManager;
import com.zhul.erp.domain.model.aggregate.User;
import com.zhul.erp.domain.model.entity.AccountOrg;
import com.zhul.erp.domain.model.entity.AccountRole;
import com.zhul.erp.domain.model.entity.UserBasic;
import com.zhul.erp.domain.model.repository.IUserBasicRepository;
import com.zhul.erp.domain.model.value.GlobalConstant;
import com.zhul.erp.domain.service.IAccountDomainService;
import com.zhul.erp.domain.service.IUserDomainService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yanglikai on 2023/12/19.
 */
@Service
public class UserDomainServiceImpl implements IUserDomainService {

  @Autowired
  private IUserBasicRepository userBasicRepository;

  @Autowired
  private IAccountDomainService accountDomainService;

  @Autowired
  private IMdmGateway mdmGateway;

  /**
   * 创建用户域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public User create(UserAddCmd cmd) {
    // 1.参数解析
    String username = cmd.getUsername();
    String password = cmd.getPassword();
    String phone = cmd.getPhone();
    List<String> roles = this.mdmGateway.loadRoleCodes(cmd.getRoleIds());
    List<String> orgs = this.mdmGateway.loadOrgCodes(cmd.getOrgIds());

    // 2.查询用户
    UserBasic userBasic = this.userBasicRepository.selectByUsername(username);
    if (userBasic != null) {
      return new User(userBasic);
    }

    // 3.创建用户域
    User user = new User();
    user.setUser(Mapper.map(cmd, UserBasic.class));
    user.setAccount(
        StringUtils.isBlank(cmd.getPassword()) ? this.accountDomainService
            .create(username, GlobalConstant.DEFAULT_PWD, roles, orgs)
            : this.accountDomainService.create(username, password, roles, orgs));

    // 设置用户信息
    user.getUser().setRoleCode(String.join(",", roles));

    // 设置账号信息
    user.getAccount().getAccount().setPhone(phone);

    // 4.返回用户域
    return user;
  }

  /**
   * 更新用户域
   * <p></p>
   *
   * @param cmd
   * @return
   */
  @Override
  public User update(UserModifyCmd cmd) {
    // 1.参数解析
    Integer userId = cmd.getId();
    List<String> roles = this.mdmGateway.loadRoleCodes(cmd.getRoleIds());
    List<String> orgs = this.mdmGateway.loadOrgCodes(cmd.getOrgIds());

    // 2.查询用户
    User user = this.queryByUserId(userId);
    if (user.isEmpty()) {
      return new User();
    }

    // 3.更新用户信息
    user.getUser().setPid(cmd.getPid());
    user.getUser().setType(cmd.getType());
    user.getUser().setName(cmd.getName());
    user.getUser().setPhone(cmd.getPhone());
    user.getUser().setDeptId(cmd.getDeptId());
    user.getUser().setStatus(cmd.getStatus());
    user.getUser().setUpdateTime(new Date());
    user.getUser().setUpdateBy(UserManager.getUserName());

    // 4.更新账号角色信息
    if (CollectionUtil.isNotEmpty(roles)) {
      user.getUser().setRoleCode(String.join(",", roles));

      user.getAccount().setRoles(roles.stream().map(roleCode -> {
        AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(user.accountId());
        accountRole.setRoleCode(roleCode);
        return accountRole;
      }).collect(Collectors.toList()));
    }

    // 5.更新账号机构信息
    if (CollectionUtil.isNotEmpty(orgs)) {
      user.getAccount().setOrgs(orgs.stream().map(orgCode -> {
        AccountOrg accountOrg = new AccountOrg();
        accountOrg.setAccountId(user.accountId());
        accountOrg.setOrgCode(orgCode);
        return accountOrg;
      }).collect(Collectors.toList()));
    }

    // 6.返回用户域
    return user;
  }

  /**
   * 查询用户域
   * <p></p>
   *
   * @param userId
   * @return
   */
  @Override
  public User queryByUserId(Integer userId) {

    // 1.查询用户基本信息
    UserBasic basic = this.userBasicRepository.getById(userId);
    if (basic == null) {
      return new User();
    }

    // 2.查询用户账号信息
    User user = new User(basic);
    user.setAccount(this.accountDomainService.queryByUserId(userId));

    return user;
  }

  /**
   * 查询用户域
   * <p></p>
   *
   * @param username
   * @return
   */
  @Override
  public User queryByUsername(String username) {

    UserBasic user = this.userBasicRepository.lambdaQuery().eq(UserBasic::getUsername, username)
        .one();
    if (user == null) {
      return new User();
    }

    return this.queryByUserId(user.getId());
  }

  /**
   * 用户键值对
   * <p></p>
   *
   * @return
   */
  @Override
  public List<Keyvalue> keyvalues() {

    return this.mdmGateway.loadAll4User();
  }
}
