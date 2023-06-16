package org.jeecg.modules.bookkeeping.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.bookkeeping.entity.BkCollaborator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 供货商/客户
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
public interface BkCollaboratorMapper extends BaseMapper<BkCollaborator> {
    /**
     * 获取所有采购个体的合作伙伴数据（包含采购销售都有）
     * @param page 分页对象，默认取每页数的一半（用于分组）
     * @return 分页结果
     */
    IPage<BkCollaborator> getAllPurchaseSelfCollaborator(Page<BkCollaborator> page);

    /**
     * 获取所有采购公司的合作伙伴数据（包含采购销售都有）
     * @param page 分页对象，默认取每页数的一半（用于分组）
     * @return 分页结果
     */
    IPage<BkCollaborator> getAllPurchaseCompanyCollaborator(Page<BkCollaborator> page);

    /**
     * 获取所有销售个体的合作伙伴数据（包含采购销售都有）
     * @param page 分页对象，默认取每页数的一半（用于分组）
     * @return 分页结果
     */
    IPage<BkCollaborator> getAllSellSelfCollaborator(Page<BkCollaborator> page);

    /**
     * 获取所有销售公司的合作伙伴数据（包含采购销售都有）
     * @param page 分页对象，默认取每页数的一半（用于分组）
     * @return 分页结果
     */
    IPage<BkCollaborator> getAllSellCompanyCollaborator(Page<BkCollaborator> page);
}
