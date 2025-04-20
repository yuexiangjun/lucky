package com.lucky.controller.external;

import com.lucky.application.tripartite.WechatServer;
import com.lucky.controller.external.vo.Code2SessionVO;
import com.lucky.controller.external.vo.WechatConfigVO;
import com.lucky.controller.external.vo.WechatPhoneVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序的三方接口
 *
 * @folder API/小程序/小程序的三方接口
 */
@RestController
@RequestMapping("/wechat/tripartite")
public class WechatTripartiteController {
	private final WechatServer wechatServer;

	public WechatTripartiteController(WechatServer wechatServer) {
		this.wechatServer = wechatServer;
	}

	/**
	 * 获取微信配置
	 *
	 * @return
	 */
	@GetMapping("/config")
	@ResponseFormat
	public WechatConfigVO getConfig() {
		var config = wechatServer.getConfig();
		return WechatConfigVO.builder()
				.appid(config.getAppid())
				.secret(config.getSecret())
				.build();

	}

	/**
	 * 获取微信accessToken
	 *
	 * @return
	 */
	@GetMapping("/access-token")
	@ResponseFormat
	public String getAccessToken() {
		return wechatServer.getAccessToken();
	}


	/**
	 * 根据获取手机号码的组件code 获取手机号码
	 *
	 * @param code
	 * @return
	 */
	@GetMapping("/code")
	@ResponseFormat
	public WechatPhoneVO getPhoneNumberToken(@RequestParam String code) {
		var phoneNumberToken = wechatServer.getPhoneNumberToken(code);
		return WechatPhoneVO.getInstance(phoneNumberToken);
	}

	/**
	 * 小程序登录参数
	 */
	@GetMapping("/login/js-code")
	@ResponseFormat
	public Code2SessionVO code2Session(@RequestParam String jsCode) {
		var code2Session = wechatServer.code2Session(jsCode);
		return Code2SessionVO.builder()
				.openid(code2Session.getOpenid())
				.sessionKey(code2Session.getSessionKey())
				.build();
	}
	/**
	 *业务系统的登录注册
	 */
	@GetMapping("/login/register")
	@ResponseFormat
	public void loginRegister(@RequestParam String phone,@RequestParam String password) {

	}


}
