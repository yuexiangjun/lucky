package com.lucky.domain;

import com.lucky.domain.entity.OrderEntity;
import com.lucky.domain.entity.OrderPrizeEntity;
import com.lucky.domain.exception.BusinessException;
import com.lucky.domain.repository.OrderPrizeRepository;
import com.lucky.domain.repository.OrderRepository;
import com.lucky.domain.valueobject.BaseDataPage;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderPrizeRepository orderPrizeRepository;

    public OrderService(OrderRepository orderRepository, OrderPrizeRepository orderPrizeRepository) {
        this.orderRepository = orderRepository;
        this.orderPrizeRepository = orderPrizeRepository;
    }

    /**
     * 添加/修改订单
     */
    public Long saveOrUpdate(OrderEntity entity) {
        return orderRepository.saveOrUpdate(entity);
    }

    /**
     * 列表
     */
    public List<OrderEntity> list(OrderEntity entity) {
        return orderRepository.list(entity);
    }

    /**
     * 更据id查询
     */
    public OrderEntity getById(Long id) {
        if (Objects.isNull(id))
            return null;
        var order = orderRepository.getById(id);

        var orderEntities = new ArrayList<OrderEntity>();
        orderEntities.add(order);

        return CollectionUtils.firstElement(this.getOrderEntities(orderEntities));

    }

    /**
     * 修改状态
     */
    public void updateStatus(Long id, Integer status) {
        if (Objects.isNull(id))
            throw BusinessException.newInstance("id不能为空");
        var order = OrderEntity.builder()
                .id(id)
                .status(status)
                .build();
        if (Objects.equals(1, status))
            order.setSendTime(LocalDateTime.now());
        if (Objects.equals(2, status))
            order.setFinishTime(LocalDateTime.now());

        orderRepository.saveOrUpdate(order);
    }

    public BaseDataPage<OrderEntity> page(OrderEntity entity, Integer page, Integer size) {

        var orderPage = orderRepository.page(entity, page, size);

        var orderEntities = orderPage.getDataList();

        if (CollectionUtils.isEmpty(orderEntities))
            return orderPage;

        orderEntities = this.getOrderEntities(orderEntities);

        orderPage.setDataList(orderEntities);

        return orderPage;
    }


    public List<OrderEntity> findByTopicId(Long topicId) {
        var orderEntities = orderRepository.list(OrderEntity.builder().topicId(topicId).build());

        return this.getOrderEntities(orderEntities);


    }

    private List<OrderEntity> getOrderEntities(List<OrderEntity> orderEntities) {
        if (CollectionUtils.isEmpty(orderEntities))
            return List.of();

        var orderIds = orderEntities.stream()
                .map(OrderEntity::getId)
                .collect(Collectors.toList());

        var orderPrizeEntities = orderPrizeRepository.findByOrderIdIn(orderIds);

        if (CollectionUtils.isEmpty(orderPrizeEntities))
            return orderEntities;

        var orderPrizeMap = orderPrizeEntities
                .stream()
                .collect(Collectors.groupingBy(OrderPrizeEntity::getOrderId));

        return orderEntities.stream()
                .peek(s -> s.setOrderPrizeEntities(orderPrizeMap.getOrDefault(s.getId(), List.of())))
                .collect(Collectors.toList());
    }

    /**
     * 添加订单
     */
    public void save(List<Long> prizeIds,
                     Long topicId,
                     Long sessionId,
                     Long wechatUserId,
                     Long payOrderId) {

        var orderEntity = OrderEntity.builder()
                .payOrderId(payOrderId)
                .topicId(topicId)
                .sessionId(sessionId)
                .wechatUserId(wechatUserId)
                .status(0)
                .createTime(LocalDateTime.now())
                .build();

        var orderId = orderRepository.saveOrUpdate(orderEntity);

        var orderPrizeEntities = prizeIds.stream()
                .map(s -> OrderPrizeEntity.builder()
                        .orderId(orderId)
                        .wechatUserId(wechatUserId)
                        .productId(s)
                        .sessionId(sessionId)
                        .build()
                ).collect(Collectors.toList());

        orderPrizeRepository.saveBatch(orderPrizeEntities);

    }

    public  List<OrderPrizeEntity> findByPrizeIds(List<Long> prizeInfoIds) {

      return   orderPrizeRepository .findByPrizeIds(prizeInfoIds);
    }
}
