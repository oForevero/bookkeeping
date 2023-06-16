package org.jeecg.modules.bookkeeping.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.bookkeeping.entity.BkProduct;
import org.jeecg.modules.bookkeeping.service.IBkProductService;

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
 * @Description: bk_product
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_product")
@RestController
@RequestMapping("/bookkeeping/bkProduct")
@Slf4j
public class BkProductController extends JeecgController<BkProduct, IBkProductService> {
	@Autowired
	private IBkProductService bkProductService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bkProduct
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_product-分页列表查询")
	@ApiOperation(value="bk_product-分页列表查询", notes="bk_product-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkProduct>> queryPageList(BkProduct bkProduct,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkProduct> queryWrapper = QueryGenerator.initQueryWrapper(bkProduct, req.getParameterMap());
		Page<BkProduct> page = new Page<BkProduct>(pageNo, pageSize);
		IPage<BkProduct> pageList = bkProductService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bkProduct
	 * @return
	 */
	@AutoLog(value = "bk_product-添加")
	@ApiOperation(value="bk_product-添加", notes="bk_product-添加")
	@RequiresPermissions("bookkeeping:bk_product:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkProduct bkProduct) {
		bkProductService.save(bkProduct);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bkProduct
	 * @return
	 */
	@AutoLog(value = "bk_product-编辑")
	@ApiOperation(value="bk_product-编辑", notes="bk_product-编辑")
	@RequiresPermissions("bookkeeping:bk_product:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkProduct bkProduct) {
		bkProductService.updateById(bkProduct);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_product-通过id删除")
	@ApiOperation(value="bk_product-通过id删除", notes="bk_product-通过id删除")
	@RequiresPermissions("bookkeeping:bk_product:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkProductService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_product-批量删除")
	@ApiOperation(value="bk_product-批量删除", notes="bk_product-批量删除")
	@RequiresPermissions("bookkeeping:bk_product:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkProductService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_product-通过id查询")
	@ApiOperation(value="bk_product-通过id查询", notes="bk_product-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkProduct> queryById(@RequestParam(name="id",required=true) String id) {
		BkProduct bkProduct = bkProductService.getById(id);
		if(bkProduct==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkProduct);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkProduct
    */
    @RequiresPermissions("bookkeeping:bk_product:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkProduct bkProduct) {
        return super.exportXls(request, bkProduct, BkProduct.class, "bk_product");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("bookkeeping:bk_product:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkProduct.class);
    }

}
