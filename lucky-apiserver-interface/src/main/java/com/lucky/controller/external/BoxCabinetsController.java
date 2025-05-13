package com.lucky.controller.external;

import com.lucky.application.OrderServer;
import com.lucky.controller.external.dto.LogisticsOrderDTO;
import com.lucky.controller.external.vo.WechatPrizeInfoVO;
import com.lucky.utils.ResponseFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 盒柜
 *
 * @folder API/小程序/盒柜
 */
@RequestMapping("/wechat/box-cabinets")
@RestController
public class BoxCabinetsController {
    private final OrderServer orderServer;

    public BoxCabinetsController(OrderServer orderServer) {
        this.orderServer = orderServer;
    }

    /**
     * 列表
     */
    @ResponseFormat
    @GetMapping("/list")
    public List<WechatPrizeInfoVO> list(@RequestParam Long wechatUserId) {
        return orderServer.boxCabinets(wechatUserId).stream()
                .map(WechatPrizeInfoVO::getInstance)
                .collect(Collectors.toList());
    }

    /**
     * 生成物流定单
     */
    @ResponseFormat
    @PostMapping("/generate-logistics-order")
    public void generateLogisticsOrder(@RequestBody LogisticsOrderDTO dto) {
        var logisticsOrder = LogisticsOrderDTO.toLogisticsOrder(dto);
        orderServer.generateLogisticsOrder(logisticsOrder);
    }

}
