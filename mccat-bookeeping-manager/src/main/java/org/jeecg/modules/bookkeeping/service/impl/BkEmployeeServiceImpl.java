package org.jeecg.modules.bookkeeping.service.impl;

import org.jeecg.modules.bookkeeping.entity.BkEmployee;
import org.jeecg.modules.bookkeeping.mapper.BkEmployeeMapper;
import org.jeecg.modules.bookkeeping.service.IBkEmployeeService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: bk_employee
 * @Author: Raven
 * @Date:   2023-05-26
 * @Version: V1.0
 */
@Service
public class BkEmployeeServiceImpl extends ServiceImpl<BkEmployeeMapper, BkEmployee> implements IBkEmployeeService {

}
