package com.lucky.controller.external;


import com.lucky.application.WechatUserServer;
import com.lucky.application.interceptor.LoginUserEntity;
import com.lucky.controller.common.BaseController;
import com.lucky.controller.external.dto.WechatUserInfoDTO;
import com.lucky.controller.external.vo.WechatUserInfoVO;
import com.lucky.domain.entity.WechatUserEntity;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

/**
 * 小程序的业务系统用户
 *
 * @folder API/小程序/业务系统用户
 */
@RestController
@RequestMapping("/wechat/user")
public class WechatUserController extends BaseController {
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

	/**
	 * 获取用户信息
	 */
	@GetMapping("/get-info")
	@ResponseFormat
	public WechatUserInfoVO getInfo() {
		var entity = wechatUserServer.getInfo(this.getWechatUserId());
		return WechatUserInfoVO.toEntity(entity);

	}
	/**
	 * 修改信息
	 */
	@PutMapping("/update-info")
	@ResponseFormat
	public void updateInfo(@RequestBody WechatUserInfoDTO dto) {
		WechatUserEntity entity = WechatUserInfoDTO.toEntity(dto);
		entity.setId(this.getWechatUserId());
		wechatUserServer.saveOrUpdate(entity);
	}

}
