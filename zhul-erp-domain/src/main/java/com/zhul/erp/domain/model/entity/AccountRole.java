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
 * 账号角色表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account_role")
public class AccountRole extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 账号ID
     */
    @TableField("account_id")
    private Integer accountId;
    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;


    public static final String ID = "id";

    public static final String ACCOUNT_ID = "account_id";

    public static final String ROLE_CODE = "role_code";

}
