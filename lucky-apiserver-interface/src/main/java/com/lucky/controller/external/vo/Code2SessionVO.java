package com.lucky.controller.external.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Code2SessionVO {
	/**
	 * 微信用户唯一标识
	 */
	private String openid;
	/**
	 * 会话密钥
	 */
	private String sessionKey;
	/**
	 * token
	 */
	private String authorization;

}
