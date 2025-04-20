package com.lucky.application.tripartite;

import com.lucky.domain.tripartite.WechatService;

import com.lucky.domain.valueobject.Code2Session;
import com.lucky.domain.valueobject.WechatConfigValueObject;
import com.lucky.domain.valueobject.WechatPhone;
import org.springframework.stereotype.Component;

@Component
public class WechatServer {
	private final WechatService wechatService;

	public WechatServer(WechatService wechatService) {
		this.wechatService = wechatService;
	}


	/**
	 * 获取微信配置
	 *
	 * @return
	 */
	public WechatConfigValueObject getConfig() {
		return wechatService.getConfig();
	}


	/**
	 * 获取微信accessToken
	 *
	 * @return
	 */
	public String getAccessToken() {
		return wechatService.getAccessToken();
	}


	/**
	 * 根据获取手机号码的组件code获取手机号码
	 *
	 * @param code
	 * @return
	 */

	public WechatPhone getPhoneNumberToken(String code) {
		return wechatService.getPhoneNumberToken(code);
	}

	/**
	 * 小程序登录参数
	 */
	public Code2Session code2Session(String jsCode) {
		return wechatService.code2Session(jsCode);
	}

}
