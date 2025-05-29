package com.lucky.controller.external.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WechatOrderListVO {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 系列名称
	 */
	private String seriesName;
	/**
	 * 场次编号
	 */
	private String sessionName;
	/**
	 * 时间
	 */
	private LocalDateTime createTime;
	/**
	 * 状态 2：完成
	 */
	private Integer status;
	/**
	 * 商品详情
	 */
	/**
	 * 商品信息
	 */
	private List<WechatPrizeInfoVO> goods;
}
