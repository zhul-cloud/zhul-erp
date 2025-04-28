package com.zhul.erp.domain.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.zhul.cloud.common.model.GeneralDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account")
public class Account extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 账号ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 用户名
     */
    @TableField("username")
    private String username;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;
    /**
     * 管理员标记（0-否、1-是）
     */
    @TableField("admin_flag")
    private Integer adminFlag;
    /**
     * 最近登录时间
     */
    @TableField("last_login_time")
    private String lastLoginTime;
    /**
     * 最后登出时间
     */
    @TableField("last_logout_time")
    private String lastLogoutTime;
    /**
     * 当前登录状态（0-登出、1-登录）
     */
    @TableField("login_status")
    private Integer loginStatus;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String USERNAME = "username";

    public static final String PHONE = "phone";

    public static final String EMAIL = "email";

    public static final String ADMIN_FLAG = "admin_flag";

    public static final String LAST_LOGIN_TIME = "last_login_time";

    public static final String LAST_LOGOUT_TIME = "last_logout_time";

    public static final String LOGIN_STATUS = "login_status";

    public static final String STATUS = "status";

}
