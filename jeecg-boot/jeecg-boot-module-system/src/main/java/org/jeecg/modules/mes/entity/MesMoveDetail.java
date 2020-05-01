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
 * @Description: mes_move_detail
 * @Author: jeecg-boot
 * @Date:   2020-04-30
 * @Version: V1.0
 */
@Data
@TableName("mes_move_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mes_move_detail对象", description="mes_move_detail")
public class MesMoveDetail implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.String id;
	/**移动单编号*/
	@Excel(name = "移动单编号", width = 15)
    @ApiModelProperty(value = "移动单编号")
    private java.lang.String number;
	/**出入库类型
1：入库
2：出库
3：移位*/
	@Excel(name = "出入库类型 1：入库 2：出库 3：移位", width = 15)
    @ApiModelProperty(value = "出入库类型 1：入库 2：出库 3：移位")
    private java.lang.Integer type;
	/**物料ID*/
	@Excel(name = "物料ID", width = 15, dictTable = "mes_material", dicText = "name", dicCode = "id")
	@Dict(dictTable = "mes_material", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "物料ID")
    private java.lang.String materialId;
	/**库存明细ID*/
	@Excel(name = "库存明细ID", width = 15)
    @ApiModelProperty(value = "库存明细ID")
    private java.lang.String inventoryId;
	/**移位情况有效：
表示移往库存点*/
	@Excel(name = "移位情况有效： 表示移往库存点", width = 15)
    @ApiModelProperty(value = "移位情况有效： 表示移往库存点")
    private java.lang.String inventoryTid;
	/**companyId*/
	@Excel(name = "companyId", width = 15, dictTable = "mes_company", dicText = "name", dicCode = "id")
	@Dict(dictTable = "mes_company", dicText = "name", dicCode = "id")
    @ApiModelProperty(value = "companyId")
    private java.lang.String companyId;
	/**planQuantity*/
	@Excel(name = "planQuantity", width = 15)
    @ApiModelProperty(value = "planQuantity")
    private java.math.BigDecimal planQuantity;
	/**quantity*/
	@Excel(name = "quantity", width = 15)
    @ApiModelProperty(value = "quantity")
    private java.math.BigDecimal quantity;
	/**isDel*/
	@Excel(name = "isDel", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "isDel")
    private java.util.Date isDel;
	/**stockEntryId*/
	@Excel(name = "stockEntryId", width = 15)
    @ApiModelProperty(value = "stockEntryId")
    private java.lang.String stockEntryId;
	/**stockTransId*/
	@Excel(name = "stockTransId", width = 15)
    @ApiModelProperty(value = "stockTransId")
    private java.lang.String stockTransId;
	/**deliveryId*/
	@Excel(name = "deliveryId", width = 15)
    @ApiModelProperty(value = "deliveryId")
    private java.lang.String deliveryId;
	/**happenDate*/
	@Excel(name = "happenDate", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "happenDate")
    private java.util.Date happenDate;
	/**状态：
1：初始提交；
2：审核中；
3：审核通过；
4：审核未通过；
5：已完成；*/
	@Excel(name = "状态： 1：初始提交； 2：审核中； 3：审核通过； 4：审核未通过； 5：已完成；", width = 15, dicCode = "storage_in_order_status")
	@Dict(dicCode = "storage_in_order_status")
    @ApiModelProperty(value = "状态： 1：初始提交； 2：审核中； 3：审核通过； 4：审核未通过； 5：已完成；")
    private java.lang.Integer status;
	/**批准人ID*/
	@Excel(name = "批准人ID", width = 15)
    @ApiModelProperty(value = "批准人ID")
    private java.lang.Integer approveUid;
	/**批准时间*/
	@Excel(name = "批准时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "批准时间")
    private java.util.Date approveTime;
	/**拒绝理由*/
	@Excel(name = "拒绝理由", width = 15)
    @ApiModelProperty(value = "拒绝理由")
    private java.lang.String rejectRemark;
	/**备注*/
	@Excel(name = "备注", width = 15)
    @ApiModelProperty(value = "备注")
    private java.lang.String remark;
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
