package com.zhul.erp.domain.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.zhul.cloud.common.model.GeneralDO;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;

import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账号访问Token表
 * </p>
 *
 * @author sys
 * @since 2025-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("account_access_token")
public class AccountAccessToken extends GeneralDO {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Integer userId;
    /**
     * 账号ID
     */
    @TableField("account_id")
    private Integer accountId;
    /**
     * 认证Token
     */
    @TableField("access_token")
    private String accessToken;
    /**
     * 状态(0-失效、1-有效)
     */
    @TableField("status")
    private Integer status;
    /**
     * 到期时间
     */
    @TableField("expiry_time")
    private Date expiryTime;


    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String ACCOUNT_ID = "account_id";

    public static final String ACCESS_TOKEN = "access_token";

    public static final String STATUS = "status";

    public static final String EXPIRY_TIME = "expiry_time";

}
