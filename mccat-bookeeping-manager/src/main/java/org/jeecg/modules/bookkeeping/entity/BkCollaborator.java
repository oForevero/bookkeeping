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
 * @Description: 供货商/客户
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("供货商/客户")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="供货商/客户对象", description="供货商/客户")
public class BkCollaborator implements Serializable {
    private static final long serialVersionUID = 1L;

	/**厂商/个体id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "厂商/个体id")
    private Integer id;
	/**厂商/个体 公司名称*/
	@Excel(name = "厂商/个体 公司名称", width = 15)
    @ApiModelProperty(value = "厂商/个体 公司名称")
    private String companyName;
	/**厂商/个体 公司固话/手机号码*/
	@Excel(name = "厂商/个体 公司固话/手机号码", width = 15)
    @ApiModelProperty(value = "厂商/个体 公司固话/手机号码")
    private String companyTel;
	/**是否还在合作，0为正常，1为不合作*/
	@Excel(name = "是否还在合作，0为正常，1为不合作", width = 15)
    @ApiModelProperty(value = "是否还在合作，0为正常，1为不合作")
    private Integer collaboratorStatus;
	/**厂商/个体 默认员工id，当商户规模为厂家（2）时来添加员工作为采购/供货人*/
	@Excel(name = "厂商/个体 默认员工id，当商户规模为厂家（2）时来添加员工作为采购/供货人", width = 15)
    @ApiModelProperty(value = "厂商/个体 默认员工id，当商户规模为厂家（2）时来添加员工作为采购/供货人")
    private Integer employeeId;
	/**商户状态，0为只采购不销售（也就是客户），1为只销售不采购（也就是供货商），2为合作同行（既是客户又供货商）*/
	@Excel(name = "商户状态，0为只采购不销售（也就是客户），1为只销售不采购（也就是供货商），2为合作同行（既是客户又供货商）", width = 15)
    @ApiModelProperty(value = "商户状态，0为只采购不销售（也就是客户），1为只销售不采购（也就是供货商），2为合作同行（既是客户又供货商）")
    private Integer collaboratorType;
	/**默认匿名账户为0（有且只有一个）个体为 1  厂商为 2*/
	@Excel(name = "默认匿名账户为0（有且只有一个）个体为 1  厂商为 2", width = 15)
    @ApiModelProperty(value = "默认匿名账户为0（有且只有一个）个体为 1  厂商为 2")
    private Integer collaboratorScale;
}
