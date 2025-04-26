package com.lucky.controller.external;

import com.lucky.application.WechatUserServer;
import com.lucky.application.tripartite.WechatServer;
import com.lucky.controller.external.vo.Code2SessionVO;
import com.lucky.controller.external.vo.WechatConfigVO;
import com.lucky.controller.external.vo.WechatPhoneVO;
import com.lucky.domain.entity.WechatUserEntity;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * 小程序的三方接口
 *
 * @folder API/小程序/小程序的三方接口
 */
@RestController
@RequestMapping("/wechat/tripartite")
public class WechatTripartiteController {
    private final WechatServer wechatServer;
    private final WechatUserServer wechatUserServer;

    public WechatTripartiteController(WechatServer wechatServer, WechatUserServer wechatUserServer) {
        this.wechatServer = wechatServer;
        this.wechatUserServer = wechatUserServer;
    }


    /**
     * 1：小程序登录参数
     */
    @GetMapping("/login/js-code")
    @ResponseFormat
    public Code2SessionVO code2Session(@RequestParam String jsCode) {
        var code2Session = wechatServer.code2Session(jsCode);
        return Code2SessionVO.builder()
                .openid(code2Session.getOpenid())
                .sessionKey(code2Session.getSessionKey())
                .authorization(code2Session.getAuthorization())
                .build();
    }


    /**
     * 2：根据获取手机号码的组件code 获取手机号码
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
     * 3:更据openId注册
     */
    @GetMapping("/register")
    @ResponseFormat
    public Code2SessionVO register(@RequestParam String openId, @RequestParam String phone) {
        var code2Session = wechatServer.register(openId, phone);
        return Code2SessionVO.builder()
                .openid(code2Session.getOpenid())
                .sessionKey(code2Session.getSessionKey())
                .authorization(code2Session.getAuthorization())
                .build();

    }


}
