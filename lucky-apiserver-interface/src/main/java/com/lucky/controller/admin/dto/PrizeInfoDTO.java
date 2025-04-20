package com.lucky.controller.admin.dto;

import com.lucky.domain.entity.PrizeInfoEntity;
import com.lucky.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 奖品
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrizeInfoDTO {
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
     * 奖品等级
     */
    private Long gradeId;
    /**
     * 主题id
     */
    private Long topicId;
    /**
     * 奖品名称
     */
    private String prizeName;
    /**
     * 奖品图片
     */
    private String prizeUrl;
    /**
     * 库存
     */
    private Integer inventory;
    public static PrizeInfoEntity toEntity(PrizeInfoDTO dto){
        if (Objects.isNull(dto))
            throw BusinessException.newInstance("缺少参数");
        return PrizeInfoEntity.builder()
                .id(dto.getId())
                .type(dto.getType())
                .gradeId(dto.getGradeId())
                .prizeName(dto.getPrizeName())
                .prizeUrl(dto.getPrizeUrl())
                .inventory(dto.getInventory())
                .build();
    }
}