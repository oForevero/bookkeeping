package org.jeecg.bookkeeping.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.bookkeeping.entity.BkPurchase;
import org.jeecg.bookkeeping.service.IBkPurchaseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: bk_purchase
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_purchase")
@RestController
@RequestMapping("/org.jeecg.bookkeeping/bkPurchase")
@Slf4j
public class BkPurchaseController extends JeecgController<BkPurchase, IBkPurchaseService> {
	@Autowired
	private IBkPurchaseService bkPurchaseService;
	
	/**
	 * 分页列表查询
	 *
	 * @param bkPurchase
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_purchase-分页列表查询")
	@ApiOperation(value="bk_purchase-分页列表查询", notes="bk_purchase-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkPurchase>> queryPageList(BkPurchase bkPurchase,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkPurchase> queryWrapper = QueryGenerator.initQueryWrapper(bkPurchase, req.getParameterMap());
		Page<BkPurchase> page = new Page<BkPurchase>(pageNo, pageSize);
		IPage<BkPurchase> pageList = bkPurchaseService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param bkPurchase
	 * @return
	 */
	@AutoLog(value = "bk_purchase-添加")
	@ApiOperation(value="bk_purchase-添加", notes="bk_purchase-添加")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkPurchase bkPurchase) {
		bkPurchaseService.save(bkPurchase);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param bkPurchase
	 * @return
	 */
	@AutoLog(value = "bk_purchase-编辑")
	@ApiOperation(value="bk_purchase-编辑", notes="bk_purchase-编辑")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkPurchase bkPurchase) {
		bkPurchaseService.updateById(bkPurchase);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_purchase-通过id删除")
	@ApiOperation(value="bk_purchase-通过id删除", notes="bk_purchase-通过id删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkPurchaseService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_purchase-批量删除")
	@ApiOperation(value="bk_purchase-批量删除", notes="bk_purchase-批量删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkPurchaseService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_purchase-通过id查询")
	@ApiOperation(value="bk_purchase-通过id查询", notes="bk_purchase-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkPurchase> queryById(@RequestParam(name="id",required=true) String id) {
		BkPurchase bkPurchase = bkPurchaseService.getById(id);
		if(bkPurchase==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkPurchase);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkPurchase
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkPurchase bkPurchase) {
        return super.exportXls(request, bkPurchase, BkPurchase.class, "bk_purchase");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_purchase:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkPurchase.class);
    }

}
