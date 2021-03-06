package org.jeecg.modules.mes.org.jeecg.modules.mes.entity;

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
 * @Description: mes_material
 * @Author: jeecg-boot
 * @Date:   2020-04-26
 * @Version: V1.0
 */
@Data
@TableName("mes_material")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mes_material对象", description="mes_material")
public class MesMaterial implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**code*/
	@Excel(name = "code", width = 15)
    @ApiModelProperty(value = "code")
    private java.lang.String code;
	/**name*/
	@Excel(name = "name", width = 15)
    @ApiModelProperty(value = "name")
    private java.lang.String name;
	/**物料类型：
1：采购物料;
2：生产产品;
3：物料和产品；*/
	@Excel(name = "物料类型：
1：采购物料;
2：生产产品;
3：物料和产品；", width = 15)
    @ApiModelProperty(value = "物料类型：
1：采购物料;
2：生产产品;
3：物料和产品；")
    private java.lang.Integer type;
	/**categoryId*/
	@Excel(name = "categoryId", width = 15)
    @ApiModelProperty(value = "categoryId")
    private java.lang.Integer categoryId;
	/**unitId*/
	@Excel(name = "unitId", width = 15)
    @ApiModelProperty(value = "unitId")
    private java.lang.Integer unitId;
	/**安全库存*/
	@Excel(name = "安全库存", width = 15)
    @ApiModelProperty(value = "安全库存")
    private java.lang.Integer safeNum;
	/**有效天*/
	@Excel(name = "有效天", width = 15)
    @ApiModelProperty(value = "有效天")
    private java.lang.Integer validDay;
	/**库存数量*/
	@Excel(name = "库存数量", width = 15)
    @ApiModelProperty(value = "库存数量")
    private java.math.BigDecimal quantity;
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
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
	/**创建日期*/
	@Excel(name = "创建日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;
}
