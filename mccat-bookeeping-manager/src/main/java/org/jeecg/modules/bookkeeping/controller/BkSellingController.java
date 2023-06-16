package org.jeecg.modules.bookkeeping.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.bookkeeping.entity.BkSelling;
import org.jeecg.modules.bookkeeping.service.IBkSellingService;

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
 * @Description: bk_selling
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_selling")
@RestController
@RequestMapping("/bookkeeping/bkSelling")
@Slf4j
public class BkSellingController extends JeecgController<BkSelling, IBkSellingService> {
	@Autowired
	private IBkSellingService bkSellingService;

	/**
	 * 分页列表查询
	 *
	 * @param bkSelling
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_selling-分页列表查询")
	@ApiOperation(value="bk_selling-分页列表查询", notes="bk_selling-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkSelling>> queryPageList(BkSelling bkSelling,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkSelling> queryWrapper = QueryGenerator.initQueryWrapper(bkSelling, req.getParameterMap());
		Page<BkSelling> page = new Page<BkSelling>(pageNo, pageSize);
		IPage<BkSelling> pageList = bkSellingService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param bkSelling
	 * @return
	 */
	@AutoLog(value = "bk_selling-添加")
	@ApiOperation(value="bk_selling-添加", notes="bk_selling-添加")
	@RequiresPermissions("bookkeeping:bk_selling:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkSelling bkSelling) {
		bkSellingService.save(bkSelling);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param bkSelling
	 * @return
	 */
	@AutoLog(value = "bk_selling-编辑")
	@ApiOperation(value="bk_selling-编辑", notes="bk_selling-编辑")
	@RequiresPermissions("bookkeeping:bk_selling:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkSelling bkSelling) {
		bkSellingService.updateById(bkSelling);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_selling-通过id删除")
	@ApiOperation(value="bk_selling-通过id删除", notes="bk_selling-通过id删除")
	@RequiresPermissions("bookkeeping:bk_selling:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkSellingService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_selling-批量删除")
	@ApiOperation(value="bk_selling-批量删除", notes="bk_selling-批量删除")
	@RequiresPermissions("bookkeeping:bk_selling:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkSellingService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_selling-通过id查询")
	@ApiOperation(value="bk_selling-通过id查询", notes="bk_selling-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkSelling> queryById(@RequestParam(name="id",required=true) String id) {
		BkSelling bkSelling = bkSellingService.getById(id);
		if(bkSelling==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkSelling);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkSelling
    */
    @RequiresPermissions("bookkeeping:bk_selling:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkSelling bkSelling) {
        return super.exportXls(request, bkSelling, BkSelling.class, "bk_selling");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("bookkeeping:bk_selling:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkSelling.class);
    }

}
