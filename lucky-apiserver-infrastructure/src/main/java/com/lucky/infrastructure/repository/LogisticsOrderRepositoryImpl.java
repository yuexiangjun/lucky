package com.lucky.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.domain.repository.LogisticsOrderRepository;
import com.lucky.infrastructure.repository.mysql.mapper.LogisticsOrderMapper;
import com.lucky.infrastructure.repository.mysql.po.LogisticsOrderPO;
import org.springframework.stereotype.Component;

/**
 * 物流订单
 */

@Component
public class LogisticsOrderRepositoryImpl extends ServiceImpl<LogisticsOrderMapper, LogisticsOrderPO> implements LogisticsOrderRepository {

}
