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
 * @Description: mes_in_storage
 * @Author: jeecg-boot
 * @Date:   2020-04-29
 * @Version: V1.0
 */
@Data
@TableName("mes_in_storage")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mes_in_storage对象", description="mes_in_storage")
public class MesInStorage implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**入库或出库单号*/
	@Excel(name = "入库或出库单号", width = 15)
    @ApiModelProperty(value = "入库或出库单号")
    private java.lang.String number;
	/**订单号*/
	@Excel(name = "订单号", width = 15)
    @ApiModelProperty(value = "订单号")
    private java.lang.String orderNumber;
	/**入库单类别*/
	@Excel(name = "入库单类别", width = 15, dicCode = "srote_in_order_type")
	@Dict(dicCode = "srote_in_order_type")
    @ApiModelProperty(value = "入库单类别")
    private java.lang.Integer cateId;
	/**公司ID*/
	@Excel(name = "公司ID", width = 15, dictTable = "mes_company", dicText = "name", dicCode = "id")
	@Dict(dictTable = "mes_company", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "公司ID")
    private java.lang.Integer companyId;
	/**状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；*/
	@Excel(name = "状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；", width = 15)
    @ApiModelProperty(value = "状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；")
    private java.lang.Integer status;
	/**快递ID*/
	@Excel(name = "快递ID", width = 15)
    @ApiModelProperty(value = "快递ID")
    private java.lang.Integer deliveryId;
	/**1：入库
2：出库*/
	@Excel(name = "1：入库
2：出库", width = 15)
    @ApiModelProperty(value = "1：入库
2：出库")
    private java.lang.Integer typeId;
	/**isDel*/
	@Excel(name = "isDel", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "isDel")
    private java.util.Date isDel;
	/**remark*/
	@Excel(name = "remark", width = 15)
    @ApiModelProperty(value = "remark")
    private java.lang.String remark;
	/**approveUid*/
	@Excel(name = "approveUid", width = 15)
    @ApiModelProperty(value = "approveUid")
    private java.lang.Integer approveUid;
	/**approveTime*/
	@Excel(name = "approveTime", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "approveTime")
    private java.util.Date approveTime;
	/**驳回理由*/
	@Excel(name = "驳回理由", width = 15)
    @ApiModelProperty(value = "驳回理由")
    private java.lang.String rejectRemark;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**修改人*/
	@Excel(name = "修改人", width = 15)
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**修改时间*/
	@Excel(name = "修改时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
}
