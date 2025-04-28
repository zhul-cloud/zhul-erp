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
 * 字典表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dict")
public class Dict extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 类型（1-字典、2-子目录、3-字典项）
     */
    @TableField("type")
    private Integer type;
    /**
     * 编码
     */
    @TableField("code")
    private String code;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 字典值
     */
    @TableField("value")
    private String value;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 备注
     */
    @TableField("remark")
    private String remark;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String TYPE = "type";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String VALUE = "value";

    public static final String SORT = "sort";

    public static final String REMARK = "remark";

    public static final String STATUS = "status";

}
