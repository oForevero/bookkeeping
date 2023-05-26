package org.jeecg.bookkeeping.entity;

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
 * @Description: bk_product
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_product")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_product对象", description="bk_product")
public class BkProduct implements Serializable {
    private static final long serialVersionUID = 1L;

	/**product 主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "product 主键")
    private Integer id;
	/**关系id*/
	@Excel(name = "关系id", width = 15)
    @ApiModelProperty(value = "关系id")
    private Integer relationId;
	/**商品名*/
	@Excel(name = "商品名", width = 15)
    @ApiModelProperty(value = "商品名")
    private String name;
	/**商品单价*/
	@Excel(name = "商品单价", width = 15)
    @ApiModelProperty(value = "商品单价")
    private BigDecimal price;
	/**计量单位*/
	@Excel(name = "计量单位", width = 15)
    @ApiModelProperty(value = "计量单位")
    private String module;
	/**商品数量*/
	@Excel(name = "商品数量", width = 15)
    @ApiModelProperty(value = "商品数量")
    private Double amount;
	/**商品备注*/
	@Excel(name = "商品备注", width = 15)
    @ApiModelProperty(value = "商品备注")
    private String remark;
	/**供货商id，0对应无厂商，临时购买，其他对应厂家*/
	@Excel(name = "供货商id，0对应无厂商，临时购买，其他对应厂家", width = 15)
    @ApiModelProperty(value = "供货商id，0对应无厂商，临时购买，其他对应厂家")
    private Integer collaboratorId;
	/**品牌id*/
	@Excel(name = "品牌id", width = 15)
    @ApiModelProperty(value = "品牌id")
    private Integer brandId;
}
