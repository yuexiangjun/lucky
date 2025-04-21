package com.lucky.controller.admin.vo;

import com.lucky.domain.entity.SeriesTopicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 主题 系列
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeriesTopicVO {
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 主题颜色
     */
    private String topicColor;
    /**
     * 主题图片
     */
    private String topicUrl;

    /**
     * 场次(一共多少场)
     */
    private Integer session;
    /**
     * 价格（多少钱一抽）
     */
    private BigDecimal price;

    /**
     * 是否启用
     */
    private Boolean status;

    public static SeriesTopicVO getInstance(SeriesTopicEntity entity) {

        return SeriesTopicVO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .topicColor(entity.getTopicColor())
                .status(entity.getStatus())
                .session(entity.getSession())
                .topicUrl(entity.getTopicUrl())
                .build();

    }


}
