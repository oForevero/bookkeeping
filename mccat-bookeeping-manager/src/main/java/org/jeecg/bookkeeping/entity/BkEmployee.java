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
 * @Description: bk_employee
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_employee")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_employee对象", description="bk_employee")
public class BkEmployee implements Serializable {
    private static final long serialVersionUID = 1L;

	/**本公司所属员工id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "本公司所属员工id")
    private Integer id;
	/**本公司所属采购员工名*/
	@Excel(name = "本公司所属采购员工名", width = 15)
    @ApiModelProperty(value = "本公司所属采购员工名")
    private String employeeName;
	/**本公司所属采购员工手机号*/
	@Excel(name = "本公司所属采购员工手机号", width = 15)
    @ApiModelProperty(value = "本公司所属采购员工手机号")
    private Integer employeePhone;
	/**是否已离职 0正常，1已离职*/
	@Excel(name = "是否已离职 0正常，1已离职", width = 15)
    @ApiModelProperty(value = "是否已离职 0正常，1已离职")
    private Integer employeeStatus;
}
