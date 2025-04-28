package com.zhul.erp.domain.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.zhul.cloud.common.model.GeneralDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("resource")
public class Resource extends GeneralDO {

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
     * 资源编码（RS+类型+主键，形如RS100000）
     */
    @TableField("code")
    private String code;
    /**
     * 资源名称
     */
    @TableField("name")
    private String name;
    /**
     * 资源类型(1-目录、2-菜单、3-按钮)
     */
    @TableField("type")
    private Integer type;
    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;
    /**
     * 浅色默认图标地址
     */
    @TableField("light_icon")
    private String lightIcon;
    /**
     * 浅色选中状态图标地址
     */
    @TableField("light_selected_icon")
    private String lightSelectedIcon;
    /**
     * 深色默认图标地址
     */
    @TableField("dark_icon")
    private String darkIcon;
    /**
     * 深色选中状态图标地址
     */
    @TableField("dark_selected_icon")
    private String darkSelectedIcon;
    /**
     * 路径
     */
    @TableField("path")
    private String path;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;
    /**
     * 微应用标识（字典）
     */
    @TableField("micro_app")
    private String microApp;

    /**
     * 类型名称
     */
    @TableField(exist = false)
    private String typeName;

    /**
     * 状态名称
     */
    @TableField(exist = false)
    private String statusName;

    /**
     * 子目录
     */
    @TableField(exist = false)
    private List<Resource> children;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String CODE = "code";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String SORT = "sort";

    public static final String LIGHT_ICON = "light_icon";

    public static final String LIGHT_SELECTED_ICON = "light_selected_icon";

    public static final String DARK_ICON = "dark_icon";

    public static final String DARK_SELECTED_ICON = "dark_selected_icon";

    public static final String PATH = "path";

    public static final String STATUS = "status";

    public static final String MICRO_APP = "micro_app";

}
