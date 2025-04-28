package com.zhul.erp.client.dto.passport.viewobject;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by yanglikai on 2023/12/17.
 */
@Data
public class DictListVO implements Serializable {

  @ApiModelProperty(value = "主键")
  private Integer id;

  @ApiModelProperty(value = "字典编码")
  private String code;

  @ApiModelProperty(value = "字典名称")
  private String name;

  @ApiModelProperty(value = "创建人")
  private String createBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "修改人")
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @ApiModelProperty(value = "修改时间")
  private Date updateTime;
}
