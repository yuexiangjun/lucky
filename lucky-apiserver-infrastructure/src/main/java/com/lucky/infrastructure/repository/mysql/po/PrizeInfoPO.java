package com.lucky.infrastructure.repository.mysql.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lucky.domain.entity.PrizeInfoEntity;
import com.lucky.domain.entity.SeriesTopicEntity;
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
@TableName(value = "prize_info")
public class PrizeInfoPO {
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
     * 主题id
     */
    private Long topicId;
    /**
     * 奖品等级
     */
    private Long gradeId;
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
    public static PrizeInfoPO getInstance(PrizeInfoEntity entity) {
        if (Objects.isNull(entity))
            return null;
        return PrizeInfoPO.builder()
                .id(entity.getId())
                .type(entity.getType())
                .topicId(entity.getTopicId())
                .gradeId(entity.getGradeId())
                .prizeName(entity.getPrizeName())
                .prizeUrl(entity.getPrizeUrl())
                .inventory(entity.getInventory())
                .build();

    }

    public static PrizeInfoEntity toEntity(PrizeInfoPO po) {
        if (Objects.isNull(po))

            return null;
        return PrizeInfoEntity.builder()
                .id(po.getId())
                .type(po.getType())
                .topicId(po.getTopicId())
                .gradeId(po.getGradeId())
                .prizeName(po.getPrizeName())
                .prizeUrl(po.getPrizeUrl())
                .inventory(po.getInventory())
                .build();

    }
}
