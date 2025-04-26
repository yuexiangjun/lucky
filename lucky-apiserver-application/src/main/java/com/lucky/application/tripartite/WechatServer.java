package com.lucky.application.tripartite;

import com.lucky.application.WechatUserServer;
import com.lucky.application.interceptor.JwtUtils;
import com.lucky.application.interceptor.LoginUserEntity;
import com.lucky.application.interceptor.TokenEntity;
import com.lucky.domain.entity.WechatUserEntity;
import com.lucky.domain.tripartite.WechatService;
import com.lucky.domain.valueobject.Code2Session;
import com.lucky.domain.valueobject.WechatConfigValueObject;
import com.lucky.domain.valueobject.WechatPhone;
import org.springframework.stereotype.Component;

@Component
public class WechatServer {
    private final WechatService wechatService;
    private final WechatUserServer wechatUserServer;

    public WechatServer(WechatService wechatService, WechatUserServer wechatUserServer) {
        this.wechatService = wechatService;
        this.wechatUserServer = wechatUserServer;
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
        var code2Session = wechatService.code2Session(jsCode);
        LoginUserEntity login = wechatUserServer.login(
                WechatUserEntity.builder()
                        .openid(code2Session.getOpenid())
                        .build()
        );
        code2Session.setAuthorization(login.getAuthorization());
        return code2Session;
    }

    public Code2Session register(String openId, String phone) {

        var wechatUserEntity = wechatUserServer.getByOpenId(openId);

        wechatUserEntity.setPhone(phone);

        wechatUserServer.saveOrUpdate(wechatUserEntity);

        var tokenEntity = TokenEntity.builder()
                .userId(String.valueOf(wechatUserEntity.getOpenid()))
                .username(wechatUserEntity.getOpenid())
                .client(2)
                .createTime(String.valueOf(System.currentTimeMillis()))
                .build();

        var token = JwtUtils.createToken(tokenEntity);

        return Code2Session.builder()
                .authorization(token)
                .openid(openId)
                .build();
    }
}
