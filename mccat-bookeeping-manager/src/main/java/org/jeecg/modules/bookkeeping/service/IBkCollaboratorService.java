package org.jeecg.modules.bookkeeping.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.bookkeeping.domain.utils.OptSelectResult;
import org.jeecg.modules.bookkeeping.entity.BkCollaborator;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Description: 供货商/客户
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
public interface IBkCollaboratorService extends IService<BkCollaborator> {
    /**
     * 用于 select 的供货商数据
     * @param page 分页对象
     * @param name 姓名
     * @return 供货商人员page数据
     */
    List<OptSelectResult<BkCollaborator>> listPurchaseCollaborator(Page<BkCollaborator> page, String name);

    /**
     * 用于 select 的销售客户数据
     * @param page 分页对象
     * @param name 姓名
     * @return 销售人员page数据
     */
    List<OptSelectResult<BkCollaborator>> listSellCollaborator(Page<BkCollaborator> page, String name);
}
