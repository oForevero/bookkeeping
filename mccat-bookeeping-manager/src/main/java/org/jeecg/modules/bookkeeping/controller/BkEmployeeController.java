package org.jeecg.modules.bookkeeping.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.bookkeeping.entity.BkEmployee;
import org.jeecg.modules.bookkeeping.service.IBkEmployeeService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: bk_employee
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_employee")
@RestController
@RequestMapping("/org.jeecg.bookkeeping/bkEmployee")
@Slf4j
public class BkEmployeeController extends JeecgController<BkEmployee, IBkEmployeeService> {
	@Autowired
	private IBkEmployeeService bkEmployeeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bkEmployee
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_employee-分页列表查询")
	@ApiOperation(value="bk_employee-分页列表查询", notes="bk_employee-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkEmployee>> queryPageList(BkEmployee bkEmployee,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkEmployee> queryWrapper = QueryGenerator.initQueryWrapper(bkEmployee, req.getParameterMap());
		Page<BkEmployee> page = new Page<BkEmployee>(pageNo, pageSize);
		IPage<BkEmployee> pageList = bkEmployeeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bkEmployee
	 * @return
	 */
	@AutoLog(value = "bk_employee-添加")
	@ApiOperation(value="bk_employee-添加", notes="bk_employee-添加")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_employee:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkEmployee bkEmployee) {
		bkEmployeeService.save(bkEmployee);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bkEmployee
	 * @return
	 */
	@AutoLog(value = "bk_employee-编辑")
	@ApiOperation(value="bk_employee-编辑", notes="bk_employee-编辑")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_employee:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkEmployee bkEmployee) {
		bkEmployeeService.updateById(bkEmployee);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_employee-通过id删除")
	@ApiOperation(value="bk_employee-通过id删除", notes="bk_employee-通过id删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_employee:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkEmployeeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_employee-批量删除")
	@ApiOperation(value="bk_employee-批量删除", notes="bk_employee-批量删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_employee:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkEmployeeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_employee-通过id查询")
	@ApiOperation(value="bk_employee-通过id查询", notes="bk_employee-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkEmployee> queryById(@RequestParam(name="id",required=true) String id) {
		BkEmployee bkEmployee = bkEmployeeService.getById(id);
		if(bkEmployee==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkEmployee);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkEmployee
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_employee:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkEmployee bkEmployee) {
        return super.exportXls(request, bkEmployee, BkEmployee.class, "bk_employee");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_employee:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkEmployee.class);
    }

}
