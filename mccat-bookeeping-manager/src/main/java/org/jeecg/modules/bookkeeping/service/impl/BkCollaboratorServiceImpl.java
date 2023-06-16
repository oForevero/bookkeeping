package org.jeecg.modules.bookkeeping.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.bookkeeping.domain.utils.OptSelectResult;
import org.jeecg.modules.bookkeeping.entity.BkCollaborator;
import org.jeecg.modules.bookkeeping.mapper.BkCollaboratorMapper;
import org.jeecg.modules.bookkeeping.service.IBkCollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 客户/供货商 service
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Service
public class BkCollaboratorServiceImpl extends ServiceImpl<BkCollaboratorMapper, BkCollaborator> implements IBkCollaboratorService {
    @Resource
    BkCollaboratorMapper mapper;

    @Override
    public List<OptSelectResult<BkCollaborator>> listPurchaseCollaborator(Page<BkCollaborator> page, String name) {
        List<OptSelectResult<BkCollaborator>> bkCollaboratorPurchase= new ArrayList<>();
        //获取个体数据
        bkCollaboratorPurchase.add(new OptSelectResult<>("selfPurchase", mapper.getAllPurchaseSelfCollaborator(page, name).getRecords()));
        //获取个体数据
        bkCollaboratorPurchase.add(new OptSelectResult<>("companyPurchase", mapper.getAllPurchaseCompanyCollaborator(page, name).getRecords()));
        return bkCollaboratorPurchase;
    }

    @Override
    public List<OptSelectResult<BkCollaborator>> listSellCollaborator(Page<BkCollaborator> page, String name) {
        List<OptSelectResult<BkCollaborator>> bkCollaboratorSell= new ArrayList<>();
        //获取个体数据
        bkCollaboratorSell.add(new OptSelectResult<>("selfSell", mapper.getAllSellSelfCollaborator(page, name).getRecords()));
        //获取个体数据
        bkCollaboratorSell.add(new OptSelectResult<>("companySell", mapper.getAllSellCompanyCollaborator(page, name).getRecords()));
        return bkCollaboratorSell;
    }
}
