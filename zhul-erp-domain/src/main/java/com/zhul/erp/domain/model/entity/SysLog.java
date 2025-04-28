package com.zhul.erp.domain.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.zhul.cloud.common.model.GeneralDO;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统日志表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_log")
public class SysLog extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 所属对象编码
     */
    @TableField("belong_code")
    private String belongCode;
    /**
     * 所属对象名称
     */
    @TableField("belong_name")
    private String belongName;
    /**
     * 操作分类（1-登录，2-操作， 9-其他）
     */
    @TableField("type")
    private Integer type;
    /**
     * 操作人编码
     */
    @TableField("operator_code")
    private String operatorCode;
    /**
     * 操作人姓名
     */
    @TableField("operator_name")
    private String operatorName;
    /**
     * 操作时间
     */
    @TableField("operate_time")
    private LocalDateTime operateTime;
    /**
     * 操作名称
     */
    @TableField("operation")
    private String operation;
    /**
     * 操作内容
     */
    @TableField("content")
    private String content;
    /**
     * 操作结果
     */
    @TableField("result")
    private String result;
    /**
     * 操作模块
     */
    @TableField("menu")
    private String menu;
    /**
     * 客户端ip地址
     */
    @TableField("ip")
    private String ip;


    public static final String ID = "id";

    public static final String BELONG_CODE = "belong_code";

    public static final String BELONG_NAME = "belong_name";

    public static final String TYPE = "type";

    public static final String OPERATOR_CODE = "operator_code";

    public static final String OPERATOR_NAME = "operator_name";

    public static final String OPERATE_TIME = "operate_time";

    public static final String OPERATION = "operation";

    public static final String CONTENT = "content";

    public static final String RESULT = "result";

    public static final String MENU = "menu";

    public static final String IP = "ip";

}
