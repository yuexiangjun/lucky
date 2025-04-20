package com.lucky.controller.admin.vo;

import com.lucky.domain.entity.GradeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 奖项
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GradeVO {
    /**
     * id
     */
    private Long id;

    /**
     * 类别
     * 1:隐藏
     * 2：普通级别
     */
    private Integer type;
    /**
     * 名称
     */
    private String name;
    /**
     * 概率 隐藏类型的必传 普通级别的不用传
     */
    private BigDecimal probability;
    /**
     * 是否启用
     */
    private  Boolean status;

    public static GradeVO getInstance(GradeEntity entity) {
        if (Objects.isNull(entity))
            return null;
        return GradeVO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .name(entity.getName())
                .probability(entity.getProbability())
                .status(entity.getStatus())
                .build();
    }
}
