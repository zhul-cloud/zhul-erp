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
 * 用户基本信息表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_basic")
public class UserBasic extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 上级领导ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 用户姓名
     */
    @TableField("name")
    private String name;
    /**
     * 用户类型（1-员工）
     */
    @TableField("type")
    private Integer type;
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
     * 部门编号
     */
    @TableField("dept_id")
    private Integer deptId;
    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;
    /**
     * 邮箱地址
     */
    @TableField("email")
    private String email;
    /**
     * 头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;
    /**
     * 昵称
     */
    @TableField("nickname")
    private String nickname;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String USERNAME = "username";

    public static final String PHONE = "phone";

    public static final String DEPT_ID = "dept_id";

    public static final String ROLE_CODE = "role_code";

    public static final String EMAIL = "email";

    public static final String AVATAR_URL = "avatar_url";

    public static final String NICKNAME = "nickname";

    public static final String STATUS = "status";

}
