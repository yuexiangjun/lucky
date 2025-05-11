package com.lucky.domain.valueobject;

import lombok.Data;

@Data
public class PrizePublicity {
    /**
     * 中奖者姓名
     */
    private String name;
    /**
     * 中奖者头像
     */
    private String avatar;
    /**
     * 系列名称
     */
    private String seriesName;
    /**
     * 奖品名称
     */
    private String prizeName;
    /**
     * 中奖时间
     */
    private String time;
}
