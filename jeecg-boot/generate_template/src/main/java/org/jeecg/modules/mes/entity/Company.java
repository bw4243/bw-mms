package org.jeecg.modules.mes.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: company
 * @Author: jeecg-boot
 * @Date:   2020-04-27
 * @Version: V1.0
 */
@Data
@TableName("company")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="company对象", description="company")
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
	/**供应商编码*/
	@Excel(name = "供应商编码", width = 15)
    @ApiModelProperty(value = "供应商编码")
    private java.lang.String code;
	/**供应商全称
*/
	@Excel(name = "供应商全称
", width = 15)
    @ApiModelProperty(value = "供应商全称
")
    private java.lang.String name;
	/**企业类别*/
	@Excel(name = "企业类别", width = 15)
    @ApiModelProperty(value = "企业类别")
    private java.lang.Integer type;
	/**isDel*/
	@Excel(name = "isDel", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "isDel")
    private java.util.Date isDel;
	/**地址*/
	@Excel(name = "地址", width = 15)
    @ApiModelProperty(value = "地址")
    private java.lang.String address;
	/**国家*/
	@Excel(name = "国家", width = 15)
    @ApiModelProperty(value = "国家")
    private java.lang.String country;
	/**省*/
	@Excel(name = "省", width = 15)
    @ApiModelProperty(value = "省")
    private java.lang.String province;
	/**市*/
	@Excel(name = "市", width = 15)
    @ApiModelProperty(value = "市")
    private java.lang.String city;
	/**联系人姓名*/
	@Excel(name = "联系人姓名", width = 15)
    @ApiModelProperty(value = "联系人姓名")
    private java.lang.String contactName;
	/**座机*/
	@Excel(name = "座机", width = 15)
    @ApiModelProperty(value = "座机")
    private java.lang.String tele;
	/**手机号*/
	@Excel(name = "手机号", width = 15)
    @ApiModelProperty(value = "手机号")
    private java.lang.String mobile;
	/**Email*/
	@Excel(name = "Email", width = 15)
    @ApiModelProperty(value = "Email")
    private java.lang.String email;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
}
