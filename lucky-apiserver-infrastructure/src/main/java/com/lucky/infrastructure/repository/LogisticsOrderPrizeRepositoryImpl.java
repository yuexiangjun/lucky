package com.lucky.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.domain.repository.LogisticsOrderPrizeRepository;
import com.lucky.infrastructure.repository.mysql.mapper.LogisticsOrderPrizeMapper;
import com.lucky.infrastructure.repository.mysql.po.LogisticsOrderPrizePO;
import org.springframework.stereotype.Component;

/**
 * 物流订单关联商品
 */
@Component
public class LogisticsOrderPrizeRepositoryImpl extends ServiceImpl<LogisticsOrderPrizeMapper, LogisticsOrderPrizePO> implements LogisticsOrderPrizeRepository {

}
