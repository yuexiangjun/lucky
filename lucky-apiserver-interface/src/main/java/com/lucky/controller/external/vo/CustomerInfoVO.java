package com.lucky.controller.external.vo;

import cn.hutool.core.bean.BeanUtil;
import com.lucky.domain.entity.CustomerInfoEntity;
import com.lucky.domain.entity.DeliveryAddressEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 客服信息
 */
@Data
public class CustomerInfoVO {
    /**
     * id
     */
    private Long id;
    /**
     * 客服名称
     */
    private String name;
    /**
     * 客服电话
     */
    private String phone;
    /**
     * 客服微信
     */
    private String wechat;


    public static CustomerInfoVO getInstance(CustomerInfoEntity entity) {

        if (Objects.isNull(entity))
            return null;

        return BeanUtil.toBean(entity, CustomerInfoVO.class);

    }
}
