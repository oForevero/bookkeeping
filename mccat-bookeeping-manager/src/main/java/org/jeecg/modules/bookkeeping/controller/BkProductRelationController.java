package org.jeecg.modules.bookkeeping.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.bookkeeping.entity.BkProductRelation;
import org.jeecg.modules.bookkeeping.service.IBkProductRelationService;

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
 * @Description: bk_product_relation
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_product_relation")
@RestController
@RequestMapping("/bookkeeping/bkProductRelation")
@Slf4j
public class BkProductRelationController extends JeecgController<BkProductRelation, IBkProductRelationService> {
	@Autowired
	private IBkProductRelationService bkProductRelationService;

	/**
	 * 分页列表查询
	 *
	 * @param bkProductRelation
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_product_relation-分页列表查询")
	@ApiOperation(value="bk_product_relation-分页列表查询", notes="bk_product_relation-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkProductRelation>> queryPageList(BkProductRelation bkProductRelation,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkProductRelation> queryWrapper = QueryGenerator.initQueryWrapper(bkProductRelation, req.getParameterMap());
		Page<BkProductRelation> page = new Page<BkProductRelation>(pageNo, pageSize);
		IPage<BkProductRelation> pageList = bkProductRelationService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param bkProductRelation
	 * @return
	 */
	@AutoLog(value = "bk_product_relation-添加")
	@ApiOperation(value="bk_product_relation-添加", notes="bk_product_relation-添加")
	@RequiresPermissions("bookkeeping:bk_product_relation:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkProductRelation bkProductRelation) {
		bkProductRelationService.save(bkProductRelation);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param bkProductRelation
	 * @return
	 */
	@AutoLog(value = "bk_product_relation-编辑")
	@ApiOperation(value="bk_product_relation-编辑", notes="bk_product_relation-编辑")
	@RequiresPermissions("bookkeeping:bk_product_relation:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkProductRelation bkProductRelation) {
		bkProductRelationService.updateById(bkProductRelation);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_product_relation-通过id删除")
	@ApiOperation(value="bk_product_relation-通过id删除", notes="bk_product_relation-通过id删除")
	@RequiresPermissions("bookkeeping:bk_product_relation:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkProductRelationService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_product_relation-批量删除")
	@ApiOperation(value="bk_product_relation-批量删除", notes="bk_product_relation-批量删除")
	@RequiresPermissions("bookkeeping:bk_product_relation:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkProductRelationService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_product_relation-通过id查询")
	@ApiOperation(value="bk_product_relation-通过id查询", notes="bk_product_relation-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkProductRelation> queryById(@RequestParam(name="id",required=true) String id) {
		BkProductRelation bkProductRelation = bkProductRelationService.getById(id);
		if(bkProductRelation==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkProductRelation);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkProductRelation
    */
    @RequiresPermissions("bookkeeping:bk_product_relation:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkProductRelation bkProductRelation) {
        return super.exportXls(request, bkProductRelation, BkProductRelation.class, "bk_product_relation");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("bookkeeping:bk_product_relation:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkProductRelation.class);
    }

}
