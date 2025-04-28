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
 * 角色表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("role")
public class Role extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色编码
     */
    @TableField("code")
    private String code;
    /**
     * 角色名称
     */
    @TableField("name")
    private String name;
    /**
     * 数据权限范围(0-无权限、1-全部数据、2-自定义数据、3-仅本人数据）
     */
    @TableField("permission_scope")
    private Integer permissionScope;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;


    public static final String ID = "id";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String PERMISSION_SCOPE = "permission_scope";

    public static final String STATUS = "status";

    public static final String REMARK = "remark";


    public boolean isEmpty() {
        return this.id == null || 0 == this.id ? true : false;
    }

    public boolean isNotEmpty() {
        return !this.isEmpty();
    }
}
