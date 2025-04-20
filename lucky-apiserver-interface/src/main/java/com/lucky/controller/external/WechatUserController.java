package com.lucky.controller.external;


import com.lucky.application.WechatUserServer;
import com.lucky.application.interceptor.LoginUserEntity;
import com.lucky.controller.external.dto.WechatUserInfoDTO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序的业务系统用户
 *
 * @folder API/小程序/业务系统用户
 */
@RestController
@RequestMapping("/wechat/user")
public class WechatUserController {
	private final WechatUserServer wechatUserServer;

	public WechatUserController(WechatUserServer wechatUserServer) {
		this.wechatUserServer = wechatUserServer;
	}


	/**
	 * 登录注册
	 */
	@PostMapping("/login-enroll")
	@ResponseFormat
	public LoginUserEntity login(@RequestBody WechatUserInfoDTO dto) {

		var entity = WechatUserInfoDTO.toEntity(dto);

		return wechatUserServer.login(entity);

	}
}
