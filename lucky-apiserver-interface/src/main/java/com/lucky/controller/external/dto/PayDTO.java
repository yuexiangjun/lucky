package com.lucky.controller.external.dto;

import cn.hutool.core.bean.BeanUtil;
import com.lucky.domain.entity.PayOrderEntity;
import com.lucky.domain.exception.BusinessException;
import lombok.Data;

import java.util.Objects;

@Data
public class PayDTO {
    /**
     * 支付人
     */
    private Long wechatUserId;
    /**
     * 主题id
     */
    private Long topicId;
    /**
     * 场次id
     */
    private Long sessionId;
    /**
     * 抽奖次数
     */
    private Integer times;

    public static PayOrderEntity toEntity(PayDTO dto) {
        if (Objects.isNull(dto))
            throw BusinessException.newInstance("缺少参数");
        return BeanUtil.toBean(dto, PayOrderEntity.class);


    }


}
