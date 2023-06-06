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
 * @Description: bk_purchase
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_purchase")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_purchase对象", description="bk_purchase")
public class BkPurchase implements Serializable {
    private static final long serialVersionUID = 1L;

	/**进货记录id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "进货记录id")
    private Integer id;
	/**进货时间，日期数据*/
	@Excel(name = "进货时间，日期数据", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "进货时间，日期数据")
    private Date purchaseDate;
	/**进货客户id*/
	@Excel(name = "进货客户id", width = 15)
    @ApiModelProperty(value = "进货客户id")
    private Integer purchaseCollaborator;
	/**进货物品id*/
	@Excel(name = "进货物品id", width = 15)
    @ApiModelProperty(value = "进货物品id")
    private Integer purchaseItem;
	/**进货个数，支持小数，缩进两位小数*/
	@Excel(name = "进货个数，支持小数，缩进两位小数", width = 15)
    @ApiModelProperty(value = "进货个数，支持小数，缩进两位小数")
    private Double purchaseAmount;
	/**进货物品单价*/
	@Excel(name = "进货物品单价", width = 15)
    @ApiModelProperty(value = "进货物品单价")
    private Double purchaseUnitPrice;
	/**进货物品总价，最大值100亿*/
	@Excel(name = "进货物品总价，最大值100亿", width = 15)
    @ApiModelProperty(value = "进货物品总价，最大值100亿")
    private BigDecimal purchasePrice;
	/**进货备注，最大100字*/
	@Excel(name = "进货备注，最大100字", width = 15)
    @ApiModelProperty(value = "进货备注，最大100字")
    private String purchaseRemark;
	/**发票类型，0普通票，1增值税，2未开具*/
	@Excel(name = "发票类型，0普通票，1增值税，2未开具", width = 15)
    @ApiModelProperty(value = "发票类型，0普通票，1增值税，2未开具")
    private Integer purchaseReceipt;
}
