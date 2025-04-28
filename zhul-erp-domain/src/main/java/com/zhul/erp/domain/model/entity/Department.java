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
 * 部门表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("department")
public class Department extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 上级部门ID
     */
    @TableField("pid")
    private Integer pid;
    /**
     * 部门编码（DP+主键，形如DP10000）
     */
    @TableField("code")
    private String code;
    /**
     * 部门名称
     */
    @TableField("name")
    private String name;
    /**
     * 部门全称
     */
    @TableField("all_name")
    private String allName;
    /**
     * 部门负责人ID
     */
    @TableField("leader_id")
    private Integer leaderId;
    /**
     * 所处层级
     */
    @TableField("level")
    private Integer level;
    /**
     * 当前部门人数统计
     */
    @TableField("headcount")
    private Integer headcount;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String ALL_NAME = "all_name";

    public static final String LEADER_ID = "leader_id";

    public static final String LEVEL = "level";

    public static final String HEADCOUNT = "headcount";

    public static final String SORT = "sort";

    public static final String STATUS = "status";

}
