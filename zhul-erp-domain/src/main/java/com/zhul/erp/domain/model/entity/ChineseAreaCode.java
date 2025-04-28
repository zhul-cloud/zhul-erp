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
 * 中国行政区编码表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("chinese_area_code")
public class ChineseAreaCode extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区编码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 父编码
     */
    @TableField("pid")
    private Long pid;
    /**
     * 名称
     */
    @TableField("name")
    private String name;
    /**
     * 类型（1-省级、2-市(地级)、3-区县(市级)、4-乡镇(乡级)、5-街道/村(村级)）
     */
    @TableField("type")
    private Integer type;
    /**
     * 状态（0-禁用、1-启用）
     */
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String NAME = "name";

    public static final String TYPE = "type";

    public static final String STATUS = "status";

}
