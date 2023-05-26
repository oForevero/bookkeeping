package org.jeecg.modules.bookkeeping.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.bookkeeping.entity.BkCollaborator;
import org.jeecg.modules.bookkeeping.service.IBkCollaboratorService;

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
 * @Description: bk_collaborator
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Api(tags="bk_collaborator")
@RestController
@RequestMapping("/org.jeecg.bookkeeping/bkCollaborator")
@Slf4j
public class BkCollaboratorController extends JeecgController<BkCollaborator, IBkCollaboratorService> {
	@Autowired
	private IBkCollaboratorService bkCollaboratorService;

	/**
	 * 分页列表查询
	 *
	 * @param bkCollaborator
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "bk_collaborator-分页列表查询")
	@ApiOperation(value="bk_collaborator-分页列表查询", notes="bk_collaborator-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<BkCollaborator>> queryPageList(BkCollaborator bkCollaborator,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<BkCollaborator> queryWrapper = QueryGenerator.initQueryWrapper(bkCollaborator, req.getParameterMap());
		Page<BkCollaborator> page = new Page<BkCollaborator>(pageNo, pageSize);
		IPage<BkCollaborator> pageList = bkCollaboratorService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param bkCollaborator
	 * @return
	 */
	@AutoLog(value = "bk_collaborator-添加")
	@ApiOperation(value="bk_collaborator-添加", notes="bk_collaborator-添加")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody BkCollaborator bkCollaborator) {
		bkCollaboratorService.save(bkCollaborator);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param bkCollaborator
	 * @return
	 */
	@AutoLog(value = "bk_collaborator-编辑")
	@ApiOperation(value="bk_collaborator-编辑", notes="bk_collaborator-编辑")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody BkCollaborator bkCollaborator) {
		bkCollaboratorService.updateById(bkCollaborator);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "bk_collaborator-通过id删除")
	@ApiOperation(value="bk_collaborator-通过id删除", notes="bk_collaborator-通过id删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		bkCollaboratorService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "bk_collaborator-批量删除")
	@ApiOperation(value="bk_collaborator-批量删除", notes="bk_collaborator-批量删除")
	@RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.bkCollaboratorService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "bk_collaborator-通过id查询")
	@ApiOperation(value="bk_collaborator-通过id查询", notes="bk_collaborator-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<BkCollaborator> queryById(@RequestParam(name="id",required=true) String id) {
		BkCollaborator bkCollaborator = bkCollaboratorService.getById(id);
		if(bkCollaborator==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(bkCollaborator);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param bkCollaborator
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, BkCollaborator bkCollaborator) {
        return super.exportXls(request, bkCollaborator, BkCollaborator.class, "bk_collaborator");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("org.jeecg.bookkeeping:bk_collaborator:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, BkCollaborator.class);
    }

}
