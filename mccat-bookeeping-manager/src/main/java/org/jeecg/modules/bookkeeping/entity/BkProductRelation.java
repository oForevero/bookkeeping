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
 * @Description: bk_product_relation
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_product_relation")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_product_relation对象", description="bk_product_relation")
public class BkProductRelation implements Serializable {
    private static final long serialVersionUID = 1L;

	/**product 关系表主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "product 关系表主键")
    private Integer id;
	/**关系名*/
	@Excel(name = "关系名", width = 15)
    @ApiModelProperty(value = "关系名")
    private String relationName;
	/**左右值算法左关联*/
	@Excel(name = "左右值算法左关联", width = 15)
    @ApiModelProperty(value = "左右值算法左关联")
    private Integer lft;
	/**左右值算法右关联*/
	@Excel(name = "左右值算法右关联", width = 15)
    @ApiModelProperty(value = "左右值算法右关联")
    private Integer rgt;
	/**关系表备注*/
	@Excel(name = "关系表备注", width = 15)
    @ApiModelProperty(value = "关系表备注")
    private String relationRemark;
	/**供货商id，0对应无厂商，临时购买，其他对应厂家*/
	@Excel(name = "供货商id，0对应无厂商，临时购买，其他对应厂家", width = 15)
    @ApiModelProperty(value = "供货商id，0对应无厂商，临时购买，其他对应厂家")
    private Integer collaboratorId;
	/**品牌id*/
	@Excel(name = "品牌id", width = 15)
    @ApiModelProperty(value = "品牌id")
    private Integer brandId;
}
