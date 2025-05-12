package com.lucky.domain.repository;

import com.lucky.domain.entity.OrderPrizeEntity;

import java.util.List;

/**
 * 订单关联商品
 */

public  interface OrderPrizeRepository {

    void saveBatch(List<OrderPrizeEntity> orderPrizeEntities);

    List<OrderPrizeEntity> findByOrderIdIn(List<Long> orderIds);

    List<OrderPrizeEntity> findByPrizeIds(List<Long> prizeInfoIds);
}
