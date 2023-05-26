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
 * @Description: bk_collaborator_employee
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Data
@TableName("bk_collaborator_employee")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bk_collaborator_employee对象", description="bk_collaborator_employee")
public class BkCollaboratorEmployee implements Serializable {
    private static final long serialVersionUID = 1L;

	/**员工表主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "员工表主键")
    private Integer id;
	/**员工姓名*/
	@Excel(name = "员工姓名", width = 15)
    @ApiModelProperty(value = "员工姓名")
    private String employeeName;
	/**员工手机号*/
	@Excel(name = "员工手机号", width = 15)
    @ApiModelProperty(value = "员工手机号")
    private Integer employeePhone;
	/**员工是否继续合作，0正常，1不合作或离职*/
	@Excel(name = "员工是否继续合作，0正常，1不合作或离职", width = 15)
    @ApiModelProperty(value = "员工是否继续合作，0正常，1不合作或离职")
    private Integer employeeStatus;
}
