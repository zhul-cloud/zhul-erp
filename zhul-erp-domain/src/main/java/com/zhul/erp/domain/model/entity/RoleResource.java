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
 * 角色资源表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role_resource")
public class RoleResource extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;
    /**
     * 资源编号
     */
    @TableField("resource_id")
    private Integer resourceId;
    /**
     * 资源编码
     */
    @TableField("resource_code")
    private String resourceCode;


    public static final String ID = "id";

    public static final String ROLE_CODE = "role_code";

    public static final String RESOURCE_ID = "resource_id";

    public static final String RESOURCE_CODE = "resource_code";

}
