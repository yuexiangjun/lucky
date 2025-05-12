package com.lucky.controller.external;

import com.lucky.application.CustomerInfoServer;
import com.lucky.controller.external.vo.CustomerInfoVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 客服信息
 *
 * @folder API/小程序/客服信息
 */
@RequestMapping("/wechat/customer")
@RestController
public class WechatCustomerInfoController {
    private final CustomerInfoServer customerInfoServer;

    public WechatCustomerInfoController(CustomerInfoServer customerInfoServer) {
        this.customerInfoServer = customerInfoServer;
    }


    /**
     * 查询所有
     */
    @ResponseFormat
    @GetMapping("/list")
    public List<CustomerInfoVO> getList() {
        return customerInfoServer.getList()
                .stream()
                .map(CustomerInfoVO::getInstance)
                .collect(Collectors.toList());
    }


}
