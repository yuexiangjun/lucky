package com.lucky.domain;

import com.lucky.domain.entity.OrderEntity;
import com.lucky.domain.exception.BusinessException;
import com.lucky.domain.repository.OrderRepository;
import com.lucky.domain.valueobject.BaseDataPage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
        return orderRepository.getById(id);
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
        return orderRepository.page(entity, page, size);
    }

    public Boolean saveBatch(List<Long> prizeIds,
                             Long topicId,
                             Long sessionId,
                             Long wechatUserId) {

        var orderEntities = prizeIds.stream()
                .map(s -> OrderEntity.builder()
                        .productId(s)
                        .topicId(topicId)
                        .sessionId(sessionId)
                        .wechatUserId(wechatUserId)
                        .status(0)
                        .createTime(LocalDateTime.now())
                        .build()
                ).collect(Collectors.toList());
        return orderRepository.saveBatch(orderEntities);
    }

    public List<OrderEntity> findByTopicId(Long topicId) {
        return orderRepository.list(OrderEntity.builder().topicId(topicId).build());
    }
}
