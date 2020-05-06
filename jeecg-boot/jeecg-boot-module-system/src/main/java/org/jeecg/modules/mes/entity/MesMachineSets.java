package org.jeecg.modules.mes.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: mes_machine_sets
 * @Author: jeecg-boot
 * @Date:   2020-05-06
 * @Version: V1.0
 */
@ApiModel(value="mes_work_shop对象", description="MES车间表")
@Data
@TableName("mes_machine_sets")
public class MesMachineSets implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
	@ApiModelProperty(value = "id")
	private java.lang.String id;
	/**名称*/
	@Excel(name = "名称", width = 15)
	@ApiModelProperty(value = "名称")
	private java.lang.String name;
	/**车间ID*/
	@ApiModelProperty(value = "车间ID")
	private java.lang.String workShopId;
	/**描述*/
	@Excel(name = "描述", width = 15)
	@ApiModelProperty(value = "描述")
	private java.lang.String description;
	/**编码*/
	@Excel(name = "编码", width = 15)
	@ApiModelProperty(value = "编码")
	private java.lang.String code;
	/**排序*/
	@Excel(name = "排序", width = 15)
	@ApiModelProperty(value = "排序")
	private java.lang.Integer sort;
	/**横坐标*/
	@Excel(name = "横坐标", width = 15)
	@ApiModelProperty(value = "横坐标")
	private java.lang.Double xaxis;
	/**纵坐标*/
	@Excel(name = "纵坐标", width = 15)
	@ApiModelProperty(value = "纵坐标")
	private java.lang.Double yaxis;
	/**外协机组*/
	@Excel(name = "外协机组", width = 15)
	@ApiModelProperty(value = "外协机组")
	private java.lang.Integer izOutSource;
	/**连续生产*/
	@Excel(name = "连续生产", width = 15)
	@ApiModelProperty(value = "连续生产")
	private java.lang.Integer isContinuous;
	/**传入秒数*/
	@Excel(name = "传入秒数", width = 15)
	@ApiModelProperty(value = "传入秒数")
	private java.lang.Integer passInSeconds;
	/**转出秒数*/
	@Excel(name = "转出秒数", width = 15)
	@ApiModelProperty(value = "转出秒数")
	private java.lang.Integer passOutSeconds;
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
