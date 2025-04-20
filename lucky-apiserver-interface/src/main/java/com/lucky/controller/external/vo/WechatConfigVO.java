package com.lucky.controller.external.vo;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class WechatConfigVO {
	private String appid;
	private String secret;
}