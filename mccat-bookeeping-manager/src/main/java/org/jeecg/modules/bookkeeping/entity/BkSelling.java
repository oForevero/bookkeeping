package org.jeecg.modules.bookkeeping.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @Description: bk_selling
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_selling")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_selling对象", description="bk_selling")
public class BkSelling implements Serializable {
    private static final long serialVersionUID = 1L;

	/**销售记录id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "销售记录id")
    private java.lang.Integer id;
	/**销售时间，日期数据*/
	@Excel(name = "销售时间，日期数据", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "销售时间，日期数据")
    private java.util.Date sellDate;
	/**销售客户id*/
	@Excel(name = "销售客户id", width = 15)
    @ApiModelProperty(value = "销售客户id")
    private java.lang.Integer sellCollaborator;
	/**销售物品id*/
	@Excel(name = "销售物品id", width = 15)
    @ApiModelProperty(value = "销售物品id")
    private java.lang.Integer sellItem;
	/**销售个数，支持小数，缩进两位小数*/
	@Excel(name = "销售个数，支持小数，缩进两位小数", width = 15)
    @ApiModelProperty(value = "销售个数，支持小数，缩进两位小数")
    private java.lang.Double sellAmount;
	/**销售物品单价*/
	@Excel(name = "销售物品单价", width = 15)
    @ApiModelProperty(value = "销售物品单价")
    private java.lang.Double sellUnitPrice;
	/**销售物品总价，最大值100亿*/
	@Excel(name = "销售物品总价，最大值100亿", width = 15)
    @ApiModelProperty(value = "销售物品总价，最大值100亿")
    private java.math.BigDecimal sellPrice;
	/**销售备注，最大100字*/
	@Excel(name = "销售备注，最大100字", width = 15)
    @ApiModelProperty(value = "销售备注，最大100字")
    private java.lang.String sellRemark;
	/**发票类型，0普通票，1增值税，2未开具*/
	@Excel(name = "发票类型，0普通票，1增值税，2未开具", width = 15)
    @ApiModelProperty(value = "发票类型，0普通票，1增值税，2未开具")
    private java.lang.Integer sellReceipt;
}
