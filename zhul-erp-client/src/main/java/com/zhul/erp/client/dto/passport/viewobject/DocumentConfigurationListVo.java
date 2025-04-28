package com.zhul.erp.client.dto.passport.viewobject;

import com.zhul.cloud.common.model.GeneralQuery;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * 单据配置(DocumentConfiguration)VO
 *
 * @author makejava
 * @since 2023-12-21 15:56:05
 */
@Data
public class DocumentConfigurationListVo extends GeneralQuery {
    
    /**
    * 账号ID
    */
    @ApiModelProperty("账号ID")
    private Integer id;
    /**
    * 系统编号
    */
    @ApiModelProperty("系统编号")
    private String systemId;
    /**
    * 订单类型编号
    */
    @ApiModelProperty("订单类型编号")
    private Integer orderTypeId;
    /**
    * wms订单类型
    */
    @ApiModelProperty("wms订单类型")
    private String wmsOrderType;
    /**
    * 业务类型
    */
    @ApiModelProperty("业务类型")
    private String businessType;
    /**
    * 业务类型编号
    */
    @ApiModelProperty("业务类型编号")
    private Integer businessTypeId;
    /**
    * 退货标识
    */
    @ApiModelProperty("退货标识")
    private String returnFlag;
    /**
    * 成本类型编号
    */
    @ApiModelProperty("成本类型编号")
    private Integer costTypeId;
    /**
    * 创建时间
    */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 创建人
    */
    @ApiModelProperty("创建人")
    private String createBy;
    /**
    * 更新时间
    */
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 更新人
    */
    @ApiModelProperty("更新人")
    private String updateBy;
}

