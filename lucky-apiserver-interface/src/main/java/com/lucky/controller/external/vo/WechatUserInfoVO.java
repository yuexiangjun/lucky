package com.lucky.controller.external.vo;

import com.lucky.domain.entity.WechatUserEntity;
import com.lucky.domain.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WechatUserInfoVO {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 微信头像
	 */
	private String avatarUrl;
	/**
	 * openId
	 */
	private String openId;

	/**
	 * 不加区号的手机号码
	 */
	private String phone;
	public static WechatUserInfoVO toEntity(WechatUserEntity entity) {
		if (Objects.isNull(entity))
			throw BusinessException.newInstance("参数缺失");
		return WechatUserInfoVO.builder()
				.id(entity.getId())
				.nickName(Objects.isNull(entity.getName())? entity.getPhone() : entity.getName())
				.avatarUrl(entity.getAvatar())
				.openId(entity.getOpenid())
				.phone(entity.getPhone())
				.build();


	}

}
